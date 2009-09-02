/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Aug 30, 2009
 */
package jcombinatorics.combinations;

import static java.lang.String.format;
import static jcombinatorics.util.MathUtils.factorial;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import jcombinatorics.permutations.Permutations;
import jcombinatorics.util.MathUtils;

import org.junit.Test;

/**
 * JUnit test for {@link Combinations}.
 *
 * @author Alistair A. Israel
 */
public final class CombinationsTest {

    /**
     * Test for {@link Combinations#count(int, int)}.
     */
    @Test
    public void testCount() {
        assertEquals(210, Combinations.count(21, 19));
        assertEquals(2598960, Combinations.count(52, 5));
        for (int n = 2; n < MathUtils.MAX_N_FACTORIAL; ++n) {
            assertEquals(format("C(%d,%d)", n, n), 1, Combinations.count(n, n));
            for (int k = 1; k < n; ++k) {
                final long expected = Permutations.count(n, k) / factorial(k);
                final long actual = Combinations.count(n, k);
                assertEquals(format("C(%d,%d)", n, k), expected, actual);
            }
        }
    }

    private static final int[][] C_5_3 = { { 0, 1, 2 }, { 0, 1, 3 }, { 0, 1, 4 }, { 0, 2, 3 }, { 0, 2, 4 },
            { 0, 3, 4 }, { 1, 2, 3 }, { 1, 2, 4 }, { 1, 3, 4 }, { 2, 3, 4 } };

    /**
     * Test for {@link Combinations#choose(int, int)}.
     */
    @Test
    public void testChooseIntInt() {
        final int[] expected = { 1, 2, 4 };
        assertArrayEquals(expected, Combinations.choose(5, 3).get(7));
        int i = 0;
        for (final int[] actual : Combinations.choose(5, 3)) {
            assertArrayEquals(C_5_3[i], actual);
            ++i;
        }
    }

    /**
     * Test for {@link Combinations#of(Object...)}.
     */
    @Test
    public void testCombinationsOfObjects() {
        final String[] expected = { "abc", "abd", "abe", "acd", "ace", "ade", "bcd", "bce", "bde", "cde" };
        int count = 0;
        for (final String[] combination : Combinations.of("a", "b", "c", "d", "e").take(3)) {
            assertEquals(expected[count], concat(combination));
            ++count;
        }
        assertEquals(expected.length, count);
    }

    /**
     * @param <T>
     *        a type
     * @param a
     *        T[]
     * @return the collections elements, concatenated to a single string
     */
    private static <T> String concat(final T[] a) {
        final StringBuilder sb = new StringBuilder();
        for (final T t : a) {
            sb.append(t);
        }
        return sb.toString();
    }
}
