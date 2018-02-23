package Game;

import java.util.Scanner;

public class Main {

	public static void main(String[] args){
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("How many tokens?");
		int number = scanner.nextInt();
		Game newGame = new Game(number);
		scanner.close();
	}
	
}
