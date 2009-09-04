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
package jcombinatorics.util;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit test for {@link ValuesAtIterator}.
 *
 * @author Alistair A. Israel
 */
public final class ValuesAtIteratorTest {

    /**
     *
     */
    @Test
    public void testValuesAtIterator() {
        final String[] expected = { "ccc", "eeeee", "bb", "dddd" };
        final int[] indices = { 2, 4, 1, 3 };
        final String[] elements = { "a", "bb", "ccc", "dddd", "eeeee" };
        int i = 0;
        final ValuesAtIterator<String> iterator = new ValuesAtIterator<String>(elements, indices);
        while (iterator.hasNext()) {
            final String actual = iterator.next();
            assertEquals(format("[%d] expected \"%s\", got \"%s\"", i, expected[i], actual), expected[i],
                    actual);
            ++i;
        }
    }

}
