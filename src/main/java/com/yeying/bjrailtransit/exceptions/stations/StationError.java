package com.yeying.bjrailtransit.exceptions.stations;

import com.yeying.bjrailtransit.lines.RailLine;
import com.yeying.bjrailtransit.stations.Station;
import com.yeying.bjrailtransit.system.RailSystem;

public class StationError extends Exception {
    protected final String name;
    protected final String line;

    public StationError(String name, String line) {
        this.name = name;
        this.line = RailSystem.getInstance().getLine(line).getName();
    }

    public StationError(String name, RailLine line) {
        this.name = name;
        this.line = line.getName();
    }

    public StationError(Station name, RailLine line) {
        this.name = name.getName();
        this.line = line.getName();
    }

    public StationError(Station station) {
        this.name = station.getName();
        this.line = station.getLine().getName();
    }

    public String toString() {
        return "StationError: " + name + ", " + line;
    }

}
