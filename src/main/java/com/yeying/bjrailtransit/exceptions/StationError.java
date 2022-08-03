package com.yeying.bjrailtransit.exceptions;

public class StationError extends Exception{
    protected final String name;
    protected final String line;

    public StationError(String name, String line) {
        this.name = name;
        this.line = line;
    }

    public String toString() {
        return "StationError: " + name + ", " + line;
    }

}
