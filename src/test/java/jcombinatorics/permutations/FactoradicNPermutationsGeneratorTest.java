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

import static java.lang.String.format;
import static jcombinatorics.permutations.Constants.P_4;
import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;
import java.util.Iterator;

import org.junit.Test;

/**
 * JUnit test for {@link FactoradicNPermutationsGenerator}.
 *
 * @author Alistair A. Israel
 */
public final class FactoradicNPermutationsGeneratorTest extends PnGeneratorTestBase {

    /**
     * Basic test, compare against P(4)
     */
    @Test
    public void testGeneratePermutations() {
        testGenerateP4(new FactoradicNPermutationsGenerator(N));
    }

    /**
     *
     */
    @Test
    public void testEuler24() {
        final FactoradicNPermutationsGenerator p10 = new FactoradicNPermutationsGenerator(10);
        final int[] expected = { 2, 7, 8, 3, 9, 1, 5, 4, 6, 0 };
        final int[] actual = p10.get(999999);
        assertArrayEquals(format("1 millionth permutation of 0..9 expected %s, was %s", Arrays
                .toString(expected), Arrays.toString(actual)), expected, actual);
    }

    /**
     *
     */
    @Test
    public void testGet() {
        final FactoradicNPermutationsGenerator generator = new FactoradicNPermutationsGenerator(N);
        for (int i = 0; i < P_4.length; ++i) {
            final int[] b = generator.get(i);
            assertArrayEquals(format("[%d] Expected %s, got %s", i, Arrays.toString(P_4[i]), Arrays
                    .toString(b)), P_4[i], b);
        }
    }

    /**
     *
     */
    @Test
    public void testCompareWithSepa() {
        for (int n = 2; n < 9; ++n) {
            final FactoradicNPermutationsGenerator factoradic = new FactoradicNPermutationsGenerator(n);
            final Iterator<int[]> i1 = new SepaPnIterator(n);
            final Iterator<int[]> i2 = factoradic.iterator();
            int c = 0;
            while (i1.hasNext() && i2.hasNext()) {
                final int[] a1 = i1.next();
                final int[] a2 = i2.next();
                assertArrayEquals(a1, a2);
                final int[] b = factoradic.get(c);
                assertArrayEquals(a1, b);
                ++c;
            }
        }
    }
}
