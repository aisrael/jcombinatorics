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

import jcombinatorics.util.ArrayUtils;
import jcombinatorics.util.MathUtils;
import jcombinatorics.util.ReadOnlyIterator;

/**
 * P(n) generator in lexicographical order using factoradics. Supports computing
 * the <i>i</i>-th permutation directly using {@link #get(long)}. Only supports
 * n <= 20, since 20! > {@link java.lang.Long#MAX_VALUE}.
 *
 * @author Alistair A. Israel
 * @see <a
 *      href="http://en.wikipedia.org/wiki/Factoradic">http://en.wikipedia.org/wiki/Factoradic</a>
 * @since 0.1
 */
public class FactoradicNPermutationsGenerator implements Iterable<int[]> {

    private final int n;

    private final int[] identity;

    /**
     * @param n
     *        the number of elements
     */
    public FactoradicNPermutationsGenerator(final int n) {
        if (!(n > 0 && n <= 12)) {
            throw new IllegalArgumentException("0 < n <= 12!");
        }
        this.n = n;
        identity = ArrayUtils.identityPermutation(n);
    }

    /**
     * Retrieve the i-th permutation.
     *
     * @param i
     *        int
     * @return int[]
     */
    public final int[] get(final long i) {
        // For P(n) factoradic, last digit is always 0 so no need to compute
        final int[] f = new int[n - 1];
        final int[] a = new int[n];
        factoradic(f, i);
        System.arraycopy(identity, 0, a, 0, n);
        Factoradic.factoradicToPermutation(f, a);
        return a;
    }

    /**
     * Optimized factoradic. Doesn't compute for the last digit (which is always
     * 0).
     *
     * @param f
     *        the array to hold the factoradic
     * @param i
     *        long
     */
    private static void factoradic(final int[] f, final long i) {
        final int len = f.length;
        long m = i;
        int z = 2;
        while (m > 0) {
            f[len - z + 1] = (int) (m % z);
            m /= z;
            ++z;
        }
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Iterable#iterator()
     */
    public final java.util.Iterator<int[]> iterator() {
        return new Iterator();
    }

    /**
     *
     * @author Alistair A. Israel
     */
    private class Iterator extends ReadOnlyIterator<int[]> {

        private final long count = MathUtils.factorial(n);

        private final int[] f = new int[n - 1];

        private final int[] a = new int[n];

        private long index;

        /**
         * {@inheritDoc}
         *
         * @see java.util.Iterator#hasNext()
         */
        public boolean hasNext() {
            return index < count;
        }

        /**
         * {@inheritDoc}
         *
         * @see java.util.Iterator#next()
         */
        public int[] next() {
            factoradic(f, index);
            System.arraycopy(identity, 0, a, 0, n);
            Factoradic.factoradicToPermutation(f, a);
            ++index;
            return a;
        }
    }

}
