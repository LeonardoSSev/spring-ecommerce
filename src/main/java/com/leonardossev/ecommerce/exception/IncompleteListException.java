package com.leonardossev.ecommerce.exception;

import java.io.Serializable;

/**
 * The IncompleteListException is thrown when a list does not have the minimum of items,
 *
 * @author leonardossev
 */
public class IncompleteListException extends Exception implements Serializable {

    private static final long serialVersionUID = 399553636301325282L;

    public IncompleteListException() {}

    public IncompleteListException(String message) {
        super(message);
    }
}
