//Erik Bracamonte - 111230826
//Countsort
import java.util.*;
public class CSE124_HW2_2 {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
	//Inputs
		System.out.println("Enter length of array: ");
		int size = input.nextInt();
		System.out.println("Enter minimum intenger: ");
		int min = input.nextInt();
		System.out.println("Enter maximum integer: ");
		int max = input.nextInt();
		int[] list = new int[size];
	//Placing random numbers into each element
		for(int j = 0; j < list.length; j++ ){
			list[j] = (int)(Math.random()*(max-min+1))+min;
		}
	//Unsorted Output
		System.out.println("Unsorted Array\n" + Arrays.toString(list));
	//Sorted Output
		System.out.println("Sorted Array\n" + Arrays.toString(countingSort(list, min, max)));
	}
	
	public static int[] countingSort(int[] list, int min, int max){
		int [] count = new int[max-min+1];
	//Counting the frequency of the numbers
		for (int i=0; i<list.length; i++){
			int offset = list[i] - min;
			count[offset]++; 
		}
	//Output the frequency
		//System.out.println(Arrays.toString(count));
	//Sorting the list
		int [] sortedList = list;
		int pos = 0;
		for (int i = 0; i < count.length; i++) {
		    for (int j = 0; j < count[i]; j++) {
		        sortedList[pos] = i + min;
		        pos++;
		    }
		}
        return sortedList; 
	}
}


