/*
 * Drop7 Java Simulator by Keshav Saharia is licensed under a Creative Commons Attribution-NonCommercial 3.0 Unported License.
 * 
 * You may use it in any way provided you preserve the following copyright statement and included it in
 * copyright information about your program: "Drop7 Simulator - Drop7Simulator.java" Copyright Keshav Saharia 2011.
 * 
 * This class is a very basic, unclean, 10-minute coding example of how to use the Board and PermutationGenerator 
 * to simulate a game of Drop7. I will release a better version when I have time to write one.
 */

/**
 * @author Keshav Saharia (www.keshavsaharia.com)
 * @version 1.0
 */

import java.util.Scanner;

public class Drop7Simulator {
	
	static int move=1;
	
	public static void main(String[] args) {
		Scanner s=new Scanner(System.in);
		int disc;
		double score=0;
		int[][] board=BoardInput.createBoard();
		PermutationGenerator generator=new PermutationGenerator(7,10-move*2);
		
		System.out.print("Enter disc input: ");
		disc=s.nextInt();
		System.out.println("Running through all permutations...");
		
		for (int i=0 ; i<7 ; i++) {
			score=0;
			for (double j=0 ; j<generator.maxIntegerPermutationIndex() ; j++) {
				score+=testPermutation(new Board(board),generator.generateIntegerPermutation(j),disc,i);
			}
			System.out.println("Column "+(i+1)+": "+(score/generator.maxIntegerPermutationIndex()));
		}
	}
	
	public static double testPermutation(Board board,int[] perm,int init, int column) {
		double score=0;
		double s=board.drop(init,column);
		if (s<0) { return 0; }
		score+=s;
		for (int i=0 ; i<5-move ; i++) {
			s=board.drop(perm[i*2],perm[i*2+1]-1);
			if (s<0) { return 0; }
			score+=s;
		}
		return score;
	}
}
