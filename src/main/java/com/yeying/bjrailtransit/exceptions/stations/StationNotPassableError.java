package com.yeying.bjrailtransit.exceptions.stations;

import com.yeying.bjrailtransit.lines.RailLine;
import com.yeying.bjrailtransit.stations.Station;

public class StationNotPassableError extends StationError {
    public StationNotPassableError(String name, String line) {
        super(name, line);
    }

    public StationNotPassableError(Station station) {
        super(station);
    }

    public StationNotPassableError(String name, RailLine line) {
        super(name, line);
    }

    @Override
    public String toString() {
        return "Station not passable: " + name + ", " + line;
    }
}
