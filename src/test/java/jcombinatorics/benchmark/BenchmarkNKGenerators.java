/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel. All rights reserved.
 *
 * Created Aug 26, 2009
 */
package jcombinatorics.benchmark;

import static java.lang.String.format;
import jcombinatorics.permutations.FactoradicNKPermutationsGenerator;
import jcombinatorics.permutations.Permutations;
import jcombinatorics.permutations.SepaNKPermutationsGenerator;

/**
 *
 * @author Alistair A. Israel
 */
public class BenchmarkNKGenerators implements Runnable {

    private static final int N = 10;

    private static final int K = 8;

    private static final long P = Permutations.count(N, K);

    private static final int REPS = 10;

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
        benchmark.bench("Factoradic" + pnk, new FactoradicNKPermutationsGenerator(N, K));
    }

    /**
     * @param args
     *        String[]
     */
    public static void main(final String[] args) {
        new BenchmarkNKGenerators().run();
    }
}
