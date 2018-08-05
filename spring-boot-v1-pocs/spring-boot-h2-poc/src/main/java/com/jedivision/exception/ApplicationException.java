package com.jedivision.exception;

public class ApplicationException extends Exception {
    private static final long serialVersionUID = 1L;

    public ApplicationException(String errorMessage) {
        super (errorMessage);
    }

}
