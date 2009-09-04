/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Aug 24, 2009
 */
package jcombinatorics.permutations;

import static jcombinatorics.permutations.Constants.P_A_BB_CC;
import static jcombinatorics.permutations.Constants.P_5_3;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * JUnit test for {@link Permutations}.
 *
 * @author Alistair A. Israel
 */
public final class PermutationsTest {

    /**
     * Test for {@link Permutations#count(int, int)}.
     */
    @Test
    public void testPermutationsCount() {
        assertEquals(1, Permutations.count(1, 1));
        final int[] ns = { 10, 2, 2, 3, 3, 5, 6, 10, 21 };
        final int[] ks = { 0, 1, 2, 1, 3, 3, 6, 4, 3 };
        final long[] expectedCounts = { 1, 2, 2, 3, 6, 60, 720, 5040, 7980 };
        for (int i = 0; i < expectedCounts.length; ++i) {
            assertEquals(expectedCounts[i], Permutations.count(ns[i], ks[i]));
        }
    }

    /**
     * Test for
     */
    @Test
    public void testPermuteIntInt() {
        assertArrayEquals(P_5_3[26], Permutations.permute(5, 3).get(26));
    }

    /**
     * Test for {@link Permutations#of(int...)}.
     */
    @Test
    public void testPermutationsOfInts() {
        final int[][] expected = { { 2, 3, 7 }, { 2, 7, 3 }, { 3, 2, 7 }, { 3, 7, 2 }, { 7, 2, 3 },
                { 7, 3, 2 } };
        int count = 0;
        for (final int[] permutation : Permutations.of(2, 3, 7)) {
            assertArrayEquals(expected[count], permutation);
            ++count;
        }
        assertEquals(expected.length, count);
    }

    /**
     *
     */
    @Test
    public void testPermutationsOver() {
        final List<String> list = Arrays.asList("a", "bb", "ccc");
        final String[][] expected = P_A_BB_CC;
        int i = 0;
        for (final Iterable<String> permutation : Permutations.over(list)) {
            int j = 0;
            for (final String s : permutation) {
                assertEquals(expected[i][j], s);
                ++j;
            }
            ++i;
        }
    }
}
