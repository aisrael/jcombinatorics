/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Sep 22, 2009
 */
package jcombinatorics.util;

import static java.lang.System.arraycopy;

import java.util.Arrays;

/**
 * Convenience class for computing the factoradic representation of a (
 * <code>long</code>) number.
 *
 * @author Alistair A. Israel
 * @see <a
 *      href="http://en.wikipedia.org/wiki/Factoradic">http://en.wikipedia.org/wiki/Factoradic</a>
 */
public class Factoradic {

    /**
     * <code>long</code> can only hold up to {@value #MAX_N}!
     */
    public static final int MAX_N = 20;

    /**
     * Maximum length on an array to hold a factoradic of a long value,
     * equivalent to {@link #MAX_N} + 1.
     */
    private static final int MAX_LEN = MAX_N + 1;

    private final int[] factoradic;

    /**
     * @param n
     *        long
     */
    public Factoradic(final long n) {
        if (n < 1) {
            if (n < 0) {
                throw new IllegalArgumentException("n must be greater than 0!");
            }
            factoradic = new int[] { 0 };
        } else {
            final int[] f = new int[MAX_LEN];
            final int len = f.length;
            long m = n;
            int z = 0;
            while (m > 0 && z < len) {
                ++z;
                f[len - z] = (int) (m % z);
                m /= z;
            }
            // pack
            factoradic = new int[z];
            arraycopy(f, MAX_LEN - z, factoradic, 0, z);
        }
    }

    /**
     * @param f
     *        int[]
     */
    public Factoradic(final int[] f) {
        validate(f);
        factoradic = new int[f.length];
        arraycopy(f, 0, factoradic, 0, f.length);
    }

    /**
     * @param f
     *        the int[] to validate
     */
    private static void validate(final int[] f) {
        final int len = f.length;
        if (len > MAX_LEN) {
            throw new IllegalArgumentException("Array " + Arrays.toString(f)
                    + " has too many elements (max " + MAX_LEN + ")!");
        }
        for (int x = 0, i = len - 1; x < len; ++x, --i) {
            final int d = f[i];
            if (d > x) {
                throw new IllegalArgumentException("Illegal factoradic digit (" + d + ") at index " + i
                        + "!");
            }
        }
    }

    /**
     * @return long value
     */
    public final long longValue() {
        long result = 0;
        long f = 1;
        final int len = factoradic.length - 1;
        for (int i = 1; i <= len; ++i) {
            final int d = factoradic[len - i];
            f *= i;
            result += f * d;
        }
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Object#toString()
     */
    // CHECKSTYLE:OFF
    @Override
    public String toString() {
        // CHECKSTYLE:ON
        return Arrays.toString(factoradic);
    }
}
