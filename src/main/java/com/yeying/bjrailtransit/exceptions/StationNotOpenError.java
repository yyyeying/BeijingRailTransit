package com.yeying.bjrailtransit.exceptions;

public class StationNotOpenError extends StationError {
    public StationNotOpenError(String name, String line) {
        super(name, line);
    }

    @Override
    public String toString() {
        return "Station not open: " + name + ", " + line;
    }
}
