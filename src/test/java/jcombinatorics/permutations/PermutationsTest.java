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

import static jcombinatorics.permutations.Constants.PERMUTATIONS_OF_A_BB_CC;
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
     * P(5,3)
     */
    public static final int[][] P_5_3 = { { 0, 1, 2 }, { 0, 1, 3 }, { 0, 1, 4 }, { 0, 2, 1 }, { 0, 2, 3 },
            { 0, 2, 4 }, { 0, 3, 1 }, { 0, 3, 2 }, { 0, 3, 4 }, { 0, 4, 1 }, { 0, 4, 2 }, { 0, 4, 3 },
            { 1, 0, 2 }, { 1, 0, 3 }, { 1, 0, 4 }, { 1, 2, 0 }, { 1, 2, 3 }, { 1, 2, 4 }, { 1, 3, 0 },
            { 1, 3, 2 }, { 1, 3, 4 }, { 1, 4, 0 }, { 1, 4, 2 }, { 1, 4, 3 }, { 2, 0, 1 }, { 2, 0, 3 },
            { 2, 0, 4 }, { 2, 1, 0 }, { 2, 1, 3 }, { 2, 1, 4 }, { 2, 3, 0 }, { 2, 3, 1 }, { 2, 3, 4 },
            { 2, 4, 0 }, { 2, 4, 1 }, { 2, 4, 3 }, { 3, 0, 1 }, { 3, 0, 2 }, { 3, 0, 4 }, { 3, 1, 0 },
            { 3, 1, 2 }, { 3, 1, 4 }, { 3, 2, 0 }, { 3, 2, 1 }, { 3, 2, 4 }, { 3, 4, 0 }, { 3, 4, 1 },
            { 3, 4, 2 }, { 4, 0, 1 }, { 4, 0, 2 }, { 4, 0, 3 }, { 4, 1, 0 }, { 4, 1, 2 }, { 4, 1, 3 },
            { 4, 2, 0 }, { 4, 2, 1 }, { 4, 2, 3 }, { 4, 3, 0 }, { 4, 3, 1 }, { 4, 3, 2 } };

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
        final String[][] expected = PERMUTATIONS_OF_A_BB_CC;
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
