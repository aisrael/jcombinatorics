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
public class ValuesAtIterator<T> implements Iterator<T> {

    private final T[] elements;

    private final int[] indices;

    private int i;

    /**
     * @param elements
     *        the elements to choose from
     * @param indices
     *        the order of the elements to return
     */
    public ValuesAtIterator(final T[] elements, final int[] indices) {
        this.elements = elements;
        this.indices = indices;
    }

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

    /**
     * Not supported. Throws {@link RemoveNotSupported}.
     */
    public final void remove() {
        throw new RemoveNotSupported();
    }

}
