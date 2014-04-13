/**
 * 
 */
package org.vm7.billing_system;

import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;


/**
 * @author vlada
 *
 */
public class BillingProcessorTests {

	// Monthly billing
	// Grace period for missed payments ("dunning" status)
	// Not all customer are necessarily subscribers
	// Idle customers should be automatically unsubscribed
	// What about customers who sign up today?
	
	
	@Test
	public void customerWhoDoesNotHaveSubscriptionDoesNotGetCharged() {
		Customer customer = new CustomerBuilder()
								.build();
		BillingProcessor processor = TestableBillingProcessor.buildTestableBillingProcessor(Arrays.asList(customer)); 
		
		processor.processMonth(2011,8);
		
		verify(processor.charger, never()).chargeCustomer(customer);
	}
	
	@Test
	public void customerWithSubscriptionThatIsExpiredGetsCharged() {
		
		Customer customer = new CustomerBuilder()
								.withPaidSubscription()
								.withMonthlySubscription()
								.build(); 
		BillingProcessor processor = TestableBillingProcessor.buildTestableBillingProcessor(Arrays.asList(customer));
		
		processor.processMonth(2011,8);
		
		verify(processor.charger, times(1)).chargeCustomer(customer);
	}
	
	@Test
	public void customerWithSubscriptionThatIsCurrentDoesNotGetCharged() {
		Customer customer = new CustomerBuilder()
								.withPaidSubscription()
								.withCurrentSubscription()
								.build(); 
		BillingProcessor processor = TestableBillingProcessor.buildTestableBillingProcessor(Arrays.asList(customer));

		processor.processMonth(2011,8);
		
		verify(processor.charger, never()).chargeCustomer(customer);
		
	}

	@Test
	public void customerWithSubscriptionThatIsCurrentThroughNextYearDoesNotGetCharged() {
		Customer customer = new CustomerBuilder()
								.withPaidSubscription()
								.withNextYearSubscription()
								.build(); 
		BillingProcessor processor = TestableBillingProcessor.buildTestableBillingProcessor(Arrays.asList(customer));

		processor.processMonth(2011,8);
		
		verify(processor.charger, never()).chargeCustomer(customer);
		
	}
	
	@Test
	public void customerWhoIsSubscribedAndDueToPayButFailesOnceIsStilSubcribed() {
		Customer customer = new CustomerBuilder()
								.withPaidSubscription()
								.withMonthlySubscription()
								.build();
		BillingProcessor processor = TestableBillingProcessor
				.buildTestableBillingProcessor(Arrays.asList(customer));
		when(processor.charger.chargeCustomer(customer)).thenReturn(false);
		processor.processMonth(2011, 8);

		Assert.assertTrue(customer.getSubscription().isCurrent());

	}

	@Test
	public void customerWhoIsSubscribedAndDueToPayButFailesMaximumTimesOnceIsNoLongerSubcribed() {
		Customer customer = new CustomerBuilder()
								.withPaidSubscription()
								.build();
		BillingProcessor processor = TestableBillingProcessor
				.buildTestableBillingProcessor(Arrays.asList(customer));
		when(processor.charger.chargeCustomer(customer)).thenReturn(false);
		
		for (int i = 0; i < BillingProcessor.MAX_FAILURES; i++) {
			processor.processMonth(2011, 8);	
		}
		
		Assert.assertFalse(customer.isSubscribed());

	}

}
