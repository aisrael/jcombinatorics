/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Sep 2, 2009
 */
package jcombinatorics.permutations;

import jcombinatorics.util.ArrayUtils;
import jcombinatorics.util.ReadOnlyIterator;

/**
 * <p>
 * An iterator that enumerates <code>P(n,k)</code>, or all permutations of
 * <code>n</code> taken <code>k</code> at a time in lexicographic order. Derived
 * from the SEPA P(n) iterator.
 * </p>
 *
 * @author Alistair A. Israel
 * @see SepaPnIterator
 */
public class SepaPnkIterator extends ReadOnlyIterator<int[]> {

    private boolean hasNext = true;

    private final int n;

    private final int k;

    private final int[] a;

    private final int[] result;

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
        computeNext();
        return result;
    }

    /**
     *
     */
    private void computeNext() {
        int i = k - 1;
        int j = k;
        while (j < n && a[j] < a[i]) {
            ++j;
        }
        if (j < n) {
            swap(i, j);
        } else {
            reverseRightOf(i);
            --i;
            while (i >= 0 && a[i] >= a[i + 1]) {
                --i;
            }
            if (i < 0) {
                hasNext = false;
                return;
            }
            --j;
            while (j > i && a[j] <= a[i]) {
                --j;
            }
            swap(i, j);
            reverseRightOf(i);
        }
    }

    /**
     * Reverse the order of elements from <code>a[start + 1]..a[n-1]</code>.
     *
     * @param start
     *        the starting element
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