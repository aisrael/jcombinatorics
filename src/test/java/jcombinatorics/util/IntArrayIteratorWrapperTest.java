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

import static org.junit.Assert.assertEquals;

import java.util.Iterator;

import jcombinatorics.permutations.SepaPnIterator;

import org.junit.Test;

/**
 * JUnit test for {@link IntArrayIteratorWrapper}.
 *
 * @author Alistair A. Israel
 */
public final class IntArrayIteratorWrapperTest {

    /**
     *
     */
    @Test
    public void testIntArrayIteratorWrapper() {
        final String[] elements = { "a", "b", "c" };
        final Iterator<int[]> iterator = new SepaPnIterator(elements.length);
        final IntArrayIteratorWrapper<String> arrayIteratorWrapper = new IntArrayIteratorWrapper<String>(
                elements, iterator);

        final String[][] expected = { { "a", "b", "c" }, { "a", "c", "b" }, { "b", "a", "c" },
                { "b", "c", "a" }, { "c", "a", "b" }, { "c", "b", "a" } };
        int i = 0;
        while (arrayIteratorWrapper.hasNext()) {
            int j = 0;
            for (final String s : arrayIteratorWrapper.next()) {
                assertEquals(expected[i][j], s);
                ++j;
            }
            ++i;
        }
    }
}
