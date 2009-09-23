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
 * JUnit test for {@link SepaPnkIterator}.
 *
 * @author Alistair A. Israel
 */
public final class SepaPnkIteratorTest extends PnkGeneratorTestBase {

    /**
     * Basic test, generate P(5,3)
     *
     * @see PnkGeneratorTestBase#testGenerateP53(Iterable)
     */
    @Test
    public void testGeneratePermutations() {
        testGenerateP53(new SepaPnkIterator.Factory(5, 3));
    }

    /**
     *
     */
    @Test
    public void testCompareAgainstFactoradic() {
        for (int n = 2; n < 9; ++n) {
            for (int k = 1; k <= n; ++k) {
                final FactoradicPnkGenerator factoradic = new FactoradicPnkGenerator(
                        n, k);
                final Iterator<int[]> sepa = new SepaPnkIterator(n, k);
                int i = 0;
                while (sepa.hasNext()) {
                    final int[] actual = sepa.next();
                    final int[] expected = factoradic.get(i);
                    assertArrayEquals(format("P(%d,%d)[%d] expected %s, got %s", n, k, i, Arrays
                            .toString(expected), Arrays.toString(actual)), expected, actual);
                    ++i;
                }
            }
        }
    }
}
