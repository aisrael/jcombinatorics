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
        final FactoradicNPermutationsGenerator generator = new FactoradicNPermutationsGenerator(5);
        final int[][] expected = { { 0, 3, 1, 4, 2 }, { 0, 3, 4, 2, 1 }, { 1, 3, 0, 4, 2 },
                { 2, 0, 1, 4, 3 }, { 3, 4, 0, 2, 1 }, { 4, 2, 1, 3, 0 } };
        final long[] l = { 13, 17, 37, 49, 91, 111 };
        for (int i = 0; i < expected.length; ++i) {
            final int[] actual = generator.get(l[i]);
            assertArrayEquals(format("P(5)[%d] Expected %s, got %s", l[i], Arrays.toString(expected[i]),
                    Arrays.toString(actual)), expected[i], actual);
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
