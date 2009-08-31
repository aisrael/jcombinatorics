/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel. All rights reserved.
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

}
