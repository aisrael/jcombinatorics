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
import static jcombinatorics.permutations.Constants.P_5_3;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

/**
 *
 * @author Alistair A. Israel
 */
public class PnkGeneratorTestBase {

    /**
     * Basic test, generate P(5,3)
     *
     * @param permutations
     *        the P(n,k) generator to test
     */
    protected final void testGenerateP53(final Iterable<int[]> permutations) {
        int i = 0;
        for (final int[] permutation : permutations) {
            assertArrayEquals(format("P(5,3) iter:%d Expected %s, got %s", i, Arrays.toString(P_5_3[i]),
                    Arrays.toString(permutation)), P_5_3[i], permutation);
            ++i;
        }
        assertEquals(P_5_3.length, i);
    }

}