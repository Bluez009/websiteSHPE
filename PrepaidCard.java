//Erik Bracamonte - 111230826 - CSE 114
public class PrepaidCard extends BankCard {
	
	public PrepaidCard(String cardHolder, long cardNumber, double balance) {
		super(cardHolder, cardNumber);
		super.balance = balance;
	}

	public PrepaidCard(String cardHolder, long cardNumber) {
		super(cardHolder, cardNumber);
		balance = 0;
	}

	public boolean addTransaction(Transaction t) {
		if (t.type().equals("debit") && t.amount() <= balance) {
			balance -= t.amount();
			allTransactions.add(t);
			return true;
		} 
		else if (t.type().equals("debit") && t.amount() > balance) {
			return false;
		} 
		else if (t.type().equals("credit")) {
			balance -= t.amount();
			allTransactions.add(t);
		} 
		else if (t.type().equals("redemption")) {
			return false;
		}
		return false;	
	}

	public boolean addFunds(double amount) {
		if (amount > 0) {
			balance += amount;
			Transaction t = new Transaction("top-up", "User payment", amount);
			allTransactions.add(t);
			return true;
		} 
		else {
			return false;
		}
	}

	public String toString() {
		return super.toString();
	}

	public void printStatement() {
		System.out.println("\nCard Holder: " + super.cardHolder() + "\nCard number: " + super.cardNumber
				+ "\nCurrent balance: $" + (Math.round(balance * 100.0) / 100.0) + "\nTransactions: " + tOut(allTransactions));
	}
}
