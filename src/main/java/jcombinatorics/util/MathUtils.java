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
 * Provides mathematical utility functions (static methods) not otherwise available in {@link java.lang.Math}. Also, extends existing
 * functions using Java 5 idioms (such as varargs).
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
     * We only support up to 20! since 21! &gt; {@link java.lang.Long#MAX_VALUE}.
     */
    public static final int MAX_FACTORIAL_N = 21;

    /**
     * Pre-calculate factorials into a lookup table, trade O(n) space for O(1) time. :D
     */
    private static final long[] FACTORIALS = new long[MAX_FACTORIAL_N];
    static {
        FACTORIALS[0] = 1;
        for (int i = 1; i < MAX_FACTORIAL_N; ++i) {
            FACTORIALS[i] = FACTORIALS[i - 1] * i;
        }
    }

    /**
     * Return <code>n!</code>, or <code>n * (n - 1) * (n - 2) * ... * 3 * 2 * 1</code>. <code>0!</code> = 1
     *
     * @param n
     *            integer &lt;= 20 (21! &gt; {@link java.lang.Long#MAX_VALUE}).
     * @return n!, or 1 if n == 0
     */
    public static long factorial(final int n) {
        if (!(n < MAX_FACTORIAL_N)) {
            throw new IllegalArgumentException("long only supports up to 20!");
        }
        return FACTORIALS[n];
    }
}
