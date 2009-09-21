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

import jcombinatorics.permutations.FactoradicNKPermutationsGenerator;
import jcombinatorics.permutations.FactoradicNPermutationsGenerator;
import jcombinatorics.permutations.SepaPnIterator;
import jcombinatorics.permutations.SepaPnkIterator;
import jcombinatorics.util.MathUtils;

/**
 *
 * @author Alistair A. Israel
 */
public class BenchmarkNGenerators implements Runnable {

    private static final int N = 10;

    private static final long P = MathUtils.factorial(N);

    private static final int REPS = 1;

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Runnable#run()
     */
    public final void run() {
        final String pn = "P(" + N + ")";
        final GeneratorBenchmark benchmark = new GeneratorBenchmark(pn + " generators benchmark", REPS, P);
        benchmark.bench("SEPA " + pn, new SepaPnIterator.Factory(N));
        benchmark.bench("SEPA P(" + N + "," + N + ")", new SepaPnkIterator.Factory(N, N));
        benchmark.bench("Factoradic " + pn, new FactoradicNPermutationsGenerator(N));
        benchmark.bench("Factoradic P(" + N + "," + N + ")", new FactoradicNKPermutationsGenerator(N, N));
        benchmark.benchmarkAll();
        benchmark.prettyPrint();
    }

    /**
     * @param args
     *        String[]
     */
    public static void main(final String[] args) {
        new BenchmarkNGenerators().run();
    }
}
