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
package jcombinatorics.util;

import static jcombinatorics.util.ArrayUtils.identityPermutation;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

/**
 * JUnit test for {@link ArrayUtils}.
 *
 * @author Alistair A. Israel
 */
public final class ArrayUtilsTest {

    /**
     * Test for {@link ArrayUtils#identityPermutation(int[])}.
     */
    @Test
    public void testIdentityPermutationIntArray() {
        final int[] expected = { 0, 1, 2, 3, 4, 5 };
        final int[] a = new int[expected.length];
        identityPermutation(a);
        assertArrayEquals(expected, a);
    }

    /**
     * Test for {@link ArrayUtils#identityPermutation(int)}.
     */
    @Test
    public void testIdentityPermutationInt() {
        for (int n = 1; n <= 10; ++n) {
            final int[] expected = new int[n];
            for (int i = 0; i < n; ++i) {
                expected[i] = i;
            }
            assertArrayEquals(expected, identityPermutation(n));
        }
    }

    /**
     * JUnit test for {@link ArrayUtils#valuesAt(int[], int[])}.
     */
    @Test
    public void testValuesAtCharArrayIntArray() {
        final char[] elements = { 'a', 'b', 'c', 'd', 'e' };
        final char[][] expecteds = { { 'c' }, { 'b', 'd' }, { 'a', 'c', 'e' } };
        final int[][] indices = { { 2 }, { 1, 3 }, { 0, 2, 4 } };
        for (int i = 0; i < expecteds.length; ++i) {
            final int[] index = indices[i];
            final char[] actual = ArrayUtils.valuesAt(elements, index);
            assertArrayEquals(expecteds[i], actual);
        }
    }

    /**
     * JUnit test for {@link ArrayUtils#valuesAt(int[], int[])}.
     */
    @Test
    public void testValuesAtIntArrayIntArray() {
        final int[] elements = { 1, 22, 333, 4444, 55555 };
        final int[][] expecteds = { { 333 }, { 22, 4444 }, { 1, 333, 55555 } };
        final int[][] indices = { { 2 }, { 1, 3 }, { 0, 2, 4 } };
        for (int i = 0; i < expecteds.length; ++i) {
            final int[] index = indices[i];
            final int[] actual = ArrayUtils.valuesAt(elements, index);
            assertArrayEquals(expecteds[i], actual);
        }
    }

    /**
     * JUnit test for {@link ArrayUtils#valuesAt(Object[], int[])}.
     */
    @Test
    public void testValuesAtTArrayIntArray() {
        final String[] elements = { "a", "bb", "ccc", "dddd", "eeeee" };
        final String[][] expecteds = { { "ccc" }, { "bb", "dddd" }, { "a", "ccc", "eeeee" } };
        final int[][] indices = { { 2 }, { 1, 3 }, { 0, 2, 4 } };
        for (int i = 0; i < expecteds.length; ++i) {
            final int[] index = indices[i];
            final String[] actual = ArrayUtils.valuesAt(elements, index);
            assertArrayEquals(expecteds[i], actual);
        }
    }
}
