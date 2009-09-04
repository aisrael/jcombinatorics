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

import java.util.Iterator;

import jcombinatorics.util.RemoveNotSupported;

/**
 * Iterator that enumerates permutations of <code>P(n)</code> in lexicographical
 * order. Only supports n <= 12, since 13! > {@link java.lang.Integer#MAX_VALUE}
 * .
 *
 * @author Alistair A. Israel
 * @see <a href="http://www.freewebz.com/permute/soda_submit.html">SEPA: A
 *      Simple, Efficient Permutation Algorithm (Jeffrey A. Johnson)</a>
 * @since 0.1
 */
public class SepaPnIterator implements Iterator<int[]> {

    private final int n;

    private int[] a;

    private boolean hasNext = true;

    /**
     * @param n
     *        the number of elements to permute
     */
    public SepaPnIterator(final int n) {
        if (!(n > 0 && n <= 12)) {
            throw new IllegalArgumentException("0 < n <= 12!");
        }
        this.n = n;
    }

    /**
     *
     */
    private void initialize() {
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i;
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
        if (a == null) {
            initialize();
        } else {
            computeNext();
        }
        return a;
    }

    /**
     *
     */
    private void computeNext() {
        // search for the rightmost i where a[i] > a[i + 1]
        int i = n - 2;
        while (i >= 0) {
            if (a[i + 1] < a[i]) {
                --i;
            } else {
                break;
            }
        }

        int j = n - 1;

        while (j > i && a[j] <= a[i]) {
            --j;
        }

        swap(i, j);

        // reverse the remaining elements (sort to ascending order)
        i++;
        j = n - 1;

        while (i < j) {
            swap(i, j);
            i++;
            j--;
        }

        i = n - 2;
        while (i >= 0 && a[i + 1] < a[i]) {
            --i;
        }
        if (i == -1) {
            // all in descending order
            hasNext = false;
            return;
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
     * {@inheritDoc}
     *
     * @see java.util.Iterator#remove()
     */
    public final void remove() {
        throw new RemoveNotSupported();
    }

    /**
     *
     * @author Alistair A. Israel
     */
    public static class Factory implements Iterable<int[]> {

        private final int n;

        /**
         * @param n
         *        the number of elements to permute
         */
        public Factory(final int n) {
            this.n = n;
        }

        /**
         * {@inheritDoc}
         *
         * @see java.lang.Iterable#iterator()
         */
        public final Iterator<int[]> iterator() {
            return new SepaPnIterator(n);
        }

    }

}
