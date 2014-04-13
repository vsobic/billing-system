/**
 * 
 */
package org.vm7.billing_system;

/**
 * @author vlada
 *
 */
public class CustomerBuilder implements Builder<Customer> {
	
	private boolean subscribed;
	private int paidThroughYear;
	
	private int paidThroughMonth;

	private Subscription subscription;
	
	private boolean paidSubscription;
	private int balance;
	private boolean dueDate;

	public CustomerBuilder withPaidSubscription() {
		subscribed = true;
		return this;
	}

	public CustomerBuilder withBalance(int balance) {
		this.balance = balance;
		return this;
	}

	public Customer build() {
		return new Customer(subscribed,paidThroughYear,paidThroughMonth,subscription);
	}

	public CustomerBuilder withLowBalance() {
		return withBalance(99);
	}

	public CustomerBuilder withDueDate() {
		dueDate = true;
		return this;
	}

	public CustomerBuilder withCurrentSubscription() {
		subscribed = true;
		paidThroughMonth = 8;
		paidThroughYear = 2011;
		return this;
	}

	public CustomerBuilder withNextYearSubscription() {
		subscribed = true;
		paidThroughMonth = 1;
		paidThroughYear = 2012;
		return this;
	}

	public CustomerBuilder withMonthlySubscription() {
		paidThroughMonth = 7;
		paidThroughYear = 2011;
		subscription = new MonthlySubscription();
		return this;
	}

}
