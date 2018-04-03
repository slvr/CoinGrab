package Game;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	private int aCoins;
	
	public Game(int pCoins){
		this.aCoins = pCoins;
		firstTurn();
	}
	
	@SuppressWarnings("resource")
	private void firstTurn(){
		Scanner scanner = new Scanner(System.in);
		int toRemove = this.getCoins();
		while(toRemove >= this.getCoins()){
			System.out.println("How many coins to remove?");
			toRemove = scanner.nextInt();
			if(toRemove >= this.getCoins()){
				System.out.println("Cannot remove this many!");
			}
		}
		removeCoins(toRemove);
		AITurn(toRemove);
	}
	
	private void playerTurn(int prevRem){
		System.out.println("There are now " + this.getCoins() + " coins.");
		Scanner scanner = new Scanner(System.in);
		int toRemove = this.getCoins()+1;
		while(toRemove > this.getCoins() || toRemove > 2*prevRem){
			System.out.println("How many coins to remove?");
			toRemove = scanner.nextInt();
			if(toRemove > this.getCoins() || toRemove > 2*prevRem){
				System.out.println("Cannot remove this many!");
			}
		}
		removeCoins(toRemove);
		if(this.getCoins() == 0){
			System.out.println("You win!");
		}
		else{
			AITurn(toRemove);
		}
		scanner.close();
	}
	
	private void AITurn(int prevRem){
		ArrayList<Integer> fib2kDecomp = new ArrayList<Integer>();
		if(this.getCoins() <= 2*prevRem){
			int rem = this.getCoins();
			removeCoins(this.getCoins());
			System.out.println("Comp removed " + rem + " coins");
			System.out.println("You lose!");
		}
		else{
			fib2kDecomp = decomp(this.getCoins());
			int toRemove = fib2kDecomp.get(fib2kDecomp.size()-1);
			if(toRemove > 2*prevRem && 2*prevRem < (1/3)*this.getCoins()){
				toRemove = 2*prevRem;
			}
			else{
				int tot = this.getCoins();
				tot = tot / 3;
				toRemove = tot - 1;
			}
			removeCoins(toRemove);
			System.out.println("Comp removed " + toRemove + " coins");
			playerTurn(toRemove);
		}
	}
	
	private ArrayList<Integer> decomp(int num){
		ArrayList<Integer> ret = new ArrayList<Integer>();
		while(num > 0){
			int largestFib = findLargestFib(num);
			ret.add(largestFib);
			num = num - largestFib;
		}
		return ret;
	}
	
	private int findLargestFib(int num){
		int m = 0;
		int n = 1;
		while(true){
			int nextFib = findNextFib(m, n);
			if(nextFib > num){
				break;
			}
			else{
				m = n;
				n = nextFib;
			}
		}
		return n;
	}
	
	private int findNextFib(int m, int n){
		return m + n;
	}
	
	private void removeCoins(int toRemove){
		this.aCoins = this.aCoins - toRemove;
	}
	
	private int getCoins(){
		int num = this.aCoins;
		return num;
	}
	
}
