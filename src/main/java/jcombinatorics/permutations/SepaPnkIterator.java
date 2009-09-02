/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel. All rights reserved.
 *
 * Created Sep 2, 2009
 */
package jcombinatorics.permutations;

import jcombinatorics.util.ArrayUtils;

/**
 *
 * @author Alistair A. Israel
 */
public class SepaPnkIterator implements java.util.Iterator<int[]> {

    private boolean hasNext = true;

    private final int n;

    private final int k;

    private final int[] a;

    private final int[] result;

    private final int edge;

    /**
     * @param n
     *        the number of elements
     * @param k
     *        taken k at a time
     */
    public SepaPnkIterator(final int n, final int k) {
        if (n < 1) {
            throw new IllegalArgumentException("Need at least 1 element!");
        }
        if (n < k || k < 0) {
            throw new IllegalArgumentException("0 < k <= n!");
        }
        this.n = n;
        this.k = k;
        a = ArrayUtils.identityPermutation(n);
        result = new int[k];
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
     * @see java.util.Iterator#hasNext()
     */
    public final boolean hasNext() {
        return hasNext;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.util.Iterator#next()
     */
    public final int[] next() {
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
            final int t = a[edge];
            for (int j = edge; j < n - 1; ++j) {
                a[j] = a[j + 1];
            }
            a[n - 1] = t;
        } else {
            final int i = findi();
            if (i == 0 && a[i] > a[i + 1]) {
                hasNext = false;
                return;
            }

            // alternately, swap first then sort
            reverseRightOf(edge);
            reverseRightOf(i);
            // find next 0..n in a[i + 1, n]
            final int j = findj(i);
            swap(i, j);
        }
    }

    /**
     * @param start
     *        int
     */
    private void reverseRightOf(final int start) {
        int i = start + 1;
        int j = n - 1;
        while (i < j) {
            swap(i, j);
            ++i;
            --j;
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
    public final void remove() {
        throw new UnsupportedOperationException("remove() not supported!");
    }

    /**
     *
     * @author Alistair A. Israel
     */
    public static class Factory implements Iterable<int[]> {

        private final int n;

        private final int k;

        /**
         * @param n
         *        the number of elements
         * @param k
         *        taken k at a time
         */
        public Factory(final int n, final int k) {
            if (n < 1) {
                throw new IllegalArgumentException("Need at least 1 element!");
            }
            if (n < k || k < 0) {
                throw new IllegalArgumentException("0 < k <= n!");
            }
            this.n = n;
            this.k = k;
        }

        /**
         * {@inheritDoc}
         *
         * @see java.lang.Iterable#iterator()
         */
        public final java.util.Iterator<int[]> iterator() {
            return new SepaPnkIterator(n, k);
        }

    }
}