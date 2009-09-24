/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Aug 31, 2009
 */
package jcombinatorics.util;

import java.util.Arrays;

/**
 * Provides a couple of static utility methods for creating and initializing
 * arrays used throughout permutation and combination generation.
 *
 * @author Alistair A. Israel
 */
public final class ArrayUtils {

    /**
     * Utility classes should not have a public or default constructor.
     */
    private ArrayUtils() {
        // noop
    }

    /**
     * Fills the given array with a[i] = i. For example, if a = int[4], then
     * fills <code>a</code> with <code>{ 0, 1, 2, 3 }</code>. Used throughout
     * permutation and combination generation as the first result
     * (lexicographically).
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
     * Creates and fills an array with a[i] = i. For example, if n = 4, then
     * returns <code>{ 0, 1, 2, 3 }</code>. Used throughout permutation and
     * combination generation as the first result (lexicographically).
     *
     * @param n
     *        the size of the array
     * @return the initialized array
     */
    public static int[] identityPermutation(final int n) {
        final int[] a = new int[n];
        identityPermutation(a);
        return a;
    }

    /**
     * @param elements
     *        the elements to choose from
     * @param indices
     *        the array of indices
     * @return the mapped array
     */
    public static char[] valuesAt(final char[] elements, final int[] indices) {
        final int n = indices.length;
        final char[] result = new char[n];
        for (int i = 0; i < n; ++i) {
            result[i] = elements[indices[i]];
        }
        return result;
    }

    /**
     * @param elements
     *        the elements to choose from
     * @param indices
     *        the array of indices
     * @return the mapped array
     */
    public static int[] valuesAt(final int[] elements, final int[] indices) {
        final int n = indices.length;
        final int[] result = new int[n];
        for (int i = 0; i < n; ++i) {
            result[i] = elements[indices[i]];
        }
        return result;
    }

    /**
     * @param <T>
     *        a type
     * @param elements
     *        the elements to choose from
     * @param indices
     *        the array of indices
     * @return the mapped array
     */
    public static <T> T[] valuesAt(final T[] elements, final int[] indices) {
        final int n = indices.length;
        final T[] result = Arrays.copyOf(elements, n);
        for (int i = 0; i < n; ++i) {
            result[i] = elements[indices[i]];
        }
        return result;
    }

}
