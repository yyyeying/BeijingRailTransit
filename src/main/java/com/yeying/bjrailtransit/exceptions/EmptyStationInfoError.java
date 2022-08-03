package com.yeying.bjrailtransit.exceptions;

import com.yeying.bjrailtransit.stations.Station;

public class EmptyStationInfoError extends StationError {
    public EmptyStationInfoError(String name, String line) {
        super(name, line);
    }

    public EmptyStationInfoError(Station station) {
        super(station);
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
