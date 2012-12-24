package jcombinatorics.combinations;

import java.util.Iterator;

/**
 * <p>
 * An iterator that generates all partitions of <code>n</code> elements, into <code>k</code> parts containing the number
 * of elements in each part, based on Rosen's algorithm.
 * </p>
 *
 * @author Alistair A. Israel
 * @see "Kenneth H. Rosen, Discrete Mathematics and Its Applications, 2nd edition (NY: McGraw-Hill, 1991), pp. 284-286"
 */
public class RosenNumberPartitionIterator extends RosenIterator {

    /**
     * @param n
     *        the number of elements
     * @param k
     *        divided into k parts
     */
    public RosenNumberPartitionIterator(final int n, final int k) {
        super(n - 1, k - 1);
    }

    /**
     * {@inheritDoc}
     *
     * @see Iterator#next()
     */
    @Override
    public final int[] next() {
        final int[] actual = super.next();
        final int[] temp = new int[k + 1];
        for (int i = 0; i < temp.length; i++) {
            if (i == 0) {
                temp[i] = actual[i] + 1;
            } else {
                if (i == temp.length - 1) {
                    temp[i] = n - actual[i - 1];
                } else {
                    temp[i] = actual[i] - actual[i - 1];
                }
            }
        }
        return temp;
    }

}