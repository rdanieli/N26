package com.n26.trial.statistics.service;

/**
 * Created by Rafael on 21/03/2017.
 */
public class ServiceException extends RuntimeException {

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
