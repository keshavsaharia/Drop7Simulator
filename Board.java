/*
 * Drop7 Java Simulator by Keshav Saharia is licensed under a Creative Commons Attribution-NonCommercial 3.0 Unported License.
 * 
 * You may use it in any way provided you preserve the following copyright statement and included it in
 * copyright information about your program: "Drop7 Simulator - Board.java" Copyright Keshav Saharia 2011.
 */

/**
 * @author Keshav Saharia (www.keshavsaharia.com)
 * @version 1.0
 */

public class Board {
	private int[][] board;
	private int chain[]={7, 39, 109, 224, 391, 617, 907, 1267, 1701, 2213, 2809, 3391, 3851, 4265, 4681, 5113};
	
	/**
	 *  Constructs a Board object. A board is a 7 x 7 matrix, with 1-7 representing numbers, 8 a gray circle, 
	 *  9 a cracked gray, 10 a revealed gray.
	 *  @param int[][] other is a 7 x 7 matrix
	 */
	
	public Board() {
		board=new int[7][7];
		for (int x=0 ; x<7 ; x++) {
			for (int y=0 ; y<7 ; y++) {
				board[x][y]=0;
			}
		}
	}
	
	public Board(int[][] other) {
		board=new int[7][7];
		for (int x=0 ; x<7 ; x++) {
			for (int y=0 ; y<7 ; y++) {
				board[x][y]=other[x][y];
			}
		}
	}
	
	/**
	 *  Drops a number (num) into the column col (1 through 7), and returns the score resulting from the drop.
	 *  See the test cases in BoardTest.java for clarification on this method.
	 */
	
	public int drop(int num,int col) {
		if (board[0][col-1]!=0 || num<1 || num>7
							   || col<1 || col>7) {
			return -1;
		}
		
		board[0][col-1]=num;
		applyGravity();
		return explode(0);
	}
	
	/**
	 * Same as the drop function above, but returns the score plus the expected value from any revealed grays.
	 * This method has not been fully tested and may need modification. In theory, it is supposed to give the
	 * statistical expected outcome from the grays by analyzing permutations of resulting numbers from revealed
	 * grays and multiplying by the probability of such a permutation. See testExpectedGrayValue() in the test
	 * case file for clarification.
	 * 
	 */
	
	public int dropExpectedValue(int num,int col) {
		if (board[0][col]!=0) {
			return -1;
		}
		board[0][col]=num;
		applyGravity();
		return explodeExpectedValue(0);
	}
	
	/*
	 * Recursive function for exploding 
	 */
	
	public int explode(int chainNum) {
		int chainScore=0;
		for (int x=0 ; x<7 ; x++) {
			for (int y=0 ; y<7 ; y++) {
				if (board[x][y]>0 && board[x][y]<8 && canExplode(x,y)) {
					chainScore+=chain[chainNum];
					board[x][y]=11;
					revealAround(x,y);
				}
			}
		}
		if (chainScore!=0) {
			for (int x=0 ; x<7 ; x++) {
				for (int y=0 ; y<7 ; y++) {
					if (board[x][y]==11) {
						board[x][y]=0;
					}
				}
			}
		}
		applyGravity();
		if (chainScore==0) {
			return 0;
		}
		return chainScore+explode(chainNum+1);
	}
	
	public int explodeExpectedValue(int chainNum) {
		int chainScore=0;
		int explodedGrayCount=0;
		for (int x=0 ; x<7 ; x++) {
			for (int y=0 ; y<7 ; y++) {
				if (board[x][y]>0 && board[x][y]<8 && canExplode(x,y)) {
					chainScore+=chain[chainNum];
					board[x][y]=11;
					revealAround(x,y);
				}
			}
		}
		if (chainScore!=0) {
			for (int x=0 ; x<7 ; x++) {
				for (int y=0 ; y<7 ; y++) {
					if (board[x][y]==11) {
						board[x][y]=0;
					}
					if (board[x][y]==10) {
						explodedGrayCount++;
					}
				}
			}
		}
		if (explodedGrayCount>0) {
			chainScore+=expectedGrayValue(getBoard(),chainNum,explodedGrayCount);
		}
		applyGravity();
		if (chainScore==0) {
			return 0;
		}
		return chainScore+explodeExpectedValue(chainNum+1);
	}
	
	public int expectedGrayValue(int[][] b, int chainNum,int grayCount) {
		double expectedValue=0;
		PermutationGenerator grayP=new PermutationGenerator(7,grayCount);
		Board temp;
		for (int i=0 ; i<=grayP.maxIntegerPermutationIndex() ; i++) {
			temp=new Board(inputGrays(b,grayP.generateIntegerPermutation(i)));
			expectedValue+=temp.explodeExpectedValue(chainNum+1);
		}
		return (int)(expectedValue/Math.pow(7,grayCount));
	}
	
	public int[][] inputGrays(int[][] b, int[] grayBuffer) {
		int current=0;
		int[][] bout=new int[7][7];
		for (int x=0 ; x<7 ; x++) {
			for (int y=0 ; y<7 ; y++) {
				bout[x][y]=b[x][y];
				if (bout[x][y]==10) {
					bout[x][y]=grayBuffer[current];
					current++;
				}
				if (current==grayBuffer.length) {
					return bout;
				}
			}
		}
		return b;
	}
	
	public boolean canExplode(int x,int y) {
		if (board[x][y]==0 || board[x][y]>7) {
			return false;
		}
		
		if (horizontal(x,y)==board[x][y] || vertical(x,y)==board[x][y]) {
			return true;
		}
		return false;
	}
	
	public int horizontal(int xpos,int ypos) {
		int hor=1;
		if (xpos!=0) {
			for (int x=xpos-1; x>=0 ; x--) {
				if (board[x][ypos]!=0) { hor++; }
				else { break; }
			}
		}
		if (xpos!=6) {
			for (int x=xpos+1 ; x<7 ; x++) {
				if (board[x][ypos]!=0) { hor++; }
				else { break; }
			}
		}
		return hor;
	}
	
	public int vertical(int xpos,int ypos) {
		int vert=1;
		if (ypos!=0) {
			for (int y=ypos-1; y>=0 ; y--) {
				if (board[xpos][y]!=0) { vert++; }
				else { break; }
			}
		}
		if (ypos!=6) {
			for (int y=ypos+1; y<7 ; y++) {
				if (board[xpos][y]!=0) { vert++; }
				else { break; }
			}
		}
		return vert;
	}
	
	public void applyGravity() {
		for (int i=0 ; i<6 ; i++) {
			for (int x=0 ; x<6 ; x++) {
				for (int y=0 ; y<7 ; y++) {
					if (board[x][y]!=0 && board[x+1][y]==0) {
						board[x+1][y]=board[x][y];
						board[x][y]=0;
					}
				}
			}
		}
	}
	
	private void revealAround(int x, int y) {
		try { if (board[x-1][y]>7 && board[x-1][y]<10) { board[x-1][y]++; } } catch(ArrayIndexOutOfBoundsException a) {}
		try { if (board[x][y-1]>7 && board[x][y-1]<10) { board[x][y-1]++; } } catch(ArrayIndexOutOfBoundsException a) {}
		try { if (board[x][y+1]>7 && board[x][y+1]<10) { board[x][y+1]++; } } catch(ArrayIndexOutOfBoundsException a) {}
		try { if (board[x+1][y]>7 && board[x+1][y]<10) { board[x+1][y]++; } } catch(ArrayIndexOutOfBoundsException a) {}
	}
	
	public int levelUp() {
		for (int i=0 ; i<7 ; i++) {
			if (board[0][i]!=0) {
				return -1;
			}
		}
		for (int y=0 ; y<7 ; y++) {
			for (int x=1 ; x<7 ; x++) {
				board[x-1][y]=board[x][y];
			}
		}
		for (int k=0 ; k<7 ; k++) {
			board[6][k]=8;
		}
		int score=explode(0);
		applyGravity();
		return score;
	}
	
	public void draw() {
		for (int x=0 ; x<7 ; x++) {
			for (int y=0 ; y<7 ; y++) {
				if (board[x][y]==0) {
					System.out.print("  ");
				}
				if (board[x][y]>0 && board[x][y]<8) {
					System.out.print(board[x][y]+" ");
				}
				if (board[x][y]==8) {
					System.out.print("X ");
				}
				if (board[x][y]==9) {
					System.out.print("* ");
				}
				if (board[x][y]==10) {
					System.out.print("? ");
				}
			}
			System.out.println();
		}
	}
	
	public int[][] getBoard() {
		return board;
	}
}
