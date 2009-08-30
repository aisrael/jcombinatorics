/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel. All rights reserved.
 *
 * Created Aug 28, 2009
 */
package jcombinatorics.benchmark;

/**
 *
 * @author Alistair A. Israel
 */
public class GeneratorBenchmark extends Benchmark {

    private final int reps;

    private final long expectedPermutations;

    /**
     * @param name
     *        the benchmark suite name
     * @param reps
     *        the number of repetitions to run per generator
     * @param expectedPermutations
     *        the expected number of permutations generated in one 'run'
     */
    public GeneratorBenchmark(final String name, final int reps, final long expectedPermutations) {
        super(name);
        this.reps = reps;
        this.expectedPermutations = expectedPermutations;
    }

    /**
     * @param name
     *        the generator/task name
     * @param generator
     *        the permutations generator
     * @return {@link Result}
     */
    public final Result bench(final String name, final Iterable<int[]> generator) {
        final Result result = bench(name, new Task(name, generator));
        printResult(result);
        return result;
    }

    /**
     * @param result
     *        the {@link Result}
     */
    private void printResult(final Result result) {
        final String name = result.getName();
        final long millis = result.getMillis();
        final long np = expectedPermutations * reps;
        final float ppms = np / millis;
        System.out.println(String.format("%d times %s took %,d ms", reps, name, millis));
        System.out.println(String.format(
                "Generated %d * %,d = %,d permutations, average %,1.2f permutations/ms)", reps,
                expectedPermutations, np, ppms));
    }

    /**
     *
     */
    public final void prettyPrint() {
        for (final Result result : getResults()) {
            printResult(result);
        }
    }

    /**
     *
     * @author Alistair A. Israel
     */
    private class Task implements Runnable {

        private final String name;

        private final Iterable<int[]> generator;

        /**
         * @param name
         *        the generator/task name
         * @param generator
         *        iterable
         */
        public Task(final String name, final Iterable<int[]> generator) {
            this.name = name;
            this.generator = generator;
        }

        /**
         * {@inheritDoc}
         *
         * @see java.lang.Runnable#run()
         */
        public final void run() {
            for (int i = 0; i < reps; ++i) {
                int count = 0;
                for (@SuppressWarnings("unused")
                final int[] permutation : generator) {
                    ++count;
                }
                if (count != expectedPermutations) {
                    throw new Error(String.format(
                            "GeneratorBenchmark.Task %s: expected %,d permutations, got %,d!", name,
                            expectedPermutations, count));
                }
            }
        }

    }
}
