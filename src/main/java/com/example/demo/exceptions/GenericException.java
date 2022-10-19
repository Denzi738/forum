package com.example.demo.exceptions;

public class GenericException {

    private String exceptionCode;

    private String exceptionText;

    public GenericException(String exceptionCode, String exceptionText) {
        this.exceptionCode = exceptionCode;
        this.exceptionText = exceptionText;
    }

    public GenericException() {
    }

    public String getExceptionCode() {
        return exceptionCode;
    }

    public void setExceptionCode(String exceptionCode) {
        this.exceptionCode = exceptionCode;
    }

    public String getExceptionText() {
        return exceptionText;
    }

    public void setExceptionText(String exceptionText) {
        this.exceptionText = exceptionText;
    }
}
