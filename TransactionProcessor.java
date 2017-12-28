//Erik Bracamonte - 111230826 - CSE 114
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class TransactionProcessor {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Scanner input = new Scanner(System.in);
	    System.out.println("Enter card data file please: ");
	    String cardDataFile = input.nextLine();
	    CardList list;
	    list = loadCardData(cardDataFile);  
	    //list = loadCardData("carddata2.txt");  
	    System.out.println("Enter transaction data file name: ");     
	    String transactionDataFile = input.nextLine();       
	    processTransactions(transactionDataFile,list); 
	    //processTransactions("transactiondata2.txt",list); 
	    System.out.println();
	    for(int i = 0; i < list.size();i++){  
	    	list.get(i).printStatement();  
	    	System.out.println("-------------------------------------------");
	    }
}

	public static BankCard convertToCard(String data) {
		long cardNum;
		String cardHolder;
		double cardLimit;
		int expiration;
		String cardType;
		BankCard info;
		Scanner input = new Scanner(data);
		cardNum = input.nextLong();
		cardHolder = input.next();
		cardHolder = cardHolder.replace("_", " ");
		if (getCardType(cardNum) != null) {		
			cardType = getCardType(cardNum);
			if (cardType.equals("CreditCard")) {
				expiration = input.nextInt();
				if (input.hasNextDouble()) {
					cardLimit = input.nextDouble();						
					info = new CreditCard(cardHolder, cardNum, expiration, cardLimit);
					return info;
				} 
				else {
					info = new CreditCard(cardHolder, cardNum, expiration);
					return info;
				}
			}
				
		else if (cardType.equals("RewardsCard")) {
			expiration = input.nextInt();
			if (input.hasNextDouble()) {
				cardLimit = input.nextDouble();
				info = new RewardsCard(cardHolder, cardNum, expiration, cardLimit);
				return info;
			} 
			else {
				info = new RewardsCard(cardHolder, cardNum, expiration);
				return info;
				}
			} 
			else if (cardType.equals("PrepaidCard")) {
				if (input.hasNextDouble()) {
					double balance = input.nextDouble();
					info = new PrepaidCard(cardHolder, cardNum, balance);
					return info;
				} 
				else {
					info = new PrepaidCard(cardHolder, cardNum);
					return info;
				}
			}
		}
			return null;
	}
	public static CardList loadCardData(String fName) throws FileNotFoundException, IOException{
		CardList list = new CardList();
		BufferedReader br = new BufferedReader(new FileReader(fName));
		String line;
		while ((line = br.readLine()) != null) {
			list.add(convertToCard(line));
		}
		return list;
		} 
		
	public static void processTransactions(String filename, CardList c) throws FileNotFoundException, IOException{
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String line;
            while ((line = br.readLine()) != null) {
                String[] words= line.split(" ");
                long cardNumber = Long.parseLong(words[0]);
                int index = 0;
                for(int i = 0; i < c.size(); i++){
                    if(c.get(i).cardNumber == cardNumber)
                        index = i;
                }
                
                String transactionType = words[1];
                if(transactionType.equals("redeem")){
                    int redeemPoints = Integer.parseInt(words[2]);
                    RewardsCard card = (RewardsCard)c.get(index);
                    card.redeemPoints(redeemPoints);
                }
                
                else if(transactionType.equals("top-up")){
                    double addMoney = Double.parseDouble(words[2]);
                    PrepaidCard card = (PrepaidCard)c.get(index);
                    card.addFunds(addMoney);
                }
                
                else {
                	words[3] = words [3].replace("_", " ");
                	Transaction curr = new Transaction(transactionType,words[3], Double.parseDouble(words[2]));
                    BankCard card = c.get(index);
                    Transaction last = (Transaction) card.getLastTransaction();
                                       
                    if (last == null || (curr.type() != last.type() && curr.merchant() != last.merchant() 
                    		&& (curr.amount() >= 1.50 || curr.amount() <= -1.50) 
                    		&& (last.amount() >= 1.50 || last.amount() <= -1.50)
                    		)) {
                    	if (card.getCanAddTransaction())
                    		card.addTransaction(curr);
                    } else {
                    	System.out.println("Fixed " + card.cardHolder());
                    	card.flagFraud();
                    	card.fixFraud(curr);
                    }
                }
            }
	    }

	private static String getCardType(long number) {
		// Return a String indicating whether 'number' belongs to a
		// CreditCard, RewardsCard, or a PrepaidCard (or null if it's none
		// of the three)

		String result;

		int firstTwo = Integer.parseInt(("" + number).substring(0, 2));

		switch (firstTwo) {
		case 84:
		case 85:
			result = "CreditCard";
			break;
		case 86:
		case 87:
			result = "RewardsCard";
			break;
		case 88:
		case 89:
			result = "PrepaidCard";
			break;
		default:
			result = null; // invalid card number
		}

		return result;
	}
}
