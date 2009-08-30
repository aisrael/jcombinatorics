/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel. All rights reserved.
 *
 * Created Aug 24, 2009
 */
package jcombinatorics.permutations;

import java.util.Iterator;

/**
 * @author Alistair A. Israel
 * @since 0.1
 */
public final class Permutations {

    /**
     * Utility classes should not a public or default constructor.
     */
    private Permutations() {
        // noop
    }

    /**
     * Compute and return the number of permutations of <code>n</code> elements
     * taken <code>k</code> at a time, which can be computed as:
     * <code>n!/(n-k)!</code>
     *
     * @param n
     *        the number of elements
     * @param k
     *        subset/sample size
     * @return the number of permutations of <code>n</code> elements taken
     *         <code>k</code> at a time
     */
    public static long count(final int n, final int k) {
        if (k > n) {
            throw new IllegalArgumentException("n cannot be greater than k!");
        }
        long count = 1;
        for (int i = n - k + 1; i <= n; ++i) {
            count *= i;
        }
        return count;
    }

    /**
     * @param elements
     *        int[]
     * @return {@link Iterable}&lt;int[]&gt;
     */
    public static Iterable<int[]> over(final int... elements) {
        if (elements.length > 12) {
            throw new IllegalArgumentException("Too many arguments (max 12)!");
        }
        return new IntPermutator(elements);
    }

    /**
     * A base class for type-specific iterators, that just delegates to an
     * internal {@link Iterator} returned by a
     * {@link SepaNPermutationsGenerator}.
     *
     * @author Alistair A. Israel
     */
    private static class IteratorBase {

        private final Iterator<int[]> iter;

        /**
         * @param iter
         *        the internal iterator
         */
        public IteratorBase(final Iterator<int[]> iter) {
            this.iter = iter;
        }

        /**
         * {@inheritDoc}
         *
         * @see java.util.Iterator#hasNext()
         */
        public final boolean hasNext() {
            return iter.hasNext();
        }

        /**
         * @return int[]
         */
        protected final int[] getNextIndices() {
            return iter.next();
        }

        /**
         * {@inheritDoc}
         *
         * @see java.util.Iterator#remove()
         */
        public final void remove() {
            throw new UnsupportedOperationException();
        }

    }

    /**
     * A permutations generator for arrays of integers.
     *
     * @author Alistair A. Israel
     */
    private static class IntPermutator implements Iterable<int[]> {

        private final int[] elements;

        private final SepaNPermutationsGenerator generator;

        /**
         * @param elements
         *        the element set
         */
        public IntPermutator(final int[] elements) {
            this.elements = elements;
            this.generator = new SepaNPermutationsGenerator(elements.length);
        }

        /**
         * {@inheritDoc}
         *
         * @see java.lang.Iterable#iterator()
         */
        public java.util.Iterator<int[]> iterator() {
            return new IntIterator();
        }

        /**
         *
         * @author Alistair A. Israel
         */
        private final class IntIterator extends IteratorBase implements Iterator<int[]> {

            /**
             *
             */
            public IntIterator() {
                super(generator.iterator());
            }

            /**
             * {@inheritDoc}
             *
             * @see java.util.Iterator#next()
             */
            public int[] next() {
                final int n = elements.length;
                final int[] result = new int[n];
                final int[] ix = getNextIndices();
                for (int i = 0; i < n; ++i) {
                    result[i] = elements[ix[i]];
                }
                return result;
            }
        }

    }

}
