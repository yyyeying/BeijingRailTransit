package com.yeying.bjrailtransit.exceptions;

public class EmptyStationInfoError extends Exception{
    private final String name;
    private final String line;

    public EmptyStationInfoError(String name, String line){
        this.name = name;
        this.line = line;
    }

    @Override
    public String toString() {
        String message = "Empty info: ";
        if (name == null) {
            message += " name ";
        }
        if (line == null) {
            message += " line ";
        }
        return message;
    }
}
