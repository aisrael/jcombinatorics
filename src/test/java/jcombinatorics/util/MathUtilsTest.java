/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel. All rights reserved.
 *
 * Created Aug 24, 2009
 */
package jcombinatorics.util;

import static jcombinatorics.util.MathUtils.factorial;
import static jcombinatorics.util.MathUtils.lcm;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import jcombinatorics.permutations.Permutations;

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
     * Test for {@link MathUtils#factoradic(int)}.
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
     * Test for {@link MathUtils#factoradic(int[], int)}.
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

    /**
     * Test for {@link MathUtils#max(int[])}.
     */
    @Test
    public void testMax() {
        assertEquals(1, MathUtils.max(1));
        assertEquals(0, MathUtils.max(-1, 0));
        assertEquals(1, MathUtils.max(Integer.MIN_VALUE, 1, 0));
    }

    /**
     * Test for {@link MathUtils#lcm(int[])}.
     */
    @Test
    public void testLcm() {
        assertEquals(2, lcm(2));

        final int[] expected = { 4, 6, 9, 18, 42, };
        final int[] a = { 2, 2, 3, 6, 6, };
        final int[] b = { 4, 3, 9, 9, 21, };
        for (int i = 0; i < expected.length; ++i) {
            assertEquals(expected[i], lcm(a[i], b[i]));
        }
        assertEquals(12, lcm(2, 3, 4));
        assertEquals(60, lcm(2, 4, 3, 5, 6));
        final int[] elements = { 2, 3, 4, 5, 6 };
        for (final int[] numbers : Permutations.over(elements)) {
            assertEquals("P(" + Arrays.toString(numbers) + ") should be 60", 60, MathUtils.lcm(numbers));
        }
    }

    /**
     * Test for {@link MathUtils#gcd(int, int)}.
     */
    @Test
    public void testGcd() {
        final int[] expected = { 3, 5, 1, 2, 3 };
        final int[] a = { 3, 0, 9, 2, 6 };
        final int[] b = { 0, 5, 28, 4, 9 };
        for (int i = 0; i < expected.length; ++i) {
            assertEquals(expected[i], MathUtils.gcd(a[i], b[i]));
        }
    }

}
