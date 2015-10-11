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
 *            a type that implements {@link Comparable}
 * @author Alistair A. Israel
 */
public class TypedSepaPnIterator<T extends Comparable<T>> extends ReadOnlyIterator<T[]> {

    private T[] a;

    private final int n;

    private int ascent;

    /**
     * @param elements
     *            the number of elements to permute
     */
    public TypedSepaPnIterator(final T[] elements) {
        if (elements == null) {
            throw new NullPointerException("elements cannot be null!");
        }
        this.a = elements;
        Arrays.sort(elements);
        this.n = elements.length;
        ascent = n;
    }

    /**
     *
     */
    private void initialize() {
        int i = n - 2;
        while (i >= 0 && a[i + 1].compareTo(a[i]) <= 0) {
            --i;
        }
        ascent = i;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.util.Iterator#hasNext()
     */
    public final boolean hasNext() {
        return ascent >= 0;
    }

    /**
     * {@inheritDoc}
     *
     * @see java.util.Iterator#next()
     */
    public final T[] next() {
        if (ascent == n) {
            initialize();
        } else {
            computeNext();
        }
        return a;
    }

    /**
     *
     */
    private void computeNext() {
        // find rightmost ascent, or i where a[i + 1] > a[i]
        int i = ascent;
        // find smallest (rightmost) a[j] where a[j] > a[i]
        int j = n - 1;
        while (j > i && a[i].compareTo(a[j]) >= 0) {
            --j;
        }

        swap(i, j);

        // reverse to right of i (sort to ascending order)
        i++;
        j = n - 1;
        while (i < j) {
            swap(i, j);
            i++;
            j--;
        }

        i = n - 2;
        while (i >= 0 && a[i].compareTo(a[i + 1]) >= 0) {
            --i;
        }
        ascent = i;
    }

    /**
     * @param x
     *            first position
     * @param y
     *            second position
     */
    private void swap(final int x, final int y) {
        final T t = a[x];
        a[x] = a[y];
        a[y] = t;
    }

    /**
     * @param <T>
     *            a type that implements {@link Comparable}
     * @param elements
     *            the elements to permute
     * @return {@link Iterable}&lt;T[]&gt;
     */
    public static <T extends Comparable<T>> Iterable<T[]> permute(final T[] elements) {
        return new Factory<T>(elements);
    }

    /**
     *
     * @param <T>
     *            a type
     * @author Alistair A. Israel
     */
    public static class Factory<T extends Comparable<T>> implements Iterable<T[]> {

        private final T[] elements;

        /**
         * @param elements
         *            the elements to permute
         */
        public Factory(final T[] elements) {
            this.elements = elements;
        }

        /**
         * {@inheritDoc}
         *
         * @see java.lang.Iterable#iterator()
         */
        public final Iterator<T[]> iterator() {
            return new TypedSepaPnIterator<T>(elements);
        }

    }

}
