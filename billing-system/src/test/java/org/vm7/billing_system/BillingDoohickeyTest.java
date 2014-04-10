/**
 * 
 */
package org.vm7.billing_system;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;

import org.mockito.Mockito;
import org.testng.annotations.Test;


/**
 * @author vlada
 *
 */
public class BillingDoohickeyTest {

	// Monthly billing
	// Grace period for missed payments ("dunning" status)
	// Not all customer are necessarily subscribers
	// Idle customers should be automatically unsubscribed
	
	@Test
	public void customerWhoDoesNotHaveSubscriptionDoesNotGetCharged() {
		// Source of customers
		// Payment gateway or service for charging customers
		CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
		CreditCardCharger charger = Mockito.mock(CreditCardCharger.class);
		Customer customer = new Customer(false);
		BillingDoohickey thing = new BillingDoohickey(customerRepository, charger);
		
		thing.processMonth(2011,8);
		
		verify(charger, never()).chargeCustomer(customer);
	}
	
	@Test
	public void customerWithSubscriptionThatIsExpiredGetsCharged() {
		// Source of customers
		// Payment gateway or service for charging customers
		CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
		CreditCardCharger charger = Mockito.mock(CreditCardCharger.class);
		Customer customer = new Customer(true); // What does it mean to not have subscription
		BillingDoohickey thing = new BillingDoohickey(customerRepository, charger);
		
		thing.processMonth(2011,8);
		
		verify(charger, times(1)).chargeCustomer(customer);
		
	}
	
	public class Customer {

		// is the customer subscribed
		private boolean subscribed;
		
		public Customer(boolean subscribed) {
			this.subscribed = subscribed;
		}

		public boolean isSubscribed() {
			return subscribed;
		}

		public void setSubscribed(boolean subscribed) {
			this.subscribed = subscribed;
		}
		
	}
	
	public class BillingDoohickey {
		
		CustomerRepository customerRepository;
		CreditCardCharger charger;

		public BillingDoohickey(CustomerRepository customerRepository,
				CreditCardCharger charger) {
			this.customerRepository = customerRepository;
			this.charger = charger;
		}

		public void processMonth(int year, int month) {
			// TODO: implement me!
			
			Customer customer = customerRepository.getCustomers().get(1);
			charger.chargeCustomer(customer);
			//throw new UnsupportedOperationException("Not yet implemented");
		}
		
	}
	
	public interface CustomerRepository {
		
		List<Customer> getCustomers();
		Customer getCustomerById(int customerId);
		
	}
	
	public interface CreditCardCharger {

		void chargeCustomer(Customer customer);
		
	}
}
