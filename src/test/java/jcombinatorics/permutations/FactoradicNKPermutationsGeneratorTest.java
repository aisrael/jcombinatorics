/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel. All rights reserved.
 *
 * Created Aug 25, 2009
 */
package jcombinatorics.permutations;

import static org.junit.Assert.assertArrayEquals;

import org.junit.Test;

/**
 * JUnit test for {@link FactoradicNPermutationsGenerator}.
 *
 * @author Alistair A. Israel
 */
public final class FactoradicNKPermutationsGeneratorTest extends PnkGeneratorTestBase {

    /**
     * Basic test, generate P(5,3)
     *
     * @see PnkGeneratorTestBase#testGenerateP53(Iterable)
     */
    @Test
    public void testGeneratePermutations() {
        testGenerateP53(new FactoradicNKPermutationsGenerator(5, 3));
    }

    /**
     * Test for
     * {@link FactoradicNKPermutationsGenerator#factoradicNK(int[], int)}.
     */
    @Test
    public void testFactoradicNK() {
        final FactoradicNKPermutationsGenerator pgen53 = new FactoradicNKPermutationsGenerator(5, 3);
        int[] f = new int[3];
        pgen53.factoradicNK(f, 14);
        assertArrayEquals(new int[] { 1, 0, 2 }, f);

        final FactoradicNKPermutationsGenerator pgen42 = new FactoradicNKPermutationsGenerator(4, 2);
        f = new int[2];
        pgen42.factoradicNK(f, 7);
        assertArrayEquals(new int[] { 2, 1 }, f);

    }

    /**
     * Test for {@link FactoradicNKPermutationsGenerator#get(int)}.
     */
    @Test
    public void testGet() {
        final FactoradicNKPermutationsGenerator generator = new FactoradicNKPermutationsGenerator(5, 3);
        for (int i = 0; i < P_5_3.length; ++i) {
            final int[] expected = P_5_3[i];
            final int[] actual = generator.get(i);
            assertArrayEquals(expected, actual);
        }
    }
}
