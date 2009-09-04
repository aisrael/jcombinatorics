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
import jcombinatorics.permutations.Permutations;
import jcombinatorics.permutations.SepaNKPermutationsGenerator;
import jcombinatorics.permutations.SepaNKPermutationsGenerator2;

/**
 *
 * @author Alistair A. Israel
 */
public class BenchmarkNKGenerators implements Runnable {

    private static final int N = 10;

    private static final int K = 8;

    private static final long P = Permutations.count(N, K);

    private static final int REPS = 1;

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Runnable#run()
     */
    public final void run() {
        System.out.println(format("Expecting %,d permutations..", P));
        final GeneratorBenchmark benchmark = new GeneratorBenchmark("P(" + N + "," + K
                + ") generators benchmark", REPS, P);
        final String pnk = " P(" + N + ", " + K + ")";
        benchmark.bench("SEPA" + pnk, new SepaNKPermutationsGenerator(N, K));
        benchmark.bench("SEPA2" + pnk, new SepaNKPermutationsGenerator2(N, K));
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
