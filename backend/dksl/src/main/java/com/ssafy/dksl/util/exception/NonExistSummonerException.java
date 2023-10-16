package com.ssafy.dksl.util.exception;

public class NonExistSummonerException extends Exception{
    public NonExistSummonerException() {
    }

    public NonExistSummonerException(String message) {
        super(message);
    }
}
