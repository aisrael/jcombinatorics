/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel. All rights reserved.
 *
 * Created Aug 27, 2009
 */
package jcombinatorics.benchmark;

/**
 *
 * @author Alistair A. Israel
 */
public interface NKGeneratorFactory {

    /**
     * @param n
     *        n
     * @param k
     *        k
     * @return P(n, k) generator
     */
    Iterable<int[]> generator(final int n, final int k);
}
