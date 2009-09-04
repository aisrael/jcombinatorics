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
import jcombinatorics.util.RemoveNotSupported;

/**
 * P(n, k) generator in lexicographical order.
 *
 * @author Alistair A. Israel
 * @see <a
 *      href="http://en.wikipedia.org/wiki/Factoradic">http://en.wikipedia.org/wiki/Factoradic</a>
 * @since 0.1
 */
public class FactoradicNKPermutationsGenerator implements Iterable<int[]> {

    private final int n;

    private final int k;

    private final int[] identity;

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
        this.identity = ArrayUtils.identityPermutation(n);
    }

    /**
     * Retrieve the <i>l</i>-th permutation.
     *
     * @param l
     *        long
     * @return int[]
     */
    public final int[] get(final long l) {
        final int[] f = new int[k];
        final int[] a = new int[n];
        factoradicNK(f, l);
        System.arraycopy(identity, 0, a, 0, n);
        Factoradic.factoradicToPermutation(f, a);
        System.arraycopy(a, 0, f, 0, k);
        return f;
    }

    /**
     * Truncated factoradic. Only computes up to <code>k</code> digits.
     *
     * @param f
     *        the array to hold the factoradic
     * @param l
     *        long
     */
    public final void factoradicNK(final int[] f, final long l) {
        long m = l;
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
            System.arraycopy(identity, 0, a, 0, n);
            Factoradic.factoradicToPermutation(f, a);
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
            throw new RemoveNotSupported();
        }

    }

}
