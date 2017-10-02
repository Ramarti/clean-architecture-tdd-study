package com.lastminute.core.controller;

public interface Controller<T,ValidationException extends Exception> {

    void handleRequest(T request) throws ValidationException;
}
