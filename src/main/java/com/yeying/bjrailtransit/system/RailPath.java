package com.yeying.bjrailtransit.system;

import com.yeying.bjrailtransit.exceptions.StationNotOpenError;
import com.yeying.bjrailtransit.stations.Station;

import java.util.ArrayList;
import java.util.List;

public class RailPath implements Cloneable {
    private List<Station> path;
    private Station startStation;
    private Station endStation;
    private int distance;

    public RailPath() {
        path = new ArrayList<>();
        distance = 0;
    }

    public RailPath(Station startStation, Station endStation) {
        this();
        setStartStation(startStation);
        setEndStation(endStation);
        try {
            addStation(startStation, 0);
        } catch (StationNotOpenError e) {
            e.printStackTrace();
        }

    }

    public RailPath(Station startStation) {
        this();
        setStartStation(startStation);
        try {
            addStation(startStation, 0);
        } catch (StationNotOpenError e) {
            e.printStackTrace();
        }
    }

    public void addStation(Station station, int distance) throws StationNotOpenError {
        if (!station.isPassable()) {
            throw new StationNotOpenError(station.getName(), station.getLine());
        }
        path.add(station);
        this.distance += distance;
    }

    public Station getNewestStation() {
        if (path.size() > 0) {
            return path.get(path.size() - 1);
        } else {
            return null;
        }
    }

    public boolean containsStation(Station station) {
        return path.contains(station);
    }

    public List<Station> getPath() {
        return path;
    }

    public Station getStartStation() {
        return startStation;
    }

    public void setStartStation(Station startStation) {
        this.startStation = startStation;
    }

    public Station getEndStation() {
        return endStation;
    }

    public int getDistance() {
        return distance;
    }

    public void setEndStation(Station endStation) {
        this.endStation = endStation;
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        for (Station s :
                path) {
            message.append(s.getName()).append(", ").append(s.getLine()).append("\n");
        }
        message.append("distance: ").append(distance);
        return message.toString();
    }

    @Override
    protected Object clone() {
        RailPath newPath = null;
        try {
            newPath = (RailPath) super.clone();
            newPath.path = new ArrayList<>(newPath.path);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return newPath;
    }
}
