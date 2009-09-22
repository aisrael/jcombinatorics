/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Sep 22, 2009
 */
package jcombinatorics.util;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.junit.Test;

/**
 * JUnit tset for {@link Factoradic}.
 *
 * @author Alistair A. Israel
 */
public final class FactoradicTest {

    /**
     * Test for {@link Factoradic#Factoradic(long)}.
     */
    @Test
    public void testFactoradicLong() {
        final int[][] f = { { 0 }, { 1, 0 }, { 2, 1, 0 }, { 2, 5, 2, 0, 1, 1, 0 },
                { 2, 6, 6, 2, 5, 1, 2, 1, 1, 0 }, { 4, 5, 8, 7, 8, 0, 0, 1, 0, 1, 0, 1, 0 },
                { 3, 15, 15, 11, 1, 15, 9, 2, 8, 2, 5, 9, 1, 6, 3, 3, 0, 1, 0, 1, 0 } };
        final long[] n = { 0, 1, 5, 2091, 999999, Integer.MAX_VALUE, Long.MAX_VALUE };
        for (int i = 0; i < f.length; ++i) {
            final Factoradic factoradic = new Factoradic(n[i]);
            final String expected = Arrays.toString(f[i]);
            final String actual = factoradic.toString();
            assertEquals(format("Expected factoradic of %d is %s, was %s", n[i], expected, actual),
                    expected, actual);
        }
    }

    /**
     * Test that {@link Factoradic#Factoradic(int[])} only accepts arrays up to
     * {@link Factoradic#MAX_N} digits long.
     */
    @Test
    public void testFactoradicIntArrayThrowsOnLongArray() {
        final int[] arrayThatsTooLong = { 0, 3, 15, 15, 11, 1, 15, 9, 2, 8, 2, 5, 9, 1, 6, 3, 3, 0, 1, 0,
                1, 0 };
        try {
            new Factoradic(arrayThatsTooLong);
            fail("Should've thrown IllegalArgumentException!");
        } catch (final IllegalArgumentException e) {
            assertEquals(
                    "Array " + Arrays.toString(arrayThatsTooLong) + " has too many elements (max 21)!", e
                            .getMessage());
        }
    }

    /**
     * Test that {@link Factoradic#Factoradic(int[])} validates input arrays
     * properly.
     */
    @Test
    public void testFactoradicIntArrayThrowsInvalidArray() {
        final int[][] invalidFactoradicArrays = { { 1 }, { 0, 1 },
                { 3, 15, 15, 11, 1, 15, 9, 2, 8, 2, 5, 9, 1, 8, 3, 3, 0, 1, 0, 1, 0 } };
        final String[] expectedMessageFragments = { "1) at index 0", "1) at index 1", "8) at index 13", };
        for (int i = 0; i < expectedMessageFragments.length; ++i) {
            final String expectedExceptionMessage = "Illegal factoradic digit ("
                    + expectedMessageFragments[i] + "!";
            final int[] array = invalidFactoradicArrays[i];
            try {
                new Factoradic(array);
                fail("Should've thrown IllegalArgumentException!");
            } catch (final IllegalArgumentException e) {
                assertEquals(expectedExceptionMessage, e.getMessage());
            }
        }
    }

    /**
     * Test for {@link Factoradic#longValue()}.
     */
    @Test
    public void testLongValue() {
        final int[][] f = { { 0 }, { 1, 0 }, { 2, 1, 0 }, { 2, 5, 2, 0, 1, 1, 0 },
                { 2, 6, 6, 2, 5, 1, 2, 1, 1, 0 }, { 4, 5, 8, 7, 8, 0, 0, 1, 0, 1, 0, 1, 0 },
                { 3, 15, 15, 11, 1, 15, 9, 2, 8, 2, 5, 9, 1, 6, 3, 3, 0, 1, 0, 1, 0 } };
        final long[] expected = { 0, 1, 5, 2091, 999999, Integer.MAX_VALUE, Long.MAX_VALUE };
        for (int i = 0; i < expected.length; ++i) {
            final Factoradic factoradic = new Factoradic(f[i]);
            final long actual = factoradic.longValue();
            assertEquals(format("Expected value of %s is %d, was %d", Arrays.toString(f[i]), expected[i],
                    actual), expected[i], actual);
        }
    }

}
