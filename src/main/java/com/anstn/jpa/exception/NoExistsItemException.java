package com.anstn.jpa.exception;

public class NoExistsItemException extends RuntimeException {


  private static final long serialVersionUID = 1L;

  public NoExistsItemException(String msg) {
    super(msg);
  }

  public NoExistsItemException(String msg, Throwable cause) {
    super(msg, cause); 
  }
}