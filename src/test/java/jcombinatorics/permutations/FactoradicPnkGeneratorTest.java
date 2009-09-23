/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Aug 25, 2009
 */
package jcombinatorics.permutations;

import static jcombinatorics.permutations.Constants.P_5_3;
import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

/**
 * JUnit test for {@link FactoradicPnGenerator}.
 *
 * @author Alistair A. Israel
 */
public final class FactoradicPnkGeneratorTest extends PnkGeneratorTestBase {

    /**
     * Basic test, generate P(5,3)
     *
     * @see PnkGeneratorTestBase#testGenerateP53(Iterable)
     */
    @Test
    public void testGeneratePermutations() {
        testGenerateP53(new FactoradicPnkGenerator(5, 3));
    }

    /**
     * Test for
     * {@link FactoradicPnkGenerator#factoradicNK(int[], long)}.
     */
    @Test
    public void testFactoradicNK() {
        final FactoradicPnkGenerator pgen53 = new FactoradicPnkGenerator(5, 3);
        int[] f = new int[3];
        pgen53.factoradicNK(f, 14);
        assertArrayEquals(new int[] { 1, 0, 2 }, f);

        final FactoradicPnkGenerator pgen42 = new FactoradicPnkGenerator(4, 2);
        f = new int[2];
        pgen42.factoradicNK(f, 7);
        assertArrayEquals(new int[] { 2, 1 }, f);

    }

    /**
     * Test for {@link FactoradicPnkGenerator#get(long)}.
     */
    @Test
    public void testGet() {
        final FactoradicPnkGenerator generator = new FactoradicPnkGenerator(5, 3);
        for (int i = 0; i < P_5_3.length; ++i) {
            final int[] expected = P_5_3[i];
            final int[] actual = generator.get(i);
            assertArrayEquals(expected, actual);
        }
    }
}
