/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Sep 4, 2009
 */
package jcombinatorics;

import java.util.Iterator;

import jcombinatorics.util.ReadOnlyIterator;

/**
 * A parameterized generator. Basically represents set of directly retrievable items addressed by a <code>long</code> index.
 *
 * @param <T>
 *            a type
 * @author Alistair A. Israel
 */
public interface Generator<T> {

    /**
     * Compute and return the <code>l</code>-th item
     *
     * @param l
     *            the index of the item to generate
     * @return T
     */
    T get(final long l);

    /**
     * Return the total number of items available for generation.
     *
     * @return the total number of items available for generation.
     */
    long count();

    /**
     * A base class for generators that also implements {@link Iterable} by returning a {@link ForwardIterator}.
     *
     * @param <T>
     *            a type
     * @author Alistair A. Israel
     */
    abstract class Of<T> implements Generator<T>, Iterable<T> {

        /**
         * {@inheritDoc}
         *
         * @see java.lang.Iterable#iterator()
         */
        public final Iterator<T> iterator() {
            return new ForwardIterator<T>(this);
        }

    }

    /**
     * An 'adapter' for {@link Generator}s acts as an {@link java.util.Iterator}.
     *
     * @param <T>
     *            a type
     * @author Alistair A. Israel
     */
    class ForwardIterator<T> extends ReadOnlyIterator<T> {

        private final Generator<T> generator;

        private long index;

        /**
         * @param generator
         *            the generator to use
         */
        public ForwardIterator(final Generator<T> generator) {
            this.generator = generator;
        }

        /**
         * {@inheritDoc}
         *
         * @see java.util.Iterator#hasNext()
         */
        public final boolean hasNext() {
            return index < generator.count();
        }

        /**
         * {@inheritDoc}
         *
         * @see java.util.Iterator#next()
         */
        public final T next() {
            return generator.get(index++);
        }

    }
}
