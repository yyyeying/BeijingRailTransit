package com.yeying.bjrailtransit.exceptions;

import com.yeying.bjrailtransit.stations.Station;

public class StationError extends Exception {
    protected final String name;
    protected final String line;

    public StationError(String name, String line) {
        this.name = name;
        this.line = line;
    }

    public StationError(Station station) {
        this.name = station.getName();
        this.line = station.getLine();
    }

    public String toString() {
        return "StationError: " + name + ", " + line;
    }

}
