/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel. All rights reserved.
 *
 * Created Aug 31, 2009
 */
package jcombinatorics.combinations;

import static java.lang.String.format;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

/**
 * JUnit test for {@link CombinationsIterator}.
 *
 * @author Alistair A. Israel
 */
public final class CombinationsIteratorTest {

    private static final long EXPECTED_COUNT = Combinations.count(5, 3);
    private static final int[][] C_5_3 = { { 0, 1, 2 }, { 0, 1, 3 }, { 0, 1, 4 }, { 0, 2, 3 }, { 0, 2, 4 },
            { 0, 3, 4 }, { 1, 2, 3 }, { 1, 2, 4 }, { 1, 3, 4 }, { 2, 3, 4 } };

    /**
     *
     */
    @Test
    public void testGenerateC53() {
        final CombinationsIterator iter = new CombinationsIterator(5, 3);
        int count = 0;
        while (iter.hasNext()) {
            final int[] actual = iter.next();
            final int[] expected = C_5_3[count];
            assertArrayEquals(format("C(5,3)[%d], expected %s, got %s", count, Arrays.toString(expected),
                    Arrays.toString(actual)), expected, actual);
            ++count;
        }
        assertEquals(EXPECTED_COUNT, count);
    }
}
