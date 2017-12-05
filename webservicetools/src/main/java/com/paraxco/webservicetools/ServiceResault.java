package com.paraxco.webservicetools;

/**
 *
 */

public class ServiceResault<T> {
    public String error;
    public T result;

    public Class<T> getResultType() {
        return (Class<T>) result.getClass();
    }
}
