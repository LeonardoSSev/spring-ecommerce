package com.leonardossev.ecommerce.exception;

import java.io.Serializable;

/**
 * The StatusListIncompleteException is thrown when the Status' list of an order does not has, at least, 4 items,
 *
 * @author leonardossev
 */
public class StatusListIncompleteException extends Exception implements Serializable {

    private static final long serialVersionUID = 399553636301325282L;

    public StatusListIncompleteException() {}

    public StatusListIncompleteException(String message) {
        super(message);
    }
}
