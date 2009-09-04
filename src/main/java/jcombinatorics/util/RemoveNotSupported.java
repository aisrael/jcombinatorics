/**
 * jcombinatorics:
 * Java Combinatorics Library
 *
 * Copyright (c) 2009 by Alistair A. Israel.
 *
 * This software is made available under the terms of the MIT License.
 * See LICENSE.txt.
 *
 * Created Sep 4, 2009
 */
package jcombinatorics.util;

/**
 * @author Alistair A. Israel
 */
public class RemoveNotSupported extends UnsupportedOperationException {

    private static final long serialVersionUID = 8632752510609952411L;

    /**
     * {@value #REMOVE_OPERATION_NOT_SUPPORTED_MESSAGE}.
     */
    private static final String REMOVE_OPERATION_NOT_SUPPORTED_MESSAGE = "remove() operation not supported!";

    /**
     *
     */
    public RemoveNotSupported() {
        super(REMOVE_OPERATION_NOT_SUPPORTED_MESSAGE);
    }
}
