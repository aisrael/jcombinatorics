/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel. All rights reserved.
 *
 * Created Aug 27, 2009
 */
package jcombinatorics.permutations;

import jcombinatorics.util.ArrayUtils;
import jcombinatorics.util.MathUtils;

/**
 * P(n) generator in lexicographical order using factoradics. Supports computing
 * the <i>i</i>-th permutation directly using {@link #get(long)}. Only supports n
 * <= 20, since 20! > {@link java.lang.Long#MAX_VALUE}.
 *
 * @author Alistair A. Israel
 * @see <a
 *      href="http://en.wikipedia.org/wiki/Factoradic">http://en.wikipedia.org/wiki/Factoradic</a>
 * @since 0.1
 */
public class FactoradicNPermutationsGenerator implements Iterable<int[]> {

    private final int n;

    /**
     * @param n
     *        the number of elements
     */
    public FactoradicNPermutationsGenerator(final int n) {
        if (!(n > 0 && n <= 12)) {
            throw new IllegalArgumentException("0 < n <= 12!");
        }
        this.n = n;
    }

    /**
     * Retrieve the i-th permutation.
     *
     * @param i
     *        int
     * @return int[]
     */
    public final int[] get(final long i) {
        final int[] f = new int[n];
        final int[] a = new int[n];
        factoradic(f, i);
        ArrayUtils.identityPermutation(a);
        factoradicToPermutation(f, a);
        return a;
    }

    /**
     * @param f
     *        the array to hold the factoradic
     * @param i
     *        long
     */
    private static void factoradic(final int[] f, final long i) {
        final int len = f.length;
        long m = i;
        int z = 1;
        while (m > 0) {
            f[len - z] = (int) (m % z);
            m /= z;
            ++z;
        }
    }

    /**
     * @param f
     *        the factoradic
     * @param a
     *        array of int
     */
    private static void factoradicToPermutation(final int[] f, final int[] a) {
        for (int i = 0; i < a.length - 1; ++i) {
            if (f[i] != 0) {
                final int t = a[i];
                int j = i + f[i];
                a[i] = a[j];
                while (j > i + 1) {
                    a[j] = a[j - 1];
                    --j;
                }
                a[j] = t;
            }
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
    private class Iterator implements java.util.Iterator<int[]> {

        private final long count = MathUtils.factorial(n);

        private final int[] f = new int[n];

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
            ArrayUtils.identityPermutation(a);
            factoradicToPermutation(f, a);
            ++index;
            return a;
        }

        /**
         * {@inheritDoc}
         *
         * @see java.util.Iterator#remove()
         */
        public void remove() {
            throw new UnsupportedOperationException("remove() not supported!");
        }

    }

}
