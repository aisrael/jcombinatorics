/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Aug 30, 2009
 */
package jcombinatorics.combinations;

import java.util.Iterator;

import jcombinatorics.util.ArrayUtils;
import jcombinatorics.util.ReadOnlyIterator;

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
     * An inner class to implement the <code>Combinations.choose(n, k)</code>
     * DSL.
     *
     * @author Alistair A. Israel
     */
    public static final class Chooser implements Iterable<int[]> {

        /**
         *
         */
        private final int k;

        /**
         *
         */
        private final int n;

        private final CombinadicCombinationsGenerator generator;

        /**
         * @param n
         *        the number of elements
         * @param k
         *        taken k at a time
         */
        private Chooser(final int n, final int k) {
            this.n = n;
            this.k = k;
            this.generator = new CombinadicCombinationsGenerator(n, k);
        }

        /**
         * {@inheritDoc}
         *
         * @see java.lang.Iterable#iterator()
         */
        public Iterator<int[]> iterator() {
            return new RosenIterator(n, k);
        }

        /**
         * Retrieve the <i>i</i>-th combination.
         *
         * @param l
         *        long
         * @return int[]
         */
        public int[] get(final long l) {
            return generator.get(l);
        }
    }

    /**
     * @param n
     *        the number of elements
     * @param k
     *        taken k at a time
     * @return {@link Iterable}&lt;int[]&gt;
     */
    public static Chooser choose(final int n, final int k) {
        return new Chooser(n, k);
    }

    /**
     * @param <T>
     *        a type
     * @param t
     *        the elements to choose from
     * @return a {@link Of}
     */
    public static <T> Of<T> of(final T... t) {
        return new Of<T>(t);
    }

    /**
     * A builder/helper to implement the <code>Combinations.of(T...)</code> DSL
     * method.
     *
     * @author Alistair A. Israel
     */
    public static class Of<T> {

        private final T[] elements;

        /**
         * @param elements
         *        the element set (array)
         */
        public Of(final T[] elements) {
            this.elements = elements;
        }

        /**
         * @param k
         *        take <code>k</code> at a time
         * @return {@link ParameterizedGenerator}&lt;T&gt;
         */
        public final Iterable<T[]> take(final int k) {
            return new ParameterizedGenerator<T>(elements, k);
        }
    }

    /**
     * A parameterized combinations generator.
     *
     * @author Alistair A. Israel
     */
    public static class ParameterizedGenerator<T> implements Iterable<T[]> {

        private final T[] elements;

        private final int k;

        /**
         * @param elements
         *        the element set (array)
         * @param k
         *        take <code>k</code> at a time
         */
        public ParameterizedGenerator(final T[] elements, final int k) {
            this.elements = elements;
            this.k = k;
        }

        /**
         * {@inheritDoc}
         *
         * @see java.lang.Iterable#iterator()
         */
        public final Iterator<T[]> iterator() {
            return new RosenIteratorWrapper();
        }

        /**
         *
         * @author Alistair A. Israel
         */
        private final class RosenIteratorWrapper extends ReadOnlyIterator<T[]> {

            private final RosenIterator iterator = new RosenIterator(elements.length, k);

            /**
             * {@inheritDoc}
             *
             * @see Iterator#hasNext()
             */
            public boolean hasNext() {
                return iterator.hasNext();
            }

            /**
             * {@inheritDoc}
             *
             * @see Iterator#next()
             */
            public T[] next() {
                final int[] indices = iterator.next();
                return ArrayUtils.valuesAt(elements, indices);
            }
        }
    }
}
