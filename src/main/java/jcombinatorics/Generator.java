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

/**
 * A parameterized generator. Basically represents set of directly retrievable
 * items addressed by a <code>long</code> index.
 *
 * @param <T>
 *        a type
 * @author Alistair A. Israel
 */
public interface Generator<T> {

    /**
     * Compute and return the <code>l</code>-th item
     *
     * @param l
     *        the index of the item to generate
     * @return T
     */
    T get(final long l);

    /**
     * Return the total number of items available for generation.
     *
     * @return the total number of items available for generation.
     */
    long count();
}
