package com.eburtis.tp.exceptions;

/*******************************************************************
 * Entity Exception Formatting
 * @author  Melissa Kouadio
 * @version 1.0
 *****************************************************************/
public class EntityException extends  Exception{
    public EntityException() {
        super();
    }

    public EntityException(String message) {
        super(message);
    }

    public EntityException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityException(Throwable cause) {
        super(cause);
    }

    protected EntityException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}