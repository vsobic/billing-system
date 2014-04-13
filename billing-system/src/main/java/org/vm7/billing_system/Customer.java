package org.vm7.billing_system;

public class Customer {

	// is the customer subscribed
	private boolean subscribed;
	
	private int paidThroughYear;
	
	private int paidThroughMonth;
	
	private int paymentFailures;
	private Subscription subscription;
	
	/**
	 * @param subscribed
	 * @param paidThroughYear
	 * @param paidThroughMonth
	 */
	public Customer(boolean subscribed, int paidThroughYear,
			int paidThroughMonth, Subscription subscription) {
		this.subscribed = subscribed;
		this.paidThroughYear = paidThroughYear;
		this.paidThroughMonth = paidThroughMonth;
		this.subscription = subscription;
	}

	public boolean isSubscribed() {
		return subscribed;
	}

	public void setSubscribed(boolean subscribed) {
		this.subscribed = subscribed;
	}

	public int getPaidThroughYear() {
		return paidThroughYear;
	}

	public void setPaidThroughYear(int paidThroughYear) {
		this.paidThroughYear = paidThroughYear;
	}

	public int getPaidThroughMonth() {
		return paidThroughMonth;
	}

	public void setPaidThroughMonth(int paidThroughMonth) {
		this.paidThroughMonth = paidThroughMonth;
	}

	public int getPaymentFailures() {
		return paymentFailures;
	}

	public void setPaymentFailures(int paymentFailures) {
		this.paymentFailures = paymentFailures;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public void setSubscription(Subscription subscription) {
		this.subscription = subscription;
	}
	
}
