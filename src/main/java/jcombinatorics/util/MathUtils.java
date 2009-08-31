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

    /**
     * Fills the given array with a[i] = i, for example, if a.length = 4, then
     * fills a with { 0, 1, 2, 3 }. Used throughout permutation and combination
     * generation as the first result (lexicographically).
     *
     * @param a
     *        an array
     */
    public static void identityPermutation(final int[] a) {
        for (int i = a.length - 1; i >= 0; --i) {
            a[i] = i;
        }
    }

    /**
     * @param n
     *        integer <= 21 (21! > {@link Long#MAX_VALUE}).
     * @return n!
     */
    public static long factorial(final int n) {
        assert n < 21;
        long f = 1;
        for (int i = 1; i <= n; ++i) {
            f *= i;
        }
        return f;
    }

    /**
     * {@link Integer#MAX_VALUE} can only occupy 13 places in factoradic base (
     * 13! > {@link Integer#MAX_VALUE}).
     */
    private static final int MAX_INT_FACTORADIC = 13;

    /**
     * Return the representation of the given integer in factoradic base.
     *
     * @param n
     *        the number
     * @return the factoradic
     * @see http://en.wikipedia.org/wiki/Factoradic
     */
    public static int[] factoradic(final int n) {
        if (n < 1) {
            if (n < 0) {
                throw new IllegalArgumentException("n must be greater than 0!");
            }
            return new int[] { 0 };
        }
        final int[] f = new int[MAX_INT_FACTORADIC];
        int m = n;
        int z = 0;
        while (m > 0) {
            ++z;
            final int r = m % z;
            f[MAX_INT_FACTORADIC - z] = r;
            m /= z;
        }

        // pack
        final int[] result = new int[z];
        System.arraycopy(f, MAX_INT_FACTORADIC - z, result, 0, z);
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
     * @see http://en.wikipedia.org/wiki/Factoradic
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

    /**
     * Return the largest integer out of a set of integers.
     *
     * @param numbers
     *        the numbers
     * @return the largest integer
     */
    public static int max(final int... numbers) {
        int max = numbers[0];
        final int n = numbers.length;
        if (n > 1) {
            for (int i = 1; i < n; ++i) {
                max = Math.max(max, numbers[i]);
            }
        }
        return max;
    }

    /**
     * Compute the greatest common divisor or GCD of two integers
     *
     * @param a
     *        an int
     * @param b
     *        an int
     * @return the greatest common divisor of <code>a</code> and <code>b</code>
     */
    public static int gcd(final int a, final int b) {
        int x = a;
        int y = b;
        while (y != 0) {
            final int t = y;
            y = x % y;
            x = t;
        }
        return x;
    }

    /**
     * Compute the Least Common Multiple or LCM of a pair of integers.
     *
     * @param a
     *        an int
     * @param b
     *        an int
     * @return (a / GCD(a, b) * b;
     */
    public static int lcm(final int a, final int b) {
        return (a / gcd(a, b)) * b;
    }

    /**
     * Compute the least common multiple or LCM of a set of integers.
     *
     * @param numbers
     *        the numbers
     * @return the least common multiple
     */
    public static int lcm(final int... numbers) {
        switch (numbers.length) {
        case 1:
            return numbers[0];
        case 2:
            return lcm(numbers[0], numbers[1]);
        default:
            return lcma(numbers);
        }
    }

    /**
     * Compute the LCM of an array of integers. Relies on the fact that LCM(a,
     * b, c) = LCM(LCM(a, b), c), with a minor optimizations along the way.
     *
     * @param numbers
     *        an array of ints
     * @return LCM of the numbers
     */
    private static int lcma(final int[] numbers) {
        int l = numbers[0];
        if (l == 0) {
            return 0;
        }
        final int n = numbers.length;
        if (n > 1) {
            for (int i = 1; i < n; ++i) {
                final int x = numbers[i];
                if (x == 0) {
                    return 0;
                }
                if (l > x) {
                    if (l % x == 0) {
                        continue;
                    }
                } else {
                    if (x % l == 0) {
                        l = x;
                        continue;
                    }
                }
                l = lcm(l, x);
            }
        }
        return l;
    }
}
