package jcombinatorics;

/**
 * <p>
 * Interface to be used in the Visitor pattern for each generated
 * combination.
 * </p>
 * 
 * <p>
 * See: <a href="https://en.wikipedia.org/wiki/Visitor_pattern">Wikipedia -
 * Visitor pattern</a>
 * </p>
 *
 */
public interface IStepVisitor {

	/**
	 * Get the current result of the combinatorics algorithm step.
	 * 
	 * @param result
	 * @return <code>false</code> if the combinatorics algorithm should stop
	 *         immediately; <code>true</code> otherwise.
	 */
	public abstract boolean visit(int[][] result);

}