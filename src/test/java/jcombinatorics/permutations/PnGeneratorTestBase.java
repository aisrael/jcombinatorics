/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel. All rights reserved.
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
public class PnGeneratorTestBase {

    /**
     * N
     */
    public static final int N = 4;

    /**
     * P(4)
     */
    public static final int[][] P4 = { { 0, 1, 2, 3 }, { 0, 1, 3, 2 }, { 0, 2, 1, 3 }, { 0, 2, 3, 1 },
            { 0, 3, 1, 2 }, { 0, 3, 2, 1 }, { 1, 0, 2, 3 }, { 1, 0, 3, 2 }, { 1, 2, 0, 3 }, { 1, 2, 3, 0 },
            { 1, 3, 0, 2 }, { 1, 3, 2, 0 }, { 2, 0, 1, 3 }, { 2, 0, 3, 1 }, { 2, 1, 0, 3 }, { 2, 1, 3, 0 },
            { 2, 3, 0, 1 }, { 2, 3, 1, 0 }, { 3, 0, 1, 2 }, { 3, 0, 2, 1 }, { 3, 1, 0, 2 }, { 3, 1, 2, 0 },
            { 3, 2, 0, 1 }, { 3, 2, 1, 0 } };

    /**
     * @param generator
     *        the P(n) generator
     */
    protected final void testGenerateP4(final Iterable<int[]> generator) {
        int i = 0;
        for (final int[] permutation : generator) {
            assertArrayEquals(format("[%d] Expected %s, got %s", i, Arrays.toString(P4[i]), Arrays
                    .toString(permutation)), P4[i], permutation);
            ++i;
        }
        assertEquals(24, i);

    }
}
