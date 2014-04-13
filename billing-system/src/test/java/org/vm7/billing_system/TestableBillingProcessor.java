/**
 * 
 */
package org.vm7.billing_system;

import static org.mockito.Mockito.when;

import java.util.List;

import org.mockito.Mockito;

/**
 * @author vlada
 *
 */
public class TestableBillingProcessor extends BillingProcessor {

	public CustomerRepository customerRepository;
	public CreditCardCharger charger;
	
	private TestableBillingProcessor(CustomerRepository customerRepository,
			CreditCardCharger charger) {
		super(customerRepository, charger);
		this.customerRepository = customerRepository;
		this.charger = charger;
	}
	
	public static TestableBillingProcessor buildTestableBillingProcessor(List<Customer> customers) {
		CustomerRepository customerRepository = Mockito.mock(CustomerRepository.class);
		when(customerRepository.getCustomers()).thenReturn(customers);
		return new TestableBillingProcessor(customerRepository, 
											Mockito.mock(CreditCardCharger.class));
	}
}
