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

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

/**
 * JUnit test for {@link RosenIterator}.
 *
 * @author Alistair A. Israel
 */
public final class RosenNumberPartitionIteratorTest {

    private static final long EXPECTED_COUNT_5_2 = Combinations.count(5, 2);
    private static final int[][] C_6_3 = {
        { 1, 1, 4 },
        { 1, 2, 3 },
        { 1, 3, 2 },
        { 1, 4, 1 },
        { 2, 1, 3 },
        { 2, 2, 2 },
        { 2, 3, 1 },
        { 3, 1, 2 },
        { 3, 2, 1 },
        { 4, 1, 1 }
    };

    private static final long EXPECTED_COUNT_4_1 = Combinations.count(4, 1);
    private static final int[][] C_5_2 = { { 1, 4 }, { 2, 3 }, { 3, 2 }, { 4, 1 }, };

    /**
     *
     */
    @Test
    public void testGenerateC63() {
        final RosenNumberPartitionIterator iter = new RosenNumberPartitionIterator(6, 3);
        int count = 0;
        while (iter.hasNext()) {
            final int[] actual = iter.next();
            final int[] expected = C_6_3[count++];
            System.out.print(Arrays.toString(expected));
            System.out.print(", " + Arrays.toString(actual));
            System.out.println("");
            assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        }
        assertEquals(EXPECTED_COUNT_5_2, count);
    }

    /**
     *
     */
    @Test
    public void testGenerateC52() {
        final RosenNumberPartitionIterator iter = new RosenNumberPartitionIterator(5, 2);
        int count = 0;
        while (iter.hasNext()) {
            final int[] actual = iter.next();
            final int[] expected = C_5_2[count++];
            System.out.print(Arrays.toString(expected));
            System.out.print(Arrays.toString(actual));
            System.out.println("");
            assertEquals(Arrays.toString(expected), Arrays.toString(actual));
        }
        assertEquals(EXPECTED_COUNT_4_1, count);
    }
}
