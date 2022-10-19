package com.example.demo.exceptions;

public class TitleDuplicateException extends RuntimeException{

    public TitleDuplicateException(String message) {
        super("Message with title " + message + " already exists");
    }
}
