package com.nadajp.sample.exceptions;

public class UnsuccessfulRegistrationException extends Throwable {
    public UnsuccessfulRegistrationException(String message) {
        super(message);
          // log error message
          System.out.println(message);
     }
}
