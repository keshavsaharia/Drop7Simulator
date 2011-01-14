/*
 * Drop7 Java Simulator by Keshav Saharia is licensed under a Creative Commons Attribution-NonCommercial 3.0 Unported License.
 * 
 * You may use it in any way provided you preserve the following copyright statement and included it in
 * copyright information about your program: "Drop7 Simulator - Drop7Simulator.java" Copyright Keshav Saharia 2011.
 * 
 * This is a test case of the permutation generator, it doesn't have any real tests but just prints to the console.
 */

/**
 * @author Keshav Saharia (www.keshavsaharia.com)
 * @version 1.0
 */

import junit.framework.TestCase;

public class PermutationGeneratorTest extends TestCase {
	
	public void testIntegerPermutation() {
		PermutationGenerator gen=new PermutationGenerator(7,5);
		for (int j=0 ; j<100 ; j++) {
			int[] i=gen.generateIntegerPermutation(j);
			for (int k=0 ; k<i.length ; k++) {
				System.out.print(i[k]+" ");
			}
			System.out.println();
		}
	}
	
	public void testMaxIndex() {
		PermutationGenerator gen=new PermutationGenerator(7,9);
		System.out.println(gen.maxIntegerPermutationIndex());
		gen.showIntegerPermutation(777777);
	}
	
	public void testMinimumSize() {
		PermutationGenerator gen=new PermutationGenerator(7,1);
		System.out.println(gen.maxIntegerPermutationIndex());
		gen.showIntegerPermutation(4);
	}
}
