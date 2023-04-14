package com.eburtis.tp.exceptions;

public enum ErrorCodes {

  PERSON_NOT_FOUND(1000),
  PERSON_NOT_VALID(1001),
  PERSON_ALREADY_IN_USE(1002),

  DEPARTMENT_NOT_FOUND(2000),
  DEPARTMENT_NOT_VALID(2001),
  DEPARTMENT_ALREADY_IN_USE(2002),
  UNKNOWN_CONTEXT(14001);

  private int code;

  ErrorCodes(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
