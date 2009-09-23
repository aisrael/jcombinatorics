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

import jcombinatorics.Generator;
import jcombinatorics.util.ArrayUtils;
import jcombinatorics.util.ReadOnlyIterator;

/**
 * P(n, k) generator in lexicographical order.
 *
 * @author Alistair A. Israel
 * @see <a
 *      href="http://en.wikipedia.org/wiki/Factoradic">http://en.wikipedia.org/wiki/Factoradic</a>
 * @since 0.1
 */
public class FactoradicPnkGenerator implements Generator<int[]>, Iterable<int[]> {

    private final int n;

    private final int k;

    private final long count;

    private final int[] identity;

    /**
     * @param n
     *        the number of elements
     * @param k
     *        taken k at a time
     */
    public FactoradicPnkGenerator(final int n, final int k) {
        assert n > 0;
        this.n = n;
        this.k = k;
        this.count = Permutations.count(n, k);
        this.identity = ArrayUtils.identityPermutation(n);
    }

    /**
     * {@inheritDoc}
     *
     * @see jcombinatorics.Generator#count()
     */
    public final long count() {
        return count;
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
        factoradicNK(f, l);

        final int[] a = new int[n];
        System.arraycopy(identity, 0, a, 0, n);
        applyFactoradic(f, a);

        return f;
    }

    /**
     * Apply factoradic f to permutation a, but store the result back in f
     *
     * @param f
     *        int[]
     * @param a
     *        int[]
     */
    private void applyFactoradic(final int[] f, final int[] a) {
        for (int i = 0; i < f.length; ++i) {
            final int skip = f[i];
            if (skip == 0) {
                f[i] = a[i];
            } else {
                f[i] = a[i + skip];
                // shift right
                System.arraycopy(a, i, a, i + 1, skip);
            }
        }
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
    private class Iterator extends ReadOnlyIterator<int[]> {

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
            applyFactoradic(f, a);
            ++index;
            return f;
        }

    }

}
