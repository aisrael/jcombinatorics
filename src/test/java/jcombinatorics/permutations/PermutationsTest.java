/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel. All rights reserved.
 *
 * Created Aug 24, 2009
 */
package jcombinatorics.permutations;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit test for {@link Permutations}.
 *
 * @author Alistair A. Israel
 */
public final class PermutationsTest {

    /**
     * Test for {@link Permutations#count()}.
     */
    @Test
    public void testPermutationsCount() {
        assertEquals(1, Permutations.count(1, 1));
        final int[] ns = { 10, 2, 2, 3, 3, 5, 6, 10 };
        final int[] rs = { 0, 1, 2, 1, 3, 3, 6, 4 };
        final long[] expectedCounts = { 1, 2, 2, 3, 6, 60, 720, 5040 };
        for (int i = 0; i < expectedCounts.length; ++i) {
            assertEquals(expectedCounts[i], Permutations.count(ns[i], rs[i]));
        }
    }

    /**
     *
     */
    @Test
    public void testPermutationsOverInts() {
        final int[][] expected = { { 2, 3, 7 }, { 2, 7, 3 }, { 3, 2, 7 }, { 3, 7, 2 }, { 7, 2, 3 },
                { 7, 3, 2 } };
        int count = 0;
        for (final int[] permutation : Permutations.over(2, 3, 7)) {
            assertArrayEquals(expected[count], permutation);
            ++count;
        }
        assertEquals(expected.length, count);
    }

}
