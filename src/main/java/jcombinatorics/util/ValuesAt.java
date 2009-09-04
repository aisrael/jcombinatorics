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
package jcombinatorics.util;

/**
 * @param <T>
 *        a type
 * @author Alistair A. Israel
 */
public class ValuesAt<T> implements Iterable<T> {

    private final T[] elements;

    private final int[] indices;

    /**
     * @param elements
     *        the elements to choose from
     * @param indices
     *        the order of the elements to return
     */
    public ValuesAt(final T[] elements, final int[] indices) {
        this.elements = elements;
        this.indices = indices;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Iterable#iterator()
     */
    public final java.util.Iterator<T> iterator() {
        return new Iterator();
    }

    /**
     * @author Alistair A. Israel
     */
    public class Iterator extends ReadOnlyIterator<T> {

        private int i;

        /**
         * {@inheritDoc}
         *
         * @see java.util.Iterator#hasNext()
         */
        public final boolean hasNext() {
            return i < indices.length;
        }

        /**
         * {@inheritDoc}
         *
         * @see java.util.Iterator#next()
         */
        public final T next() {
            return elements[indices[i++]];
        }

    }
}
