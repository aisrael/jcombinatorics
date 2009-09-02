/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Aug 27, 2009
 */
package jcombinatorics.permutations;


/**
 * P(n, k) generator in lexicographical order.
 *
 * @author Alistair A. Israel
 * @since 0.1
 */
public class SepaNKPermutationsGenerator implements Iterable<int[]> {

    private final int n;

    private final int k;

    /**
     * @param n
     *        the number of elements
     * @param k
     *        taken k at a time
     */
    public SepaNKPermutationsGenerator(final int n, final int k) {
        if (n < 1) {
            throw new IllegalArgumentException("Need at least 1 element!");
        }
        if (n < k || k < 0) {
            throw new IllegalArgumentException("0 < k <= n!");
        }
        this.n = n;
        this.k = k;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Iterable#iterator()
     */
    public final java.util.Iterator<int[]> iterator() {
        return new SepaPnkIterator(n, k);
    }

}