/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel. All rights reserved.
 *
 * Created Aug 30, 2009
 */
package jcombinatorics.combinations;

import java.util.Iterator;

import jcombinatorics.util.ArrayUtils;

/**
 * A utility class that provides convenience methods for generating and working
 * with combinations.
 *
 * @author Alistair A. Israel
 * @see <a
 *      href="http://en.wikipedia.org/wiki/Combination">http://en.wikipedia.org/wiki/Combination</a>
 * @since 0.1
 */
public final class Combinations {

    /**
     * Utility classes should not have a public or default constructor.
     */
    private Combinations() {
        // noop
    }

    /**
     * Computes the number of unique combinations of <code>n</code> elements
     * taken <code>k</code> at a time, which can be computed as:
     * <code>n! / k! (n - k)!</code>
     *
     * @param n
     *        the number of elements
     * @param k
     *        subset/sample size
     * @return the number of combinations of <code>n</code> elements taken
     *         <code>k</code> at a time
     */
    public static long count(final int n, final int k) {
        if (k < 0 || k > n) {
            throw new IllegalArgumentException("0 <= k <= " + n + "!");
        }
        long count = 1;
        for (int i = 0; i < k; ++i) {
            count = count * (n - i) / (i + 1);
        }
        return count;
    }

    /**
     * @param n
     *        the number of elements
     * @param k
     *        taken k at a time
     * @return {@link Iterable}&lt;int[]&gt;
     */
    public static Iterable<int[]> choose(final int n, final int k) {
        return /**
         *
         * @author Alistair A. Israel
         */
        new Iterable<int[]>() {

            public Iterator<int[]> iterator() {
                return new RosenIterator(n, k);
            }
        };
    }

    /**
     * @param <T>
     *        a type
     * @param t
     *        the elements to choose from
     * @return a {@link Builder}
     */
    public static <T> Builder<T> choose(final T... t) {
        return new Builder<T>(t);
    }

    /**
     * A builder/helper to implement the choose(T...) DSL method.
     *
     * @author Alistair A. Israel
     */
    public static class Builder<T> {

        private final T[] elements;

        /**
         * @param elements
         *        the element set (array)
         */
        public Builder(final T[] elements) {
            this.elements = elements;
        }

        /**
         * @param k
         *        take <code>k</code> at a time
         * @return {@link Generator}&lt;T&gt;
         */
        public final Iterable<T[]> take(final int k) {
            return new Generator<T>(elements, k);
        }
    }

    /**
     * A parameterized combinations generator.
     *
     * @author Alistair A. Israel
     */
    public static class Generator<T> implements Iterable<T[]> {

        private final T[] elements;

        private final int k;

        /**
         * @param elements
         *        the element set (array)
         * @param k
         *        take <code>k</code> at a time
         */
        public Generator(final T[] elements, final int k) {
            this.elements = elements;
            this.k = k;
        }

        /**
         * {@inheritDoc}
         *
         * @see java.lang.Iterable#iterator()
         */
        public final java.util.Iterator<T[]> iterator() {
            return new Iterator();
        }

        /**
         *
         * @author Alistair A. Israel
         */
        private final class Iterator implements java.util.Iterator<T[]> {

            private final RosenIterator iterator = new RosenIterator(elements.length, k);

            /**
             * {@inheritDoc}
             *
             * @see java.util.Iterator#hasNext()
             */
            public boolean hasNext() {
                return iterator.hasNext();
            }

            /**
             * {@inheritDoc}
             *
             * @see java.util.Iterator#next()
             */
            public T[] next() {
                final int[] indices = iterator.next();
                return ArrayUtils.valuesAt(elements, indices);
            }

            /**
             * {@inheritDoc}
             *
             * @see java.util.Iterator#remove()
             */
            public void remove() {
                throw new UnsupportedOperationException("remove() not supported");
            }
        }
    }
}
