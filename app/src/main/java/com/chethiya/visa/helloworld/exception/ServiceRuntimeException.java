package com.chethiya.visa.helloworld.exception;

/**
 * @author Chethiya
 *
 * This is the runtime exception model for this microservice
 */
public class ServiceRuntimeException extends RuntimeException {

    public ServiceRuntimeException(String message) {
        super(message);
    }

    public ServiceRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

}
