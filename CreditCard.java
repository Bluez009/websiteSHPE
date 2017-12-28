//Erik Bracamonte - 111230826 - CSE 114
class CreditCard extends BankCard {
	
	private int expiration;
	protected double limit;

	public CreditCard(String cardHolder, long cardNumber, int expiration, double limit) {
		super(cardHolder, cardNumber);
		this.expiration = expiration;
		this.limit = limit;
	}

	public CreditCard(String cardHolder, long cardNumber, int expiration) {
		super(cardHolder, cardNumber);
		this.expiration = expiration;
		limit = 500.00;
	}

	public double limit() {
		return limit;
	}

	public double availableCredit() {
		return limit - balance;
	}

	public int expiration() {
		return expiration;
	}

	@Override
	public String toString() {
		return super.toString() + "\tExpiration date: " + expiration;
	}
	
	public boolean addTransaction(Transaction t) {
		if (t.type().equals("debit") && t.amount() <= (limit-balance)) {
			balance += t.amount();
			allTransactions.add(t);
			return true;
		} 
		else if (t.type().equals("debit") && t.amount() > (limit-balance)) {
			return false;
		} 
		else if (t.type().equals("credit")) {
			balance += t.amount();
			allTransactions.add(t);
			return true;
		} 
		else if( t.type().equals("redemption")){
			return false;
		}
		else{
			return false;
		}
	}

	public void printStatement() {
		System.out.println("\nCard Holder: " + super.cardHolder() + "\nCard number: " + super.cardNumber
				+ "\nCurrent Balance: $" + (Math.round(balance * 100.0) / 100.0) + "\nExpiration: " + date(expiration) + "\nTransactions: " + tOut(allTransactions));
	}
}
