package com.yeying.bjrailtransit.utils;

import com.yeying.bjrailtransit.exceptions.EmptyStationInfoError;
import com.yeying.bjrailtransit.exceptions.StationNotFoundError;

import java.util.HashMap;
import java.util.Map;

public class Station {
    private int id;
    private String name;
    private String line;
    private Map<Station, Integer> links;

    public Station(String name, String line) {
        this.setName(name);
        this.setLine(line);
        this.setId();
        links = new HashMap<>();
    }

    private void setId() {
        if (name == null || line == null) {
            return;
        }
        id = StationIDHandler.generateID(name, line);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public Map<Station, Integer> getLinks() {
        return links;
    }

    public void setLink(String name, String line, int distance) throws StationNotFoundError, EmptyStationInfoError {
        if(name == null || line == null){
            throw new EmptyStationInfoError(name, line);
        }
        Station destinationStation= RailSystem.getInstance().getStation(name, line);
        if(destinationStation == null){
            throw new StationNotFoundError(name, line);
        }
        links.put(destinationStation, distance);
    }
}
