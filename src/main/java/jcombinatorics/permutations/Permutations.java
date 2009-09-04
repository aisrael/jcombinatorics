/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Aug 24, 2009
 */
package jcombinatorics.permutations;

import java.util.Collection;
import java.util.Iterator;

import jcombinatorics.Generator;
import jcombinatorics.util.ArrayUtils;
import jcombinatorics.util.RemoveNotSupported;
import jcombinatorics.util.ValuesAtIterator;

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
     * @param n
     *        the number of elements
     * @param k
     *        taken k at a time
     * @return {@link Generator}
     */
    public static Generator<int[]> permute(final int n, final int k) {
        return new Permutations.GeneratorImpl(n, k);
    }

    /**
     * An inner helper class to implement the
     * <code>Permutations.permute(n, k)</code> DSL.
     *
     * @author Alistair A. Israel
     */
    private static class GeneratorImpl implements Generator<int[]> {

        private final Iterable<int[]> iteratorFactory;

        private final Generator<int[]> factoradic;

        /**
         * @param n
         *        the number of elements
         * @param k
         *        taken k at a time
         */
        public GeneratorImpl(final int n, final int k) {
            if (k != n) {
                iteratorFactory = new SepaPnkIterator.Factory(n, k);
                factoradic = new FactoradicNKPermutationsGenerator(n, k);
            } else {
                iteratorFactory = new SepaPnIterator.Factory(n);
                factoradic = new FactoradicNPermutationsGenerator(n);
            }
        }

        /**
         * {@inheritDoc}
         *
         * @see java.lang.Iterable#iterator()
         */
        public final Iterator<int[]> iterator() {
            return iteratorFactory.iterator();
        }

        /**
         * Retrieve the <i>l</i>-th permutation.
         *
         * @param l
         *        long
         * @return int[]
         */
        public final int[] get(final long l) {
            return factoradic.get(l);
        }

    }

    /**
     * @param coll
     *        the collection of elements to permute
     * @return {@link Generator}&lt;{@link Iterable}&lt;T&gt;&gt;
     */
    public static <T> Generator<Iterable<T>> over(final Collection<T> coll) {
        return new TPermutator<T>(coll);
    }

    private static class TPermutator<T> implements Generator<Iterable<T>> {

        private final T[] elements;

        private final Iterable<int[]> iteratorFactory;

        private final Generator<int[]> factoradic;

        /**
         * @param coll
         *        the collection of elements
         */
        @SuppressWarnings("unchecked")
        public TPermutator(final Collection<T> coll) {
            elements = (T[]) coll.toArray();
            iteratorFactory = new SepaPnIterator.Factory(elements.length);
            factoradic = new FactoradicNPermutationsGenerator(elements.length);
        }

        /**
         * {@inheritDoc}
         *
         * @see jcombinatorics.Generator#get(long)
         */
        public Iterable<T> get(final long l) {
            final int[] indices = factoradic.get(l);
            return new Iterable<T>() {

                public Iterator<T> iterator() {
                    return new ValuesAtIterator(elements, indices);
                }
            };
        }

        /**
         * {@inheritDoc}
         *
         * @see java.lang.Iterable#iterator()
         */
        public Iterator<Iterable<T>> iterator() {
            return iteratorFactory.iterator();
        }

    }

    /**
     * @param elements
     *        int[]
     * @return {@link Iterable}&lt;int[]&gt;
     */
    public static Iterable<int[]> of(final int... elements) {
        if (elements.length > 12) {
            throw new IllegalArgumentException("Too many arguments (max 12)!");
        }
        return new IntPermutator(elements);
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
                final int[] indices = getNextIndices();
                return ArrayUtils.valuesAt(elements, indices);
            }
        }

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
         * Not supported. Throws {@link RemoveNotSupported}.
         */
        public final void remove() {
            throw new RemoveNotSupported();
        }

    }

}
