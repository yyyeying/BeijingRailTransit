package com.yeying.bjrailtransit.exceptions;

public class StationNotFoundError extends Exception{
    private final String name;
    private final String line;

    public StationNotFoundError(String name, String line){
        this.name = name;
        this.line = line;
    }

    @Override
    public String toString() {
        return "No station found: " + name + ", " + line;
    }
}
