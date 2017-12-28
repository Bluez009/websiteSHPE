//ERIK BRACAMONTE - 111230826
//Rolling Dice
import java.util.*;
public class CSE124_HW2 {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
	//Input
		System.out.println("Choose your dice.(#d#): ");
		String dice = input.nextLine();
	//Output
		System.out.println("You rolled a total " + (rollDice(dice)));
	}
	
	public static int rollDice(String dice){
		String[] inputNumber =  dice.split("d");
		int num = Integer.parseInt(inputNumber[0]);  //amount of dice
		int size = Integer.parseInt(inputNumber[1]); //max size of dice
		int roll = 0;
	//Rolling a random number on each dice
		for(int i = 1; i <= num; i++){
			int val = (int)(Math.random()*(size)+1);
			System.out.println("You rolled a " + val); //output each value on said roll
			roll = roll + val;
		}
		return roll;
	}
}
