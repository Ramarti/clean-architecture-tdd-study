package com.lastminute.core.controller;

/**
 * General interface for controllers. Assures it gets a request and suggest validation
 * @param <T>
 * @param <ValidationException>
 */
public interface Controller<T,ValidationException extends Exception> {

    void handleRequest(T request) throws ValidationException;
}
