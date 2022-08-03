package com.yeying.bjrailtransit.exceptions;

public class EmptyStationInfoError extends StationError {
    public EmptyStationInfoError(String name, String line) {
        super(name, line);
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
