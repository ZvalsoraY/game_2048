package com.mypac.exception;

public class NotEnoughSpaceException extends Exception{
    public NotEnoughSpaceException(String messageException) {
        super(messageException);
    }
}