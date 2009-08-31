/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel. All rights reserved.
 *
 * Created Aug 27, 2009
 */
package jcombinatorics.benchmark;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * A rudimentary class for timing benchmarks.
 *
 * @author Alistair A. Israel
 */
public class Benchmark {

    private final String name;

    private final Map<String, Result> results = new LinkedHashMap<String, Result>();

    private long totalMillis;

    /**
     * Default constructor.
     */
    public Benchmark() {
        this.name = "Benchmark@" + System.identityHashCode(results);
    }

    /**
     * @param name
     *        the benchmark suite's name
     */
    public Benchmark(final String name) {
        super();
        this.name = name;
    }

    /**
     * @return the name
     */
    public final String getName() {
        return name;
    }

    /**
     * @return the totalMillis
     */
    public final long getTotalMillis() {
        return totalMillis;
    }

    /**
     * @return the results
     */
    public final Collection<Result> getResults() {
        return results.values();
    }

    /**
     * @param taskName
     *        the task result name to get
     * @return the Result for the named task
     * @see java.util.Map#get(java.lang.Object)
     */
    public final Result get(final String taskName) {
        return results.get(taskName);
    }

    /**
     * @param taskName
     *        the task name
     * @param task
     *        the {@link Runnable} to time
     * @return the benchmark Result of running the task
     */
    public final Result bench(final String taskName, final Runnable task) {
        final Result result = single(taskName, task);
        totalMillis += result.getMillis();
        results.put(taskName, result);
        return result;
    }

    /**
     * @param taskName
     *        the task name
     * @param task
     *        a {@link Runnable}
     * @return {@link Result}
     */
    public static Result single(final String taskName, final Runnable task) {
        final long start = System.currentTimeMillis();
        task.run();
        final long stop = System.currentTimeMillis();
        final long millis = stop - start;
        return new Result(taskName, millis);
    }

    /**
     *
     * @author Alistair A. Israel
     */
    public static class Result {

        private final String name;

        private final long millis;

        /**
         * @param name
         *        the benchmark result name
         * @param millis
         *        the number of milliseconds the benchmark took
         */
        public Result(final String name, final long millis) {
            super();
            this.name = name;
            this.millis = millis;
        }

        /**
         * @return the name
         */
        public final String getName() {
            return name;
        }

        /**
         * @return the millis
         */
        public final long getMillis() {
            return millis;
        }

    }
}
