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

import static java.lang.String.format;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

/**
 * @author Alistair A. Israel
 */
public final class TypedSepaPnkIteratorTest {

    private static final String[] ABBC = { "a", "b", "b", "c" };

    private static final String[][] P_ABBC = { { "a", "b", "b", "c" }, { "a", "b", "c", "b" },
            { "a", "c", "b", "b" }, { "b", "a", "b", "c" }, { "b", "a", "c", "b" }, { "b", "b", "a", "c" },
            { "b", "b", "c", "a" }, { "b", "c", "a", "b" }, { "b", "c", "b", "a" }, { "c", "a", "b", "b" },
            { "c", "b", "a", "b" }, { "c", "b", "b", "a" } };

    /**
     *
     */
    @Test
    public void testPermute() {
        final TypedSepaPnkIterator<String> iter = new TypedSepaPnkIterator<String>(ABBC, ABBC.length);
        int i = 0;
        while (iter.hasNext()) {
            final String[] actual = iter.next();
            final String[] expected = P_ABBC[i];
            assertArrayEquals(String.format("P(\"a\", \"b\", \"b\", \"c\")[i], expected %s, got %s", Arrays
                    .toString(expected), Arrays.toString(actual)), expected, actual);
            ++i;
        }
        assertEquals(P_ABBC.length, i);
    }

    private static final String[][] P_122333_3 = { { "1", "2", "2" }, { "1", "2", "3" }, { "1", "3", "2" },
            { "1", "3", "3" }, { "2", "1", "2" }, { "2", "1", "3" }, { "2", "2", "1" }, { "2", "2", "3" },
            { "2", "3", "1" }, { "2", "3", "2" }, { "2", "3", "3" }, { "3", "1", "2" }, { "3", "1", "3" },
            { "3", "2", "1" }, { "3", "2", "2" }, { "3", "2", "3" }, { "3", "3", "1" }, { "3", "3", "2" },
            { "3", "3", "3" } };

    /**
     *
     */
    @Test
    public void testGenerate122333() {
        final TypedSepaPnkIterator<String> iter = new TypedSepaPnkIterator<String>(new String[] { "3", "2",
                "1", "2", "3", "3" }, 3);
        int i = 0;
        while (iter.hasNext()) {
            final String[] actual = iter.next();
            final String[] expected = P_122333_3[i];
            assertArrayEquals(format("P(\"1\", \"2\", \"2\", \"3\", \"3\", \"3\")[%d] expected %s, got %s",
                    i, Arrays.toString(expected), Arrays.toString(actual)), expected, actual);
            ++i;
        }
        assertEquals(P_122333_3.length, i);
    }
}
