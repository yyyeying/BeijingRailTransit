package com.yeying.bjrailtransit.utils;

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
        int id = StationIDHandler.generateID(name, line);
        if(!stations.containsKey(id)){
            Station newStation = new Station(name, line);
            stations.put(id, newStation);
        }
        return stations.get(id);
    }
}
