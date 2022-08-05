package com.yeying.bjrailtransit.exceptions.lines;

import com.yeying.bjrailtransit.lines.RailLine;
import com.yeying.bjrailtransit.stations.Station;

public class StationNotInLineError extends LineError {
    private final String station;

    public StationNotInLineError(String line, String station) {
        super(line);
        this.station = station;
    }

    public StationNotInLineError(RailLine line, Station station) {
        super(line);
        this.station = station.getName();
    }

    @Override
    public String toString() {
        return "StationNotInLineError: " + station + " not in " + this.line;
    }
}
