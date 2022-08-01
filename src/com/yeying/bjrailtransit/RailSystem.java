package com.yeying.bjrailtransit;

import com.yeying.bjrailtransit.utils.Station;
import com.yeying.bjrailtransit.utils.StationIDHandler;

import java.util.HashMap;
import java.util.Map;

public class RailSystem {
    private static RailSystem instance = new RailSystem();
    private Map<Integer, Station> stations;

    private RailSystem(){
        this.stations = new HashMap<>();
    }

    public static RailSystem getInstance() {
        return instance;
    }

    public Station getStation(String name, String line) {
        int id = StationIDHandler.getID(name, line);
        return stations.getOrDefault(id, null);
    }
}
