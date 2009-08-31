/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel. All rights reserved.
 *
 * Created Aug 27, 2009
 */
package jcombinatorics.permutations;

import static java.util.Arrays.sort;
import jcombinatorics.util.MathUtils;

/**
 * P(n, k) generator in lexicographical order.
 *
 * @author Alistair A. Israel
 * @since 0.1
 */
public class SepaNKPermutationsGenerator implements Iterable<int[]> {

    private final int n;

    private final int k;

    private final int edge;

    /**
     * @param n
     *        the number of elements
     * @param k
     *        taken k at a time
     */
    public SepaNKPermutationsGenerator(final int n, final int k) {
        if (n < 1) {
            throw new IllegalArgumentException("Need at least 1 element!");
        }
        if (n < k) {
            throw new IllegalArgumentException("k must be less than or equal to n!");
        }
        this.n = n;
        this.k = k;

        // pre-optimization
        if (k == n) {
            edge = n - 2;
        } else {
            edge = k - 1;
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

        private boolean hasNext = true;

        private final int[] a = new int[n];

        private final int[] result = new int[k];

        /**
         *
         */
        public Iterator() {
            MathUtils.identityPermutation(a);
        }

        /**
         * {@inheritDoc}
         *
         * @see java.util.Iterator#hasNext()
         */
        public boolean hasNext() {
            return hasNext;
        }

        /**
         * {@inheritDoc}
         *
         * @see java.util.Iterator#next()
         */
        public int[] next() {
            System.arraycopy(a, 0, result, 0, k);
            if (hasNext) {
                computeNext();
            }
            return result;
        }

        /**
         *
         */
        private void computeNext() {
            if (a[edge] < a[edge + 1]) {
                // rotate left
                final int last = n - 1;
                final int t = a[edge];
                for (int j = edge; j < last; ++j) {
                    a[j] = a[j + 1];
                }
                a[last] = t;
            } else {
                final int i = findi();
                if (i == 0 && a[i] > a[i + 1]) {
                    hasNext = false;
                    return;
                }

                // find next 0..n in a[i + 1, n]
                final int j = findj(i);
                swap(i, j);
                sort(a, i + 1, n);
            }
        }

        /**
         * Find rightmost i where a[i] > a[i+1]
         *
         * @return i
         */
        private int findi() {
            int i = edge;
            while (i > 0 && a[i] >= a[i + 1]) {
                --i;
            }
            return i;
        }

        /**
         * Find j > i where a[i] is smallest but > a[i]
         *
         * @param i
         *        start at i + 1
         * @return j
         */
        private int findj(final int i) {
            final int current = a[i];
            int minToTheRight = n;
            int indexOfMin = i;
            for (int j = i + 1; j < n; ++j) {
                if (a[j] < minToTheRight && a[j] > current) {
                    minToTheRight = a[j];
                    indexOfMin = j;
                }
            }
            return indexOfMin;
        }

        /**
         * @param x
         *        first position
         * @param y
         *        second position
         */
        private void swap(final int x, final int y) {
            final int t = a[x];
            a[x] = a[y];
            a[y] = t;
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