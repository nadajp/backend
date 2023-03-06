package com.nadajp.sample.exceptions;


public class InvalidLoginException extends Throwable {
   public InvalidLoginException(String message) {
      super(message);
        // log error message
        System.out.println(message);
   }
}
