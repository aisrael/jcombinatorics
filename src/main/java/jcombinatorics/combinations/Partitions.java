package jcombinatorics.combinations;

import java.util.Arrays;

/**
 * See <a href=
 * "http://stackoverflow.com/questions/4504974/how-to-iteratively-generate-k-elements-subsets-from-a-set-of-size-n-in-java/4505314#4505314"
 * >Stackoverflow - How to iteratively generate k elements subsets from a set of size n in java?</a>
 */
public class Partitions {
    
    private int[] set;
    private int[] parts;
    private int currentPartsIndex;
    private int partitions;
    private int[][] resultList;

    /**
     * @param set
     *            int[]
     * @param partitions
     *            int
     */
    public Partitions(final int[] set, final int partitions) {
        this.set = set;
        this.partitions = partitions;
        this.parts = new int[partitions];
        for (int i = 0; i < partitions - 1; i++) {
            this.parts[i] = 1;
        }
        this.parts[partitions - 1] = set.length - partitions + 1;
        currentPartsIndex = 0;
        resultList = new int[partitions][];
    }

    /**
     * 
     */
    public final void processSubsets() {
        final int[] subset = new int[parts[currentPartsIndex++]];
        processLargerSubsets(subset, 0, 0);
        --currentPartsIndex;
    }

    /**
     * @param subset
     *            int[]
     * @param subsetSize
     *            int
     * @param nextIndex
     *            int
     */
    final void processLargerSubsets(final int[] subset, final int subsetSize, final int nextIndex) {
        if (subsetSize == subset.length) {
            print(subset);
        } else {
            for (int j = nextIndex; j < set.length; j++) {
                if (subset[subsetSize] != set[j]) {
                    subset[subsetSize] = set[j];
                    set[j] = -1;
                    processLargerSubsets(subset, subsetSize + 1, j + 1);
                    set[j] = subset[subsetSize];
                    subset[subsetSize] = -1; // reset with an undefined index number
                }
            }
        }
    }

    /**
     * @param subset
     *            int[]
     */
    final void print(final int[] subset) {
        resultList[currentPartsIndex - 1] = subset;
        if (currentPartsIndex < partitions) {
            final int[] oldSet = set;
            final int[] newSet = new int[set.length - subset.length];
            int j = 0;
            for (int i = 0; i < oldSet.length; i++) {
                if (oldSet[i] >= 0) {
                    newSet[j++] = oldSet[i];
                }
            }
            set = newSet;
            processSubsets();
            set = oldSet;
        } else {
            // end condition
            System.out.println();
            for (int i = 0; i < resultList.length; i++) {
                System.out.print(Arrays.toString(resultList[i]));
            }
        }
        resultList[currentPartsIndex - 1] = null;
    }

    /**
     * @param args
     *            String[]
     * @throws Exception
     *             on exception
     */
    public static void main(final String[] args) throws Exception {
        final int[] set = { 1, 2, 2, 2, 3, 5, 6 };
        final Partitions p = new Partitions(set, 3);
        p.processSubsets();
    }

}
