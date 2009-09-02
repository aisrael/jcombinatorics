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
 * P(n) generator in lexicographical order. Only supports n <= 12, since 13! >
 * {@link java.lang.Integer#MAX_VALUE}.
 *
 * @author Alistair A. Israel
 * @see <a href="http://www.freewebz.com/permute/soda_submit.html">SEPA: A
 *      Simple, Efficient Permutation Algorithm (Jeffrey A. Johnson)</a>
 * @since 0.1
 */
public class SepaNPermutationsGenerator implements Iterable<int[]> {

    private final int n;

    /**
     * @param n
     *        the number of elements
     */
    public SepaNPermutationsGenerator(final int n) {
        if (!(n > 0 && n <= 12)) {
            throw new IllegalArgumentException("0 < n <= 12!");
        }
        this.n = n;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Iterable#iterator()
     */
    public final java.util.Iterator<int[]> iterator() {
        return new SepaPnIterator(n);
    }

}