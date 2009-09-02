/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Aug 27, 2009
 */
package jcombinatorics.permutations;

import static java.lang.String.format;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

/**
 *
 * @author Alistair A. Israel
 */
public class PnkGeneratorTestBase {

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
     * Basic test, generate P(5,3)
     *
     * @param generator
     *        the P(n,k) generator to test
     */
    protected final void testGenerateP53(final Iterable<int[]> generator) {
        int i = 0;
        for (final int[] permutation : generator) {
            assertArrayEquals(format("P(5,3) iter:%d Expected %s, got %s", i, Arrays.toString(P_5_3[i]),
                    Arrays.toString(permutation)), P_5_3[i], permutation);
            ++i;
        }
        assertEquals(P_5_3.length, i);
    }

}