/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel. All rights reserved.
 *
 * Created Aug 30, 2009
 */
package jcombinatorics.combinations;

/**
 * A utility class that provides convenience methods for generating and working
 * with combinations.
 *
 * @author Alistair A. Israel
 * @see http://en.wikipedia.org/wiki/Combination
 * @since 0.1
 */
public final class Combinations {

    /**
     * Utility classes should not have a public or default constructor.
     */
    private Combinations() {
        // noop
    }

    /**
     * Returns the number of unique combinations of <code>n</code> elements
     * taken <code>k</code> at a time, which can be computed as:
     * <code>n! / k! (n - k)!</code>
     *
     * @param n
     *        the number of elements
     * @param k
     *        subset/sample size
     * @return the number of combinations of <code>n</code> elements taken
     *         <code>k</code> at a time
     */
    public static long count(final int n, final int k) {
        long count = 1;
        for (int i = 0; i < k; ++i) {
            count = count * (n - i) / (i + 1);
        }
        return count;
    }
}
