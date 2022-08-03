package com.yeying.bjrailtransit.exceptions.stations;

import com.yeying.bjrailtransit.stations.Station;

public class StationNotOpenError extends StationError {
    public StationNotOpenError(String name, String line) {
        super(name, line);
    }

    public StationNotOpenError(Station station) {
        super(station);
    }

    @Override
    public String toString() {
        return "Station not open: " + name + ", " + line;
    }
}
