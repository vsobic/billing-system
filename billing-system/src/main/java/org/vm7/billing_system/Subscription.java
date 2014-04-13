package org.vm7.billing_system;

public abstract class Subscription {

	private boolean recurring;

	private boolean current;
	
	public boolean isRecurring() {
		return recurring;
	}

	public abstract boolean isCurrent();

	public abstract boolean needsBilling(int year, int month);
}
