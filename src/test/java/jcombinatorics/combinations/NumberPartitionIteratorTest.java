/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Aug 31, 2009
 */
package jcombinatorics.combinations;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import jcombinatorics.AbstractListStepVisitor;

/**
 * JUnit test for {@link NumberPartitionsIterator}. l
 */
public final class NumberPartitionIteratorTest {

	final class ListStepVisitor extends AbstractListStepVisitor<String> {

		public ListStepVisitor(List<String> list) {
			super(list);
		}

		public boolean visit(int[][] result) {
			// map the integers to the list strings:
			for (int i = 0; i < result.length; i++) {
				System.out.print("{");
				for (int j = 0; j < result[i].length; j++) {
					System.out.print(list.get(result[i][j]).toString());
					if (j < result[i].length - 1) {
						System.out.print(",");
					}
				}
				System.out.print("}");
			}
			System.out.println();
			return true;
		}
	}

	/**
	 * Partition a list {a,b,c,d,e,f} into 3 parts
	 */
	@Test
	public void testGenerateC63() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		list.add("f");
		final NumberPartitionsIterator iter = new NumberPartitionsIterator(new ListStepVisitor(list), list.size(), 3);
		iter.execute();
		System.out.println();
	}

	/**
	 * Partition a list {a,b,c,d,e} into 2 parts
	 */
	@Test
	public void testGenerateC52() {
		ArrayList<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		final NumberPartitionsIterator iter = new NumberPartitionsIterator(new ListStepVisitor(list), list.size(), 2);
		iter.execute();
		System.out.println();
	}
}
