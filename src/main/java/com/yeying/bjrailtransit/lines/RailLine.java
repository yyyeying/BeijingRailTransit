package com.yeying.bjrailtransit.lines;

import com.yeying.bjrailtransit.stations.Station;

import java.util.ArrayList;
import java.util.List;

public class RailLine {
    private String name;
    private boolean loop;
    private Fare fare;
    List<Station> stations;

    public RailLine() {
        this.stations = new ArrayList<>();
        this.fare = Fare.BEIJING_SUBWAY;
    }

    public RailLine(String name) {
        this();
        setName(name);
    }

    public RailLine(String name, boolean loop){
        this();
        setName(name);
        setLoop(loop);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }

    public boolean isLoop() {
        return loop;
    }

    public void addStation(Station station) {
        stations.add(station);
    }

    public int getStationCount() {
        return stations.size();
    }

    public Fare getFare() {
        return fare;
    }

    public void setFare(Fare fare) {
        this.fare = fare;
    }

    public void setFare(String fare) {
        this.fare = Fare.valueOf(fare);
    }
}
