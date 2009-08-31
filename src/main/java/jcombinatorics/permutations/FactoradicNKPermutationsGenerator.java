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

/**
 * P(n, k) generator in lexicographical order.
 *
 * @author Alistair A. Israel
 * @see <a href="http://en.wikipedia.org/wiki/Factoradic">http://en.wikipedia.org/wiki/Factoradic</a>
 * @since 0.1
 */
public class FactoradicNKPermutationsGenerator implements Iterable<int[]> {

    private final int n;

    private final int k;

    /**
     * @param n
     *        the number of elements
     * @param k
     *        taken k at a time
     */
    public FactoradicNKPermutationsGenerator(final int n, final int k) {
        assert n > 0;
        this.n = n;
        this.k = k;
    }

    /**
     * Retrieve the <i>i</i>-th permutation.
     *
     * @param i
     *        long
     * @return int[]
     */
    public final int[] get(final long i) {
        final int[] f = new int[k];
        final int[] a = new int[n];
        factoradicNK(f, i);
        ArrayUtils.identityPermutation(a);
        factoradicToPermutation(f, a);
        System.arraycopy(a, 0, f, 0, k);
        return f;
    }

    /**
     * @param f
     *        the array to hold the factoradic
     * @param i
     *        long
     */
    public final void factoradicNK(final int[] f, final long i) {
        long m = i;
        int z = 1;
        int j = k - 1;
        while (j >= 0) {
            m /= z;
            z = n - j;
            f[j] = (int) (m % z);
            --j;
        }
    }

    /**
     * @param f
     *        the factoradic
     * @param a
     *        array of int
     */
    private static void factoradicToPermutation(final int[] f, final int[] a) {
        for (int i = 0; i < f.length; ++i) {
            if (f[i] != 0) {
                final int t = a[i + f[i]];
                // shift right
                System.arraycopy(a, i, a, i + 1, f[i]);
                // swap
                a[i] = t;
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

        private final long count = Permutations.count(n, k);

        private final int[] f = new int[k];

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
            factoradicNK(f, index);
            ArrayUtils.identityPermutation(a);
            factoradicToPermutation(f, a);
            ++index;
            System.arraycopy(a, 0, f, 0, k);
            return f;
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
