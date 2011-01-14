/*
 * Drop7 Java Simulator by Keshav Saharia is licensed under a Creative Commons Attribution-NonCommercial 3.0 Unported License.
 * 
 * You may use it in any way provided you preserve the following copyright statement and included it in
 * copyright information about your program: "Drop7 Simulator - BoardInput.java" Copyright Keshav Saharia 2011.
 * 
 * This class is a basic visual input program that creates an applet when any of its static methods are called. For example,
 * calling Board board=new Board(BoardInput.createBoard()) will launch an applet that will allow you to input a board, then 
 * will input the resulting 7 x 7 matrix into the Board class. This is the recommended way to input into the Board.java
 * class because it is not prone to input errors that may cause unexpected behavior in the simulator.
 * 
 * To input into the BoardInput applet, click on a square and press a number 1-9 to input the corresponding disc into that
 * square. When you are done, hit the bright green "DONE" button at the bottom.
 * 
 * NOTE: This relies on the Zen graphics class.
 */

/**
 * @author Keshav Saharia (www.keshavsaharia.com)
 * @version 1.0
 */

public class BoardInput {
	
	/**
	 * Launches the user interface to input into a blank 7 x 7 board, and returns the resulting matrix.
	 * @return int[][] board, a 7 x 7 matrix
	 */
	
	public static int[][] createBoard() {
		int[][] b=new int[7][7];
		for (int i=0 ; i<7 ; i++) {
			for (int j=0 ; j<7 ; j++) {
				b[i][j]=0;
			}
		}
		return launchUI(b);
	}
	
	/**
	 * Launches the user interface to modify an existing 7 x 7 board, and returns the resulting matrix.
	 * @param prev, previous 7 x 7 matrix
	 * @return int[][] board, a 7 x 7 matrix
	 */
	
	public static int[][] updateBoard(int[][] prev) {
		int[][] b=new int[7][7];
		for (int i=0 ; i<7 ; i++) {
			for (int j=0 ; j<7 ; j++) {
				b[i][j]=prev[i][j];
			}
		}
		return launchUI(b);
	}
	
	private static int[][] launchUI(int[][] board) {
		Zen.create(700, 750, "");
		Zen.setFont("Courier-50");
		int activeX=8,activeY=8;
		int mouseX=0,mouseY=0;
		while (mouseY<700) {
			drawBoard(board,activeX,activeY);
			Zen.flipBuffer();
			mouseX=Zen.getMouseClickX();
			mouseY=Zen.getMouseClickY();
			activeX=mouseX/100;
			activeY=mouseY/100;
			if (Zen.isKeyPressed('1')) { board[activeY][activeX]=1; }
			if (Zen.isKeyPressed('2')) { board[activeY][activeX]=2; }
			if (Zen.isKeyPressed('3')) { board[activeY][activeX]=3; }
			if (Zen.isKeyPressed('4')) { board[activeY][activeX]=4; }
			if (Zen.isKeyPressed('5')) { board[activeY][activeX]=5; }
			if (Zen.isKeyPressed('6')) { board[activeY][activeX]=6; }
			if (Zen.isKeyPressed('7')) { board[activeY][activeX]=7; }
			if (Zen.isKeyPressed('8')) { board[activeY][activeX]=8; }
			if (Zen.isKeyPressed('9')) { board[activeY][activeX]=9; }
		}
		return board;
	}
	
	public static void drawBoard(int[][] board) {
		drawBoard(board,8,8);
	}
	
	private static void drawBoard(int[][] board,int aX,int aY) {
		for (int i=0 ; i<7 ; i++) {
			for (int j=0 ; j<7 ; j++) {
				Zen.setColor(255, 255, 255);
				if (i==aX && j==aY) { Zen.setColor(255,0,0); }
				Zen.fillRect(i*100,j*100,100,100);
				Zen.setColor(0,0,0);
				if (i==aX && j==aY) { Zen.setColor(140,0,0); }
				Zen.fillRect(i*100+2,j*100+2,96,96);
				draw(i,j,board[j][i]);
			}
		}
		Zen.setColor(0,100,0); 
		Zen.fillRect(0, 700, 700, 50);
		Zen.setColor(0,0,0);
		Zen.drawText("DONE",300,740);
	}
	
	private static void draw(int x,int y,int num) {
		if (num>0 && num<11) {
			setColorTo(num);
			Zen.fillOval(x*100+10,y*100+10,80,80);
			Zen.setColor(250, 250, 250);
			if (num<8) { Zen.drawText(Integer.toString(num), x*100+40, y*100+60); }
			if (num==9) {
				Zen.setColor(0,0,0);
				Zen.drawLine(x*100+10, y*100+10, x*100+90, y*100+90);
				Zen.drawLine(x*100+90, y*100+10, x*100+10, y*100+90);
				Zen.drawLine(x*100+50, y*100+10, x*100+50, y*100+90);
				Zen.drawLine(x*100+10, y*100+50, x*100+90, y*100+50);
			}
			if (num==10) {
				Zen.setColor(0, 0, 0);
				Zen.fillOval(x*100+20, y*100+20, 60, 60);
				Zen.setColor(255, 255, 255);
				Zen.drawText("?", x*100+40, y*100+60);
			}
		}
	}
	
	private static void setColorTo(int num) {
		if (num==1) { Zen.setColor(0,240,0); }
		if (num==2) { Zen.setColor(220,240,0); }
		if (num==3) { Zen.setColor(240,120,0); }
		if (num==4) { Zen.setColor(240,0,0); }
		if (num==5) { Zen.setColor(240,0,240); }
		if (num==6) { Zen.setColor(0,120,120); }
		if (num==7) { Zen.setColor(0,0,240); }
		if (num>7) { Zen.setColor(150,150,150); }
	}
}
