/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Aug 26, 2009
 */
package jcombinatorics.benchmark;

import static java.lang.String.format;
import jcombinatorics.permutations.FactoradicNKPermutationsGenerator;
import jcombinatorics.permutations.Permutations;
import jcombinatorics.permutations.SepaPnkIterator;

/**
 *
 * @author Alistair A. Israel
 */
public class BenchmarkNKGenerators implements Runnable {

    private static final int N = 11;

    private static final int K = 8;

    private static final long EXPECTED_PERMUTATIONS = Permutations.count(N, K);

    private static final int REPS = 5;

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Runnable#run()
     */
    public final void run() {
        System.out.println(format("Expecting %,d permutations..", EXPECTED_PERMUTATIONS));
        final GeneratorBenchmark benchmark = new GeneratorBenchmark("P(" + N + "," + K
                + ") generators benchmark", REPS, EXPECTED_PERMUTATIONS);
        final String pnk = " P(" + N + ", " + K + ")";
        benchmark.bench("a.SEPA" + pnk, new SepaPnkIterator.Factory(N, K));
        benchmark.bench("b.Factoradic" + pnk, new FactoradicNKPermutationsGenerator(N, K));
        benchmark.benchmarkAll();
        benchmark.prettyPrint();
    }

    /**
     * @param args
     *        String[]
     */
    public static void main(final String[] args) {
        new BenchmarkNKGenerators().run();
    }
}
