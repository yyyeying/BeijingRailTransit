package com.yeying.bjrailtransit.exceptions.stations;

import com.yeying.bjrailtransit.stations.Station;

public class StationNotPassableError extends StationError {
    public StationNotPassableError(String name, String line) {
        super(name, line);
    }

    public StationNotPassableError(Station station) {
        super(station);
    }

    @Override
    public String toString() {
        return "Station not passable: " + name + ", " + line;
    }
}
