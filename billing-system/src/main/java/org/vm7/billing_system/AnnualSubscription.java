package org.vm7.billing_system;


public class AnnualSubscription extends Subscription {

	public boolean isRecurring() {
		return false;
	}

	@Override
	public boolean needsBilling(int year, int month) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public boolean isCurrent() {
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
