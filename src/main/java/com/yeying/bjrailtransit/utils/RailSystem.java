package com.yeying.bjrailtransit.utils;

import com.yeying.bjrailtransit.exceptions.DuplicateIDError;

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

    public Station getStation(String name, String line) throws DuplicateIDError {
        int id = StationIDHandler.generateID(name, line);
        if(!stations.containsKey(id)){
            Station newStation = new Station(name, line);
            stations.put(id, newStation);
        } else if (!stations.get(id).getName().equals(name)) {
            throw new DuplicateIDError(new String[]{stations.get(id).getName(), name}, new String[]{stations.get(id).getLine(), line});
        }
        return stations.get(id);
    }
}
