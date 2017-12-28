//Erik Bracamonte - 111230826 - CSE 114
import java.util.ArrayList;
//import java.lang.StringBuffer;
public abstract class BankCard {
	private String cardholderName;
	private boolean canAddTransation;
	protected long cardNumber;
	protected double balance;
	protected ArrayList<Object> allTransactions = new ArrayList<Object>();

	public BankCard(String cardholderName, long cardNumber) {
		this.cardholderName = cardholderName;
		this.cardNumber = cardNumber;
		this.canAddTransation = true;
		balance = 0;
	}

	public double balance() {
		return balance;
	}

	public long number() {
		return cardNumber;
	}

	public String cardHolder() {
		return cardholderName;
	}
	
	public void flagFraud() {
		this.canAddTransation = false;
	}
	
	public boolean getCanAddTransaction() { 
		return this.canAddTransation; 
	}

	@Override
	public String toString() {
		return "Card # " + cardNumber + "\tBalance: $" + balance;
	}
	
	
	public Object getLastTransaction() {
		if(allTransactions.size() != 0) {
			return allTransactions.get(allTransactions.size() - 1);
		}
		else {
			return null;
		}
	}
	
	public void fixFraud(Transaction t) {
		if (!this.canAddTransation) {
			Transaction curr = (Transaction) allTransactions.remove(allTransactions.size() - 1);
			this.balance += -1 * (curr.amount() + t.amount());
		}
	}
	
	public abstract boolean addTransaction(Transaction t);
	public abstract void printStatement();

	public String tOut(ArrayList payments) {
		String display = payments.toString().replace("[", "\n").replace("]", "").replaceAll(", ", "\n");
		return display;
	}
	
	public String date (int num) {
		String num2 = String.valueOf(num);
		StringBuilder str = new StringBuilder(num2);
		if(num > 999) {
			str.insert(2, "/");
			return str.toString();
		}
		else {
			str.insert(1, "/");
			return str.toString();
		}
	}
}
