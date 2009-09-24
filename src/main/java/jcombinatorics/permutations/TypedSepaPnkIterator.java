/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Sep 24, 2009
 */
package jcombinatorics.permutations;

import java.util.Arrays;
import java.util.Iterator;

import jcombinatorics.util.ReadOnlyIterator;

/**
 * @param <T>
 *        a type that implements {@link Comparable}
 * @author Alistair A. Israel
 */
public class TypedSepaPnkIterator<T extends Comparable<T>> extends ReadOnlyIterator<T[]> {

    private final int n;

    private final int k;

    private final T[] a;

    private final T[] result;

    private boolean hasNext = true;

    /**
     * @param elements
     *        the number of elements to permute
     * @param k
     *        taken <code>k</code> at a time
     */
    public TypedSepaPnkIterator(final T[] elements, final int k) {
        if (elements == null) {
            throw new NullPointerException("elements cannot be null!");
        }
        n = elements.length;
        if (k < 0 || k > n) {
            throw new IllegalArgumentException("0 < k <= n!");
        }
        this.k = k;
        a = Arrays.copyOf(elements, n);
        Arrays.sort(a);
        result = Arrays.copyOf(a, k);
    }

    /**
     * {@inheritDoc}
     *
     * @see java.util.Iterator#hasNext()
     */
    public final boolean hasNext() {
        return hasNext;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.util.Iterator#next()
     */
    public final T[] next() {
        System.arraycopy(a, 0, result, 0, k);
        computeNext();
        return result;
    }

    /**
     *
     */
    private void computeNext() {
        int i = k - 1;
        int j = k;
        // find smallest j > k - 1 where a[j] >= a[k - 1]
        while (j < n && a[i].compareTo(a[j]) >= 0) {
            ++j;
        }
        if (j < n) {
            swap(i, j);
        } else {
            reverseRightOf(i);
            // i = (k - 1) - 1
            --i;
            while (i >= 0 && a[i].compareTo(a[i + 1]) >= 0) {
                --i;
            }
            if (i < 0) {
                hasNext = false;
                return;
            }
            // j = n - 1
            --j;
            while (j > i && a[i].compareTo(a[j]) >= 0) {
                --j;
            }
            swap(i, j);
            reverseRightOf(i);
        }
    }

    /**
     * Reverse the order of elements from <code>a[start + 1]..a[n-1]</code>.
     *
     * @param start
     *        the starting element
     */
    private void reverseRightOf(final int start) {
        int i = start + 1;
        int j = n - 1;
        while (i < j) {
            swap(i, j);
            ++i;
            --j;
        }
    }

    /**
     * @param x
     *        first position
     * @param y
     *        second position
     */
    private void swap(final int x, final int y) {
        final T t = a[x];
        a[x] = a[y];
        a[y] = t;
    }

    /**
     * @param <T>
     *        a type that implements {@link Comparable}
     * @param elements
     *        the of elements to permute
     * @param k
     *        taken <code>k</code> at a time
     * @return {@link Factory}
     */
    public static <T extends Comparable<T>> Iterable<T[]> permute(final T[] elements, final int k) {
        return new Factory<T>(elements, k);
    }

    /**
     *
     * @author Alistair A. Israel
     */
    public static class Factory<T extends Comparable<T>> implements Iterable<T[]> {

        private final T[] a;

        private final int k;

        /**
         * @param elements
         *        the of elements to permute
         * @param k
         *        taken <code>k</code> at a time
         */
        public Factory(final T[] elements, final int k) {
            if (elements == null) {
                throw new NullPointerException("elements cannot be null!");
            }
            final int n = elements.length;
            if (k < 0 || k > n) {
                throw new IllegalArgumentException("0 < k <= n!");
            }
            this.k = k;
            a = Arrays.copyOf(elements, n);
        }

        /**
         * {@inheritDoc}
         *
         * @see java.lang.Iterable#iterator()
         */
        public final Iterator<T[]> iterator() {
            return new TypedSepaPnkIterator<T>(a, k);
        }

    }

}
