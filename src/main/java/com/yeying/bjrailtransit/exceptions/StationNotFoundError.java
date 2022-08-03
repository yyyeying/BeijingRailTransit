package com.yeying.bjrailtransit.exceptions;

public class StationNotFoundError extends StationError{
    public StationNotFoundError(String name, String line){
        super(name, line);
    }

    @Override
    public String toString() {
        return "No station found: " + name + ", " + line;
    }
}
