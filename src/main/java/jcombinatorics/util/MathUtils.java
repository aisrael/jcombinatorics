/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel. All rights reserved.
 *
 * Created Aug 24, 2009
 */
package jcombinatorics.util;

import java.util.Arrays;

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

    private static final int MAX_N_FACTORIAL = 20;

    private static final long[] FACTORIALS = new long[MAX_N_FACTORIAL + 1];
    static {
        FACTORIALS[0] = 1;
        for (int i = 1; i <= MAX_N_FACTORIAL; ++i) {
            FACTORIALS[i] = FACTORIALS[i - 1] * i;
        }
    }

    /**
     * @param n
     *        integer <= 20 (21! > {@link Long#MAX_VALUE}).
     * @return n!
     */
    public static long factorial(final int n) {
        if (n > MAX_N_FACTORIAL) {
            throw new IllegalArgumentException("long only supports up to 20!");
        }
        return FACTORIALS[n];
    }

    /**
     * {@link Integer#MAX_VALUE} can only occupy 13 places in factoradic base (
     * 13! > {@link Integer#MAX_VALUE}).
     */
    private static final int MAX_FACTORADIC_N = 13;

    /**
     * Return the representation of the given integer in factoradic base.
     *
     * @param n
     *        the number
     * @return the factoradic
     * @see <a
     *      href="http://en.wikipedia.org/wiki/Factoradic">http://en.wikipedia.org/wiki/Factoradic</a>
     */
    public static int[] factoradic(final int n) {
        if (n < 1) {
            if (n < 0) {
                throw new IllegalArgumentException("n must be greater than 0!");
            }
            return new int[] { 0 };
        }
        final int[] f = new int[MAX_FACTORADIC_N];
        int m = n;
        int z = 0;
        while (m > 0) {
            ++z;
            final int r = m % z;
            f[MAX_FACTORADIC_N - z] = r;
            m /= z;
        }

        // pack
        final int[] result = new int[z];
        System.arraycopy(f, MAX_FACTORADIC_N - z, result, 0, z);
        return result;
    }

    /**
     * Compute the factoradic representation of the given integer into the given
     * array.
     *
     * @param a
     *        the array to hold the factoradic
     * @param n
     *        the number
     * @return the index (from the right) of the most significant digit
     * @see <a
     *      href="http://en.wikipedia.org/wiki/Factoradic">http://en.wikipedia.org/wiki/Factoradic</a>
     */
    public static int factoradic(final int[] a, final int n) {
        Arrays.fill(a, 0);
        final int len = a.length;
        int m = n;
        int z = 0;
        while (m > 0) {
            ++z;
            a[len - z] = m % z;
            m /= z;
        }
        return z;
    }
}
