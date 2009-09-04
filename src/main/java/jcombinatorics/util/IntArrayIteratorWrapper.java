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

import java.util.Iterator;

/**
 * @param <T>
 *        a type
 * @author Alistair A. Israel
 */
public class IntArrayIteratorWrapper<T> extends ReadOnlyIterator<Iterable<T>> {

    private final T[] elements;

    private final Iterator<int[]> iterator;

    /**
     * @param elements
     *        the elements to iterate over
     * @param iterator
     *        the {@link Iterator}&lt;int[]&gt; to wrap
     */
    public IntArrayIteratorWrapper(final T[] elements, final Iterator<int[]> iterator) {
        this.elements = elements;
        this.iterator = iterator;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.util.Iterator#hasNext()
     */
    public final boolean hasNext() {
        return iterator.hasNext();
    }

    /**
     * {@inheritDoc}
     *
     * @see java.util.Iterator#next()
     */
    public final Iterable<T> next() {
        final int[] indices = iterator.next();
        return new Iterable<T>() {

            public Iterator<T> iterator() {
                return new ValuesAtIterator<T>(elements, indices);
            }
        };
    }

}
