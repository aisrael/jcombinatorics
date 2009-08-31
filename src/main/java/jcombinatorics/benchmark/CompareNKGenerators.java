/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel. All rights reserved.
 *
 * Created Aug 27, 2009
 */
package jcombinatorics.benchmark;

import static java.lang.System.out;
import jcombinatorics.benchmark.Benchmark.Result;
import jcombinatorics.permutations.FactoradicNKPermutationsGenerator;
import jcombinatorics.permutations.Permutations;
import jcombinatorics.permutations.SepaNKPermutationsGenerator;

/**
 *
 * @author Alistair A. Israel
 */
public class CompareNKGenerators implements Runnable {

    private static final int N = 7;

    private static final int MULTIPLIER = 100;

    private static final int TARGET_TOTAL_PERMUTATIONS = (int) (MULTIPLIER * Permutations.count(N, N));

    /**
     * {@inheritDoc}
     *
     * @see java.lang.Runnable#run()
     */
    public final void run() {
        final NKGeneratorFactory sepaFactory = new NKGeneratorFactory() {

            public Iterable<int[]> generator(final int n, final int k) {
                return new SepaNKPermutationsGenerator(n, k);
            }
        };
        final float[][] sepa = collectTimings(sepaFactory);
        final NKGeneratorFactory factoradicFactory = new NKGeneratorFactory() {

            public Iterable<int[]> generator(final int n, final int k) {
                return new FactoradicNKPermutationsGenerator(n, k);
            }
        };
        final float[][] factoradic = collectTimings(factoradicFactory);
        printTimingArray(sepa);
        printTimingArray(factoradic);
    }

    /**
     * @param factory
     *        the NKGeneratorFactory
     * @return float[][]
     */
    private float[][] collectTimings(final NKGeneratorFactory factory) {
        final float[][] sepa = new float[N + 1][N + 1];
        for (int n = 2; n <= N; ++n) {
            for (int k = 1; k <= n; ++k) {
                final int p = (int) Permutations.count(n, k);
                int reps = TARGET_TOTAL_PERMUTATIONS / p;
                if (reps < 1) {
                    reps = 1;
                }
                final String taskName = "SEPA P(" + n + "," + k + ")";
                final Task task = new Task(reps, factory.generator(n, k));
                final Result result = Benchmark.single(taskName, task);
                final long millis;
                if (result.getMillis() > 0) {
                    millis = result.getMillis();
                } else {
                    millis = 1;
                }
                final String name = result.getName();
                final long np = p * reps;
                final float ppms = np / millis;
                out.println(String.format("%d times %s took %,d ms", reps, name, millis));
                out.println(String.format(
                        "Generated %d * %,d = %,d permutations, average %,1.2f permutations/ms)", reps, p,
                        np, ppms));
                sepa[n][k] = ppms;
            }
        }
        return sepa;
    }

    /**
     *
     * @author Alistair A. Israel
     */
    private static class Task implements Runnable {

        private final int reps;

        private final Iterable<int[]> generator;

        /**
         * @param reps
         *        the number of repetitions
         * @param generator
         *        iterable
         */
        public Task(final int reps, final Iterable<int[]> generator) {
            this.reps = reps;
            this.generator = generator;
        }

        /**
         * {@inheritDoc}
         *
         * @see java.lang.Runnable#run()
         */
        public final void run() {
            for (int i = 0; i < reps; ++i) {
                // CHECKSTYLE:OFF
                for (@SuppressWarnings("unused")
                final int[] permutation : generator) {
                    // noop
                }
                // CHECKSTYLE:ON
            }
        }

    }

    /**
     * @param a
     *        float[]
     */
    private void printTimingArray(final float[][] a) {
        out.print("N,");
        for (int k = 1; k <= N; ++k) {
            out.print(k + ",");
        }
        out.println();
        for (int n = 2; n <= N; ++n) {
            out.print(n + ",");
            for (int k = 1; k <= n; ++k) {
                out.print(a[n][k] + ",");
            }
            out.println();
        }
    }

    /**
     * @param args
     *        String[]
     */
    public static void main(final String[] args) {
        new CompareNKGenerators().run();
    }
}
