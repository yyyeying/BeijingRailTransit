package com.yeying.bjrailtransit.exceptions.path;

import com.yeying.bjrailtransit.stations.Station;
import org.jetbrains.annotations.NotNull;

public class DuplicateStationError extends PathException {
    private String station;

    public DuplicateStationError(@NotNull Station station) {
        super();
        this.station = station.getName();
    }

    @Override
    public String toString() {
        return "Duplicate station in path: " + station;
    }
}
