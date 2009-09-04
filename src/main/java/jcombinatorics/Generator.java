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
 * @param <T>
 *        a type
 * @author Alistair A. Israel
 */
public interface Generator<T> extends Iterable<T> {

    /**
     * @param l
     *        long
     * @return T
     */
    T get(final long l);
}
