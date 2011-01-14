/*
 * Drop7 Java Simulator by Keshav Saharia is licensed under a Creative Commons Attribution-NonCommercial 3.0 Unported License.
 * 
 * You may use it in any way provided you preserve the following copyright statement and included it in
 * copyright information about your program: "Drop7 Simulator - BoardTest.java" Copyright Keshav Saharia 2011.
 * 
 * This program is a series of JUnit 3 test cases for Board.java. I recommend adding more tests if you wish to modify Board.java
 * as these tests do not cover all the methods in Board.java.
 */

/**
 * @author Keshav Saharia (www.keshavsaharia.com)
 * @version 1.0
 */

import junit.framework.TestCase;

public class BoardTest extends TestCase {
	
	public void testApplyGravity() {
		int[][] before= {{0,0,4,0,3,0,0},
			    		 {0,0,0,0,2,6,0},
			    		 {3,0,1,0,0,0,0},
			    		 {0,4,0,0,0,0,1},
			    		 {0,0,0,0,2,0,0},
			    		 {0,6,0,0,0,0,0},
			    	 	 {3,5,0,5,7,5,0}};
		int[][] after=  {{0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0},
						 {0,0,0,0,3,0,0},
						 {0,4,0,0,2,0,0},
						 {3,6,4,0,2,6,0},
						 {3,5,1,5,7,5,1}};
		Board board=new Board(before);
		board.applyGravity();
		for (int x=0 ; x<7 ; x++) {
			for (int y=0 ; y<7 ; y++) {
			 	assertEquals(board.getBoard()[x][y],after[x][y]);
			}
		}
	}
	
	public void testDrop() {
		int[][] before= {{0,0,0,0,0,0,0},
			    		 {0,0,0,0,0,0,0},
			    		 {0,0,0,0,0,0,0},
			    		 {0,0,0,0,0,0,0},
			    		 {0,0,0,0,0,0,0},
			    		 {0,0,0,0,0,0,0},
			    	 	 {3,5,0,5,7,5,0}};
		int[][] after=  {{0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0},
						 {0,0,0,5,0,0,0},
						 {3,5,0,5,0,5,0}};
		Board board=new Board(before);
		int score=0;
		score+=board.drop(7,3);
		score+=board.drop(1,2);
		score+=board.drop(5,4);
		score+=board.drop(1,7);
		for (int x=0 ; x<7 ; x++) {
			for (int y=0 ; y<7 ; y++) {
			 	assertEquals(board.getBoard()[x][y],after[x][y]);
			}
		}
	}
	
	public void testCanExplode() {
		int[][] b=  {{0,0,0,0,0,0,0},
				 	 {0,0,0,0,0,0,0},
				 	 {0,0,0,0,0,0,0},
				 	 {0,0,0,0,0,0,0},
				 	 {0,4,0,0,2,0,0},
				 	 {3,6,4,0,2,6,0},
				 	 {3,5,1,5,7,5,1}};
		Board board=new Board(b);
		assertEquals(board.canExplode(6,4),true);
		assertEquals(board.canExplode(6,5),false);
		assertEquals(board.canExplode(6,4),true);
		assertEquals(board.canExplode(5,4),true);
		assertEquals(board.canExplode(6,2),false);
		assertEquals(board.canExplode(6,6),true);
		assertEquals(board.canExplode(0,0),false);
		assertEquals(board.canExplode(5,0),true);
		assertEquals(board.canExplode(0,5),false);
		assertEquals(board.canExplode(6,4),true);
	}
	
	public void testDropChain1() {
		int[][] before= {{0,0,0,0,0,0,0},
			    		 {0,0,0,0,0,0,0},
			    		 {0,0,0,0,0,0,0},
			    		 {0,0,0,0,0,0,0},
			    		 {0,0,0,0,0,0,0},
			    		 {0,0,0,0,0,0,0},
			    	 	 {0,0,0,2,0,0,0}};
		int[][] after=  {{0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0}};
		Board board=new Board(before);
		int score=0;
		score+=board.drop(3,2);
		score+=board.drop(2,3);
		board.draw();
		assertEquals(score,85);
		for (int x=0 ; x<7 ; x++) {
			for (int y=0 ; y<7 ; y++) {
			 	assertEquals(board.getBoard()[x][y],after[x][y]);
			}
		}
	}
	
	public void testDropChain2() {
		int[][] before= {{0,0,0,0,0,0,0},
			    		 {0,0,0,0,0,0,0},
			    		 {0,0,0,0,0,0,0},
			    		 {0,0,0,0,0,0,0},
			    		 {0,0,0,0,0,0,0},
			    		 {6,0,0,0,0,0,7},
			    	 	 {6,5,3,5,0,4,7}};
		int[][] after=  {{0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0},
						 {0,0,3,0,0,4,0}};
		Board board=new Board(before);
		int score=0;
		score=board.drop(2,5);
		assertEquals(score,1778);
		for (int x=0 ; x<7 ; x++) {
			for (int y=0 ; y<7 ; y++) {
			 	assertEquals(board.getBoard()[x][y],after[x][y]);
			}
		}
	}
	
	public void testRevealAround() {
		int[][] before= {{0,0,0,0,0,0,0},
			    		 {0,0,0,0,0,0,0},
			    		 {0,0,0,0,0,0,0},
			    		 {0,0,0,0,0,0,0},
			    		 {0,0,0,0,0,0,0},
			    		 {0,0,0,0,0,0,0},
			    	 	 {6,8,0,4,8,4,0}};
		int[][] after=  {{0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0},
						 {0,10,0,0,10,0,3}};
		Board board=new Board(before);
		int score=0;
		score=board.drop(1,3);
		assertEquals(score,14);
		assertEquals(board.getBoard()[6][1],10);
		score+=board.drop(3,7);
		assertEquals(score,28);
		for (int x=0 ; x<7 ; x++) {
			for (int y=0 ; y<7 ; y++) {
			 	assertEquals(board.getBoard()[x][y],after[x][y]);
			}
		}
	}
	
	public void testLevelUp() {
		int[][] before= {{0,0,0,0,0,0,0},
			    		 {0,0,0,0,0,0,0},
			    		 {0,0,0,0,0,0,0},
			    		 {0,0,0,0,0,0,0},
			    		 {0,0,0,0,0,2,0},
			    		 {6,0,0,0,0,4,7},
			    	 	 {6,5,2,5,0,4,7}};
		int[][] after=  {{0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0},
						 {6,0,0,0,0,0,7},
						 {6,5,0,5,0,0,7},
						 {8,8,9,8,8,10,8}};
		Board board=new Board(before);
		int score=board.levelUp();
		assertEquals(score,60);
		for (int x=0 ; x<7 ; x++) {
			for (int y=0 ; y<7 ; y++) {
			 	assertEquals(board.getBoard()[x][y],after[x][y]);
			}
		}
	}
	
	public void testInputGrays() {
		int[][] before= {{0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0},
						 {0,0,0,0,0,0,0},
						 {6,10,0,0,0,0,7},
						 {6,5,0,5,0,0,7},
						 {8,10,9,8,8,10,8}};
		int[] input=new int[] {1,2,4};
		Board board=new Board(before);
		board=new Board(board.inputGrays(before,input));
	}
	
	public void testExpectedGrayValue() {
		int[][] before= {{0,0,0,0,0,0,0},
			    		 {0,0,0,0,0,0,0},
			    		 {0,0,0,0,0,0,0},
			    		 {0,0,0,0,0,0,0},
			    		 {0,0,0,0,0,0,0},
			    		 {0,0,0,3,0,0,0},
			    	 	 {0,0,9,8,0,0,0}};
		Board board=new Board(before);
		assertEquals(board.dropExpectedValue(2,3),86);
	}
}