package org.vm7.billing_system;

public class MonthlySubscription extends Subscription {

	private int paidThroughYear;
	private int paidThroughMonth;
	
	public boolean isRecurring() {
		return true;
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

	@Override
	public boolean needsBilling(int year, int month) {
		return paidThroughYear <= year && paidThroughMonth < month;
	}

	@Override
	public boolean isCurrent() {
		return true;
	}


}
