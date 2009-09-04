/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Aug 28, 2009
 */
package jcombinatorics.benchmark;

/**
 *
 * @author Alistair A. Israel
 */
public class GeneratorBenchmark extends PermutatedOrderBenchmark {

    private final String name;

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
        super(reps);
        this.name = name;
        this.expectedPermutations = expectedPermutations;
    }

    /**
     * @param generatorName
     *        the generator/task name
     * @param generator
     *        the generator
     */
    public final void bench(final String generatorName, final Iterable<int[]> generator) {
        final Bench bench = new Bench(generator);
        addTask(generatorName, bench);
    }

    /**
     *
     */
    public final void prettyPrint() {
        for (final Task task : getTasks()) {
            printResult(task);
        }
    }

    /**
     * @param task
     *        the task
     */
    private void printResult(final Task task) {
        final long nanos = task.getTotalNanos();
        final float millis = nanos / (float) 1000000.0;
        final long np = expectedPermutations * getReps();
        final float ppms = np / millis;
        System.out.println(String.format("%d times %s took %,1.2f ms", getReps(), task.getTaskName(), millis));
        System.out.println(String.format(
                "Generated %d * %,d = %,d permutations, average %,1.2f permutations/ms)", getReps(),
                expectedPermutations, np, ppms));
    }

    /**
     *
     * @author Alistair A. Israel
     */
    private class Bench implements Runnable {

        private final Iterable<int[]> generator;

        /**
         * @param generator
         *        iterable
         */
        public Bench(final Iterable<int[]> generator) {
            this.generator = generator;
        }

        /**
         * {@inheritDoc}
         *
         * @see java.lang.Runnable#run()
         */
        public final void run() {
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
