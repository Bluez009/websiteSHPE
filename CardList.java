//Erik Bracamonte - 111230826 - CSE 114
import java.util.ArrayList;

public class CardList {
	
	private ArrayList<BankCard> list;
	
	public CardList(){
		 list = new ArrayList<BankCard>();	
	}

	public void add(BankCard b) {
		list.add(b);
	}

	public int size() {
		return list.size();
	}

	public BankCard get(int index) {
		if (index < 0 && index >= list.size()) {
			return null;
		}
		else {
			return list.get(index);
		}
	}
	
	public int indexOf(long cardNumber){
		for (int i = 0; i < list.size(); i++){
			if (list.get(i).cardNumber == cardNumber){
				return i;
			}
	    }
		return -1;
	}
}
