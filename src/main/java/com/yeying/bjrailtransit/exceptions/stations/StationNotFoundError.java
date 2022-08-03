package com.yeying.bjrailtransit.exceptions.stations;

import com.yeying.bjrailtransit.stations.Station;

public class StationNotFoundError extends StationError {
    public StationNotFoundError(String name, String line) {
        super(name, line);
    }

    public StationNotFoundError(Station station) {
        super(station);
    }

    @Override
    public String toString() {
        return "No station found: " + name + ", " + line;
    }
}
