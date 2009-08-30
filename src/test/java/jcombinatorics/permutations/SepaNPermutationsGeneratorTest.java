/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel. All rights reserved.
 *
 * Created Aug 25, 2009
 */
package jcombinatorics.permutations;

import org.junit.Test;

/**
 * JUnit test for {@link SepaNPermutationsGenerator}.
 *
 * @author Alistair A. Israel
 */
public final class SepaNPermutationsGeneratorTest extends PnGeneratorTestBase {

    /**
     * Basic test, compare against P(4)
     */
    @Test
    public void testGeneratePermutations() {
        testGenerateP4(new SepaNPermutationsGenerator(N));
    }
}
