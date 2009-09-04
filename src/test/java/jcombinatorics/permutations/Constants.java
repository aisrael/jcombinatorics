/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Sep 4, 2009
 */
package jcombinatorics.permutations;

/**
 * @author Alistair A. Israel
 */
public final class Constants {

    /**
     * Permutations of { "a", "bb", "ccc" }.
     */
    public static final String[][] PERMUTATIONS_OF_A_BB_CC = { { "a", "bb", "ccc" }, { "a", "ccc", "bb" },
            { "bb", "a", "ccc" }, { "bb", "ccc", "a" }, { "ccc", "a", "bb" }, { "ccc", "bb", "a" } };

    /**
     * Utility classes should not have a public or default constructor.
     */
    private Constants() {
        // noop
    }
}
