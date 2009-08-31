/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel. All rights reserved.
 *
 * Created Aug 30, 2009
 */
package jcombinatorics.combinations;

import static java.lang.String.format;
import static jcombinatorics.util.MathUtils.factorial;
import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.List;

import jcombinatorics.permutations.Permutations;

import org.junit.Test;

/**
 * JUnit test for {@link Combinations}.
 *
 * @author Alistair A. Israel
 */
public final class CombinationsTest {

    /**
     * Test for {@link Combinations#count(int, int)}.
     */
    @Test
    public void testCount() {
        assertEquals(2598960, Combinations.count(52, 5));
        for (int n = 2; n <= 7; ++n) {
            assertEquals(format("C(%d,%d)", n, n), 1, Combinations.count(n, n));
            for (int k = 1; k < n; ++k) {
                final long expected = Permutations.count(n, k) / factorial(k);
                final long actual = Combinations.count(n, k);
                assertEquals(format("C(%d,%d)", n, k), expected, actual);
            }
        }
    }

    /**
     *
     */
    @Test
    public void testChoose() {
        final String[] expected = { "abc", "abd", "abe", "acd", "ace", "ade", "bcd", "bce", "bde", "cde" };
        int count = 0;
        for (final List<String> combination : Combinations.choose("a", "b", "c", "d", "e").take(3)) {
            assertEquals(expected[count], concat(combination));
            ++count;
        }
        assertEquals(expected.length, count);
    }

    /**
     * @param <T>
     *        a type
     * @param coll
     *        a {@link Collection}
     * @return the collections elements, concatenated to a single string
     */
    private static <T> String concat(final Collection<T> coll) {
        final StringBuilder sb = new StringBuilder();
        for (final T t : coll) {
            sb.append(t);
        }
        return sb.toString();
    }
}
