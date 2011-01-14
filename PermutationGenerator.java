/*
 * Drop7 Java Simulator by Keshav Saharia is licensed under a Creative Commons Attribution-NonCommercial 3.0 Unported License.
 * 
 * You may use it in any way provided you preserve the following copyright statement and included it in
 * copyright information about your program: "Drop7 Simulator - Drop7Simulator.java" Copyright Keshav Saharia 2011.
 * 
 * This is a pretty basic permutation generator, which can generate any permutation given a radix and length of permutation.
 * See the JUnit test case for information on how to use it.
 */

/**
 * @author Keshav Saharia (www.keshavsaharia.com)
 * @version 1.0
 */

import java.lang.Math;

public class PermutationGenerator {
	private int radix, length;
	
	/**
	 * 
	 * @param r - radix of the permutation (i.e. the set { (1,1,1), (1,1,2), (1,1,3), (1,2,1), (1,2,2), ...} has a radix of 3)
	 * @param l - length of the permutation (i.e. above set has length 3)
	 */
	
	public PermutationGenerator(int r, int l) {
		radix=r;
		length=l;
	}
	
	/**
	 * Generates an integer permutation corresponding to the num'th permutation in the total permutation set.
	 * @param num - permutation number
	 * @return int[] containing the values of the permutation
	 */
	
	public int[] generateIntegerPermutation(double num) {
		int[] perm=new int[length];
		for (int i=0 ; i<length ; i++) {
			perm[i]=1+(int)(num % ((int)(Math.pow(radix,length-i))))/((int)(Math.pow(radix,length-i-1)));
		}
		return perm;
	}
	
	public void showIntegerPermutation(double num) {
		int[] temp=generateIntegerPermutation(num);
		System.out.print("[ ");
		for (int i=0 ; i<temp.length ; i++) {
			System.out.print(temp[i]+" ");
		}
		System.out.println("]");
	}
	
	public int maxIntegerPermutationIndex() {
		return (int)Math.pow(radix, length)-1;
	}
}
