/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Aug 24, 2009
 */
package jcombinatorics.util;

import static jcombinatorics.util.MathUtils.factorial;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit test for {@link MathUtils}.
 *
 * @author Alistair A. Israel
 */
public final class MathUtilsTest {

    /**
     * Test for {@link MathUtils#factorial(int)}.
     */
    @Test
    public void testFactorial() {
        assertEquals(1, factorial(1));
        assertEquals(2, factorial(2));
        assertEquals(6, factorial(3));
        assertEquals(3628800, factorial(10));
        assertEquals(2432902008176640000L, factorial(20));
    }
}
