package com.anime.endsystem.exception;

public class NoAuthException extends Exception {
    String message;

    public NoAuthException(String message){
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
