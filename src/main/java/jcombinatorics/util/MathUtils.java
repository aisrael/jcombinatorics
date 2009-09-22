/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Aug 24, 2009
 */
package jcombinatorics.util;

/**
 * Provides mathematical utility functions (static methods) not otherwise
 * available in {@link java.lang.Math}. Also, extends existing functions using
 * Java 5 idioms (such as varargs).
 *
 * @author Alistair A. Israel
 */
public final class MathUtils {

    /**
     * Utility classes should not have a public or default constructor.
     */
    private MathUtils() {
        // noop
    }

    /**
     * We only support up to 20! since 21! > {@link Long#MAX_VALUE}.
     */
    public static final int MAX_FACTORIAL_N = 21;

    /**
     * Pre-calculate factorials into a lookup table, trade O(n) space for O(1)
     * time. :D
     */
    private static final long[] FACTORIALS = new long[MAX_FACTORIAL_N];
    static {
        FACTORIALS[0] = 1;
        for (int i = 1; i < MAX_FACTORIAL_N; ++i) {
            FACTORIALS[i] = FACTORIALS[i - 1] * i;
        }
    }

    /**
     * Return <code>n!</code>, or
     * <code>n * (n - 1) * (n - 2) * ... * 3 * 2 * 1</code>. <code>0!</code> = 1
     *
     * @param n
     *        integer <= 20 (21! > {@link Long#MAX_VALUE}).
     * @return n!, or 1 if n == 0
     */
    public static long factorial(final int n) {
        if (!(n < MAX_FACTORIAL_N)) {
            throw new IllegalArgumentException("long only supports up to 20!");
        }
        return FACTORIALS[n];
    }

    /**
     * Return the representation of the given integer in factoradic base.
     *
     * @param n
     *        the number
     * @return the factoradic
     * @see <a
     *      href="http://en.wikipedia.org/wiki/Factoradic">http://en.wikipedia.org/wiki/Factoradic</a>
     */
    public static int[] factoradic(final long n) {
        if (n < 1) {
            if (n < 0) {
                throw new IllegalArgumentException("n must be greater than 0!");
            }
            return new int[] { 0 };
        }
        final int[] f = new int[MAX_FACTORIAL_N];
        final int z = factoradic(f, n);

        // pack
        final int[] result = new int[z];
        System.arraycopy(f, MAX_FACTORIAL_N - z, result, 0, z);
        return result;
    }

    /**
     * Compute the factoradic representation of the given (long) number into the
     * given array.
     *
     * @param a
     *        the array to hold the factoradic
     * @param n
     *        the number
     * @return the index (<code>z</code>) of the most significant digit
     * @see <a
     *      href="http://en.wikipedia.org/wiki/Factoradic">http://en.wikipedia.org/wiki/Factoradic</a>
     */
    public static int factoradic(final int[] a, final long n) {
        final int len = a.length;
        long m = n;
        int z = 0;
        while (m > 0 && z < len) {
            ++z;
            a[len - z] = (int) (m % z);
            m /= z;
        }
        return z;
    }
}
