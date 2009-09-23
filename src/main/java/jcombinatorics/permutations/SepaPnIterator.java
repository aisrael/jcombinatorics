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

import jcombinatorics.util.ReadOnlyIterator;

/**
 * <p>
 * SEPA ("Simple, Efficient Permutation Algorithm") iterator that enumerates all
 * permutations <code>P(n)</code> in lexicographical order.
 * </p>
 *
 * @author Alistair A. Israel
 * @see <a href="http://www.freewebz.com/permute/soda_submit.html">SEPA: A
 *      Simple, Efficient Permutation Algorithm (Jeffrey A. Johnson)</a>
 * @since 0.1
 */
public class SepaPnIterator extends ReadOnlyIterator<int[]> {

    private final int n;

    private int[] a;

    private int ascent;

    /**
     * @param n
     *        the number of elements to permute
     */
    public SepaPnIterator(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n < 0");
        }
        this.n = n;
        ascent = n - 1 - 1;
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
        return ascent >= 0;
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
        // find rightmost ascent, or i where a[i + 1] > a[i]
        int i = ascent;
        // find smallest (rightmost) a[j] where a[j] > a[i]
        int j = n - 1;
        while (j > i && a[j] <= a[i]) {
            --j;
        }

        swap(i, j);

        // reverse to right of i (sort to ascending order)
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
        ascent = i;
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
     * @param n
     *        the number of elements to permute
     * @return {@link Iterable}&lt;int[]&gt;
     */
    public static Iterable<int[]> permute(final int n) {
        return new Factory(n);
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
