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
package jcombinatorics.combinations;

import java.util.Iterator;

import jcombinatorics.Generator;
import jcombinatorics.util.ReadOnlyIterator;

/**
 * A combinations generator based on combinadics. Capable of computing the
 * <i>i</i>-th combination directly using {@link #get(long)}.
 *
 * @author Alistair A. Israel
 * @see <a
 *      href="http://en.wikipedia.org/wiki/Combinadic">http://en.wikipedia.org/wiki/Combinadic</a>
 * @see <a
 *      href="http://msdn.microsoft.com/en-us/library/aa289166%28VS.71%29.aspx">Generating
 *      the mth Lexicographical Element of a Mathematical Combination, James
 *      McCaffrey</a>
 */
public class CombinadicCombinationsGenerator implements Generator<int[]> {

    private final int n;

    private final int k;

    private final long count;

    /**
     * @param n
     *        the number of elements to choose from
     * @param k
     *        taken <code>k</code> at a time
     */
    public CombinadicCombinationsGenerator(final int n, final int k) {
        this.n = n;
        this.k = k;
        this.count = Combinations.count(n, k);
    }

    /**
     * Retrieve the <i>i</i>-th combination.
     *
     * @param l
     *        long
     * @return int[]
     */
    public final int[] get(final long l) {
        final int[] a = new int[k];

        int nn = n;
        long m = count(n, k) - l - 1;

        for (int i = 0; i < k; ++i) {
            final int kk = k - i;
            final int v = largestV(nn, kk, m);
            m -= count(v, kk);
            nn = v;
            a[i] = (n - 1) - v;
        }

        return a;
    }

    /**
     * @param n
     *        n
     * @param k
     *        k
     * @return C(n,k)
     */
    private static int count(final int n, final int k) {
        int count = 1;
        for (int i = 0; i < k; ++i) {
            count = count * (n - i) / (i + 1);
        }
        return count;
    }

    /**
     * @param nn
     *        int
     * @param kk
     *        int
     * @param m
     *        long
     * @return v
     */
    private static int largestV(final int nn, final int kk, final long m) {
        int x = nn - 1;
        while (count(x, kk) > m) {
            --x;
        }
        return x;
    }

    /**
     * @author Alistair A. Israel
     */
    private class IteratorImpl extends ReadOnlyIterator<int[]> {

        private long i;

        /**
         * {@inheritDoc}
         *
         * @see java.util.Iterator#hasNext()
         */
        public boolean hasNext() {
            return i < count;
        }

        /**
         * {@inheritDoc}
         *
         * @see java.util.Iterator#next()
         */
        public int[] next() {
            return get(i++);
        }

    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Iterable#iterator()
     */
    public final Iterator<int[]> iterator() {
        return new IteratorImpl();
    }

}
