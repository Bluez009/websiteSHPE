//Erik Bracamonte - 111230826 - CSE 114
import java.util.ArrayList;

public class RewardsCard extends CreditCard {
	
	protected int rPoints; //Driver helper uses "Points" instead of rPoints 

	public RewardsCard(String holder, long number, int expiration, double limit) {
		super(holder, number, expiration, limit);
		rPoints = 0;
	}

	public RewardsCard(String holder, long number, int expiration) {
		super(holder, number, expiration);
		limit = 500.00;
		rPoints = 0;
	}

	public int rewardPoints() {
		return rPoints;
	}

	public boolean redeemPoints(int points) {
		if (points <= rPoints && balance >= (points/100.00)) {
			balance -= (points / 100.00);
			rPoints -= points;
			Transaction t = new Transaction("redemption", "CSEBank", (points/100.00));
			allTransactions.add(t);
			return true;
		}
		else if(points <= rPoints &&balance > 0 && balance < (points/100.00)) {
			rPoints -= (balance*100.00);
			Transaction t = new Transaction("redemption", "CSEBank", balance);
			balance -= balance;
			allTransactions.add(t);
			return true;
		}
		
		else {
			return false;
		}
	}

	@Override
	public String toString() {
		return super.toString() + " Reward points: " + rPoints;
	}
	
	@Override
	public boolean addTransaction(Transaction t) {
		if (t.type().equals("debit") && t.amount() <= (limit-balance)) {
			balance += t.amount();
			allTransactions.add(t);
			rPoints += (int)(t.amount() * 100);
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
		else if (t.type().equals("redemption")){
			return false;
		}
		else{
			return false;
		}
	}
	@Override
	public void printStatement(){
		System.out.println("\nCard holder: " + super.cardHolder() + "\nCard number: " + super.cardNumber
				+ "\nCurrent Balance: $" + (Math.round(balance * 100.0) / 100.0) + "\nExpiration: " + date(super.expiration()) + "\nReward points: "+ rPoints + "\nTransactions: "
				+ tOut(allTransactions));
	}
}
