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
     * P(4)
     */
    public static final int[][] P_4 = { { 0, 1, 2, 3 }, { 0, 1, 3, 2 }, { 0, 2, 1, 3 }, { 0, 2, 3, 1 },
            { 0, 3, 1, 2 }, { 0, 3, 2, 1 }, { 1, 0, 2, 3 }, { 1, 0, 3, 2 }, { 1, 2, 0, 3 }, { 1, 2, 3, 0 },
            { 1, 3, 0, 2 }, { 1, 3, 2, 0 }, { 2, 0, 1, 3 }, { 2, 0, 3, 1 }, { 2, 1, 0, 3 }, { 2, 1, 3, 0 },
            { 2, 3, 0, 1 }, { 2, 3, 1, 0 }, { 3, 0, 1, 2 }, { 3, 0, 2, 1 }, { 3, 1, 0, 2 }, { 3, 1, 2, 0 },
            { 3, 2, 0, 1 }, { 3, 2, 1, 0 } };

    /**
     * P(5,3)
     */
    public static final int[][] P_5_3 = { { 0, 1, 2 }, { 0, 1, 3 }, { 0, 1, 4 }, { 0, 2, 1 }, { 0, 2, 3 },
            { 0, 2, 4 }, { 0, 3, 1 }, { 0, 3, 2 }, { 0, 3, 4 }, { 0, 4, 1 }, { 0, 4, 2 }, { 0, 4, 3 },
            { 1, 0, 2 }, { 1, 0, 3 }, { 1, 0, 4 }, { 1, 2, 0 }, { 1, 2, 3 }, { 1, 2, 4 }, { 1, 3, 0 },
            { 1, 3, 2 }, { 1, 3, 4 }, { 1, 4, 0 }, { 1, 4, 2 }, { 1, 4, 3 }, { 2, 0, 1 }, { 2, 0, 3 },
            { 2, 0, 4 }, { 2, 1, 0 }, { 2, 1, 3 }, { 2, 1, 4 }, { 2, 3, 0 }, { 2, 3, 1 }, { 2, 3, 4 },
            { 2, 4, 0 }, { 2, 4, 1 }, { 2, 4, 3 }, { 3, 0, 1 }, { 3, 0, 2 }, { 3, 0, 4 }, { 3, 1, 0 },
            { 3, 1, 2 }, { 3, 1, 4 }, { 3, 2, 0 }, { 3, 2, 1 }, { 3, 2, 4 }, { 3, 4, 0 }, { 3, 4, 1 },
            { 3, 4, 2 }, { 4, 0, 1 }, { 4, 0, 2 }, { 4, 0, 3 }, { 4, 1, 0 }, { 4, 1, 2 }, { 4, 1, 3 },
            { 4, 2, 0 }, { 4, 2, 1 }, { 4, 2, 3 }, { 4, 3, 0 }, { 4, 3, 1 }, { 4, 3, 2 } };

    /**
     * Permutations of { "a", "bb", "ccc" }.
     */
    public static final String[][] P_A_BB_CC = { { "a", "bb", "ccc" }, { "a", "ccc", "bb" },
            { "bb", "a", "ccc" }, { "bb", "ccc", "a" }, { "ccc", "a", "bb" }, { "ccc", "bb", "a" } };

    /**
     * Utility classes should not have a public or default constructor.
     */
    private Constants() {
        // noop
    }
}
