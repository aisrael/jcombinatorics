/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Sep 3, 2009
 */
package jcombinatorics.permutations;

/**
 * Factored out common <code>factoradicToPermutation()</code> method.
 *
 * @author Alistair A. Israel
 */
public final class Factoradic {

    /**
     *
     */
    private Factoradic() {
        // noop
    }

    /**
     * Generate a permutation from a factoradic. Assumes that the array
     * <code>a</code> is an identityPermutation.
     *
     * @param f
     *        the factoradic
     * @param a
     *        array to hold the permutation
     */
    public static void factoradicToPermutation(final int[] f, final int[] a) {
        for (int i = 0; i < f.length; ++i) {
            if (f[i] != 0) {
                final int t = a[i + f[i]];
                // shift right
                System.arraycopy(a, i, a, i + 1, f[i]);
                // swap
                a[i] = t;
            }
        }
    }

}
