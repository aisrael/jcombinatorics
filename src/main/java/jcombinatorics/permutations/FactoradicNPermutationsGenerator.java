/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel. All rights reserved.
 *
 * Created Aug 27, 2009
 */
package jcombinatorics.permutations;

import jcombinatorics.util.MathUtils;

/**
 * P(n) generator in lexicographical order. Only supports n <= 12, since 13! >
 * {@link java.lang.Integer#MAX_VALUE}.
 *
 * @author Alistair A. Israel
 * @see http://en.wikipedia.org/wiki/Factoradic
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
    public final int[] get(final int i) {
        final int[] f = new int[n];
        final int[] a = new int[n];
        factoradic(f, i);
        MathUtils.identityPermutation(a);
        factoradicToPermutation(f, a);
        return a;
    }

    /**
     * @param f
     *        the array to hold the factoradic
     * @param i
     *        int
     */
    private static void factoradic(final int[] f, final int i) {
        final int len = f.length;
        int m = i;
        int z = 1;
        while (m > 0) {
            f[len - z] = m % z;
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

        private final int count = nFactorial();

        private final int[] f = new int[n];

        private final int[] a = new int[n];

        private int index;

        /**
         * @return n!
         */
        private int nFactorial() {
            int fact = 1;
            for (int i = 2; i <= n; ++i) {
                fact *= i;
            }
            return fact;
        }

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
            MathUtils.identityPermutation(a);
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