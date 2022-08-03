package com.yeying.bjrailtransit.system;

import com.yeying.bjrailtransit.exceptions.stations.StationNotPassableError;
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
        try {
            setEndStation(endStation);
        } catch (StationNotPassableError e) {
            System.out.print("End station is not passable, please change another one.\n");
        }

        try {
            setStartStation(startStation);
            addStation(startStation, 0);
        } catch (StationNotPassableError e) {
            System.out.print("Start station is not passable, please change another one.\n");
        }

    }

    public RailPath(Station startStation) {
        this();
        try {
            setStartStation(startStation);
            addStation(startStation, 0);
        } catch (StationNotPassableError e) {
            System.out.print("Start station is not passable, please change another one.\n");
        }
    }

    public void addStation(Station station, int distance) throws StationNotPassableError {
        if (!station.isPassable()) {
            throw new StationNotPassableError(station.getName(), station.getLine());
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

    public void setStartStation(Station startStation) throws StationNotPassableError {
        if (!startStation.isPassable()) {
            throw new StationNotPassableError(startStation.getName(), startStation.getLine());
        }
        this.startStation = startStation;
    }

    public Station getEndStation() {
        return endStation;
    }

    public int getDistance() {
        return distance;
    }

    public void setEndStation(Station endStation) throws StationNotPassableError {
        if (!endStation.isPassable()) {
            throw new StationNotPassableError(endStation);
        }
        this.endStation = endStation;
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        for (Station s :
                path) {
            message.append(s.getName()).append(", ").append(s.getLine()).append("\n");
        }
        double kiloDistance = distance / 1000.0;
        message.append("distance: ").append(kiloDistance).append(" km");
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
