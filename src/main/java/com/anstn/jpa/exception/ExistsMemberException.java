package com.anstn.jpa.exception;

public class ExistsMemberException extends RuntimeException {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public ExistsMemberException(String message) {
    super(message); 
  }

  public ExistsMemberException(String massage, Throwable cause) {
    super(massage, cause); 
  }
}