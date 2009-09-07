/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Sep 5, 2009
 */
package jcombinatorics.benchmark;

import static java.lang.String.format;
import jcombinatorics.benchmark.PermutatedOrderBenchmark.Task;
import jcombinatorics.combinations.CombinadicCombinationsGenerator;
import jcombinatorics.combinations.CombinadicCombinationsGenerator2;
import jcombinatorics.combinations.Combinations;

/**
 * @author Alistair A. Israel
 */
public class CombinationsGeneratorBenchmark implements Runnable {

    private static final int REPS = 4;

    private static final int N = 20;

    private static final int K = 10;

    private static final long EXPECTED_COMBINATIONS = Combinations.count(N, K);

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Runnable#run()
     */
    public final void run() {
        System.out.println(format("Generating %d * %,d combinations...", REPS, EXPECTED_COMBINATIONS));
        final String cnk = " C(" + N + "," + K + ")";
        final PermutatedOrderBenchmark benchmark = new PermutatedOrderBenchmark(REPS);
        benchmark.addTask("Combinadic2" + cnk, new CombinationsGeneratorBenchmarkTask(
                new CombinadicCombinationsGenerator2(N, K)));
        benchmark.addTask("Combinadic" + cnk, new CombinationsGeneratorBenchmarkTask(
                new CombinadicCombinationsGenerator(N, K)));
//        benchmark.addTask("Rosen" + cnk, new CombinationsGeneratorBenchmarkTask(new RosenIterator.Factory(
//                N, K)));
        benchmark.benchmarkAll();
        for (final Task task : benchmark.getTasks()) {
            printResult(task);
        }

    }

    /**
     * @param task
     *        the task
     */
    private void printResult(final Task task) {
        final long nanos = task.getTotalNanos();
        final float millis = nanos / 1000000.0f;
        final long nc = EXPECTED_COMBINATIONS * REPS;
        final float ppms = nc / millis;
        System.out.println(format("%d times %s took %,1.2f ms", REPS, task.getTaskName(), millis));
        System.out.println(format("Generated %d * %,d = %,d combinations, average %,1.2f combinations/ms)",
                REPS, EXPECTED_COMBINATIONS, nc, ppms));
    }

    /**
     * @author Alistair A. Israel
     */
    private static final class CombinationsGeneratorBenchmarkTask implements Runnable {

        private final Iterable<int[]> generator;

        /**
         * @param generator
         *        {@link Iterable}&lt;int[]&gt;
         */
        public CombinationsGeneratorBenchmarkTask(final Iterable<int[]> generator) {
            super();
            this.generator = generator;
        }

        /**
         * {@inheritDoc}
         *
         * @see java.lang.Runnable#run()
         */
        public void run() {
            int i = 0;
            for (@SuppressWarnings("unused")
            final int[] combination : generator) {
                ++i;
            }
            if (i != EXPECTED_COMBINATIONS) {
                throw new Error("Expected " + EXPECTED_COMBINATIONS + " combinations generated, only got "
                        + i + "!");
            }
        }

    }

    /**
     * @param args
     *        String[]
     */
    public static void main(final String[] args) {
        new CombinationsGeneratorBenchmark().run();
    }
}
