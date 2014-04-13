/**
 * 
 */
package org.vm7.billing_system;


/**
 * @author vlada
 *
 */
public class BillingProcessor {
	
	public static final int MAX_FAILURES = 3;
	CustomerRepository customerRepository;
	CreditCardCharger charger;

	public BillingProcessor(CustomerRepository customerRepository,
			CreditCardCharger charger) {
		this.customerRepository = customerRepository;
		this.charger = charger;
	}

	public void processMonth(int year, int month) {
		// TODO: implement me!
		
		Customer customer = customerRepository.getCustomers().get(0);
		if (needsBilling(year, month, customer)) {
			boolean charged = charger.chargeCustomer(customer);
			if (!charged) {
				customer.setPaymentFailures(customer.getPaymentFailures()+1);
				if (customer.getPaymentFailures() >= MAX_FAILURES) {
					customer.setSubscribed(false);
				}
			}
		}
		//throw new UnsupportedOperationException("Not yet implemented");
	}

	private boolean needsBilling(int year, int month, Customer customer) {
		if (customer.getSubscription() != null) {
			return customer.getSubscription().needsBilling(year,month);
		}
		return customer.isSubscribed() && 
				customer.getPaidThroughMonth() < month &&
				customer.getPaidThroughYear() <= year;
	}
	
}
