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
package jcombinatorics.util;

import static jcombinatorics.util.MathUtils.factorial;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

/**
 * JUnit test for {@link MathUtils}.
 *
 * @author Alistair A. Israel
 */
public final class MathUtilsTest {

    /**
     * Test for {@link MathUtils#factorial(int)}.
     */
    @Test
    public void testFactorial() {
        assertEquals(1, factorial(1));
        assertEquals(2, factorial(2));
        assertEquals(6, factorial(3));
        assertEquals(3628800, factorial(10));
        assertEquals(2432902008176640000L, factorial(20));
    }

    /**
     * Test for {@link MathUtils#factoradic(long)}.
     */
    @Test
    public void testFactoradicInt() {
        final int[][] expected = { { 1, 0 }, { 2, 1, 0 }, { 2, 5, 2, 0, 1, 1, 0 },
                { 2, 6, 6, 2, 5, 1, 2, 1, 1, 0 }, { 4, 5, 8, 7, 8, 0, 0, 1, 0, 1, 0, 1, 0 } };
        final int[] n = { 1, 5, 2091, 999999, Integer.MAX_VALUE };
        for (int i = 0; i < expected.length; ++i) {
            final int[] actual = MathUtils.factoradic(n[i]);
            assertArrayEquals(String.format("Expected factoradic of %d is %s, was %s", n[i], Arrays
                    .toString(expected[i]), Arrays.toString(actual)), expected[i], actual);
        }
    }

    /**
     * Test for {@link MathUtils#factoradic(int[], long)}.
     */
    @Test
    public void testFactoradicArrayInt() {
        final int[][] expected = { { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 }, { 0, 0, 0, 0, 0, 0, 0, 2, 1, 0 },
                { 0, 0, 0, 2, 5, 2, 0, 1, 1, 0 }, { 2, 6, 6, 2, 5, 1, 2, 1, 1, 0 } };
        final int[] n = { 1, 5, 2091, 999999 };
        final int[] a = new int[10];
        for (int i = 0; i < expected.length; ++i) {
            MathUtils.factoradic(a, n[i]);
            assertArrayEquals(String.format("Expected factoradic of %d is %s, was %s", n[i], Arrays
                    .toString(expected[i]), Arrays.toString(a)), expected[i], a);
        }
    }
}
