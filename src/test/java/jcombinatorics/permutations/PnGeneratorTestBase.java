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
import static jcombinatorics.permutations.Constants.P_4;
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
     * @param generator
     *        the P(n) generator
     */
    protected final void testGenerateP4(final Iterable<int[]> generator) {
        int i = 0;
        for (final int[] permutation : generator) {
            assertArrayEquals(format("[%d] Expected %s, got %s", i, Arrays.toString(P_4[i]), Arrays
                    .toString(permutation)), P_4[i], permutation);
            ++i;
        }
        assertEquals(24, i);

    }
}
