package com.yeying.bjrailtransit.exceptions;

public class StationNotOpenError extends Exception{
    private final String name;
    private final String line;

    public StationNotOpenError(String name, String line){
        this.name = name;
        this.line = line;
    }

    @Override
    public String toString() {
        return "Station not open: " + name + ", " + line;
    }
}
