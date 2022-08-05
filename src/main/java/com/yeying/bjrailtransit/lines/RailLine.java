package com.yeying.bjrailtransit.lines;

import com.yeying.bjrailtransit.exceptions.lines.EmptyLineError;
import com.yeying.bjrailtransit.exceptions.lines.StationNotInLineError;
import com.yeying.bjrailtransit.exceptions.stations.StationNotPassableError;
import com.yeying.bjrailtransit.stations.Station;
import com.yeying.bjrailtransit.system.RailPath;
import com.yeying.bjrailtransit.system.RailSystem;

import java.util.ArrayList;
import java.util.List;

public class RailLine {
    List<Station> stations;
    private String name;
    private boolean loop;
    private Fare fare;

    public RailLine() {
        this.stations = new ArrayList<>();
        this.fare = Fare.BEIJING_SUBWAY;
    }

    public RailLine(String name) {
        this();
        setName(name);
    }

    public RailLine(String name, boolean loop) {
        this();
        setName(name);
        setLoop(loop);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
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

    public int getStationIndex(String station) {
        try {
            return getStationIndex(RailSystem.getInstance().getStation(station, this.getName()));
        } catch (StationNotInLineError e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Get index of a station in this line.
     *
     * @param station the station
     * @return index
     * @throws StationNotInLineError Station not in this line
     */
    public int getStationIndex(Station station) throws StationNotInLineError {
        if (!this.equals(station.getLine())) {
            throw new StationNotInLineError(this, station);
        }
        return stations.indexOf(station);
    }

    /**
     * Get index ascending station of this line.
     *
     * @param station the station
     * @return the next station
     */
    public Station nextStation(String station) {
        try {
            return nextStation(RailSystem.getInstance().getStation(station, this.getName()));
        } catch (StationNotInLineError e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get index ascending station of this line.
     *
     * @param station the station
     * @return the next station
     */
    public Station nextStation(Station station) throws StationNotInLineError {
        if (!this.equals(station.getLine())) {
            throw new StationNotInLineError(this, station);
        }
        int index = stations.indexOf(station);
        if (!this.isLoop()) {
            if (index >= getStationCount() - 1) {
                return null;
            }
            return stations.get(index + 1);
        } else {
            return stations.get((index + 1) % getStationCount());
        }
    }

    /**
     * Get index descending station of this line.
     *
     * @param station the station
     * @return the last station
     */
    public Station lastStation(String station) {
        try {
            return lastStation(RailSystem.getInstance().getStation(station, this.getName()));
        } catch (StationNotInLineError e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get index descending station of this line.
     *
     * @param station the station
     * @return the last station
     */
    public Station lastStation(Station station) throws StationNotInLineError {
        if (!this.equals(station.getLine())) {
            throw new StationNotInLineError(this, station);
        }
        int index = stations.indexOf(station);
        if (!this.isLoop()) {
            if (index <= 0) {
                return null;
            }
            return stations.get(index - 1);
        } else {
            return stations.get((index - 1 + getStationCount()) % getStationCount());
        }
    }

    /**
     * Get critical stations.
     * Critical station is transfer station or end station.
     *
     * @return A list of stations.
     */
    public List<Station> getNextCriticalStation(Station station) {
        List<Station> result = new ArrayList<>();
        Station leftStation = station;
        while (true) {
            if (!leftStation.equals(station) && (leftStation.isTransferStation()
                    || leftStation.isEnd())) {
                result.add(leftStation);
                break;
            }
            try {
                if ((!this.isLoop() && getStationIndex(leftStation) == getStationCount() - 1)
                        || !leftStation.isPassable()) {
                    break;
                }
            } catch (StationNotInLineError e) {
                e.printStackTrace();
            }
            try {
                leftStation = nextStation(leftStation);
            } catch (StationNotInLineError e) {
                e.printStackTrace();
            }
        }
        Station rightStation = station;
        while (true) {
            if (!rightStation.equals(station) && (rightStation.isTransferStation()
                    || rightStation.isEnd())) {
                if (!result.contains(rightStation)) {
                    result.add(rightStation);
                    break;
                }
            }

            try {
                if ((!this.isLoop() && getStationIndex(rightStation) == 0)
                        || !rightStation.isPassable()) {
                    break;
                }
            } catch (StationNotInLineError e) {
                e.printStackTrace();
            }

            try {
                rightStation = lastStation(rightStation);
            } catch (StationNotInLineError e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * Get the first station
     *
     * @return The first station of line
     * @throws EmptyLineError Line has no station
     */
    public Station getFirstStation() throws EmptyLineError {
        if (getStationCount() <= 0) {
            throw new EmptyLineError(this);
        }
        return stations.get(0);
    }

    /**
     * Get the last station
     *
     * @return The last station of line
     * @throws EmptyLineError Line has no station
     */
    public Station getLastStation() throws EmptyLineError {
        if (getStationCount() <= 0) {
            throw new EmptyLineError(this);
        }
        return stations.get(getStationCount() - 1);
    }

    /**
     * Get the shortest distance from one station to another, two station should belong to this line.
     *
     * @param startStation start station
     * @param endStation   end station
     * @return distance
     */
    public int getDistance(String startStation, String endStation) {
        Station start = RailSystem.getInstance().getStation(startStation, this.getName());
        Station end = RailSystem.getInstance().getStation(endStation, this.getName());
        try {
            return getDistance(start, end);
        } catch (StationNotInLineError e) {
            e.printStackTrace();
        }
        return -1;
    }

    /**
     * Get the shortest distance from one station to another, two station should belong to this line.
     *
     * @param startStation start station
     * @param endStation   end station
     * @return distance
     * @throws StationNotInLineError Station not in this line
     */
    public int getDistance(Station startStation, Station endStation) throws StationNotInLineError {
        // System.out.println("getDistance: from " + startStation + " to " + endStation);
        if (!this.equals(startStation.getLine())) {
            throw new StationNotInLineError(this, startStation);
        }
        if (!this.equals(endStation.getLine())) {
            throw new StationNotInLineError(this, endStation);
        }
        int start, end;
        try {
            start = this.getStationIndex(startStation);
            end = this.getStationIndex(endStation);
        } catch (StationNotInLineError e) {
            e.printStackTrace();
            return -1;
        }
        int distance = 0;
        if (!this.isLoop()) {
            boolean upward = start < end;
            int ptr = start;
            while (true) {
                Station station = stations.get(ptr);
                if (station.equals(endStation)) {
                    return distance;
                }
                if (upward) {
                    distance += station.distanceTo(nextStation(station));
                    ptr++;
                } else {
                    distance += station.distanceTo(lastStation(station));
                    ptr--;
                }
            }

        } else {
            int ptr = start;
            int leftDistance = 0;
            while (true) {
                Station station = stations.get(ptr);
                // System.out.println("left station: " + station);
                if (station.equals(endStation)) {
                    distance = leftDistance;
                    break;
                }
                leftDistance += station.distanceTo(nextStation(station));
                ptr++;
                if (ptr >= this.getStationCount()) {
                    ptr = 0;
                }
            }
            ptr = start;
            int rightDistance = 0;
            while (true) {
                Station station = stations.get(ptr);
                // System.out.println("right station: " + station);
                if (stations.get(ptr).equals(endStation)) {
                    distance = Math.min(distance, rightDistance);
                    break;
                }
                rightDistance += station.distanceTo(lastStation(station));
                ptr--;
                if (ptr <= 0) {
                    ptr = this.getStationCount() - 1;
                }
            }
            return distance;
        }
    }

    /**
     * Get path from onr station to another station, two station should belong to this line.
     *
     * @param startStation start station
     * @param endStation   end station
     * @return path
     */
    public RailPath getPath(String startStation, String endStation) {
        Station start = RailSystem.getInstance().getStation(startStation, this.getName());
        Station end = RailSystem.getInstance().getStation(endStation, this.getName());
        try {
            return getPath(start, end);
        } catch (StationNotInLineError e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Get path from onr station to another station, two station should belong to this line.
     *
     * @param startStation start station
     * @param endStation   end station
     * @return path
     * @throws StationNotInLineError Station not in this line
     */
    public RailPath getPath(Station startStation, Station endStation) throws StationNotInLineError {
        if (!this.equals(startStation.getLine())) {
            throw new StationNotInLineError(this, startStation);
        }
        if (!this.equals(endStation.getLine())) {
            throw new StationNotInLineError(this, endStation);
        }
        int start, end;
        try {
            start = this.getStationIndex(startStation);
            end = this.getStationIndex(endStation);
        } catch (StationNotInLineError e) {
            e.printStackTrace();
            return null;
        }
        RailPath result = new RailPath(startStation, endStation);
        if (!this.isLoop()) {
            boolean upward = start < end;
            int ptr = start;
            while (true) {
                Station station = stations.get(ptr);
                if (station.equals(endStation)) {
                    return result;
                }
                if (upward) {
                    Station nextStation = nextStation(station);
                    try {
                        result.addStation(nextStation, station.distanceTo(nextStation));
                    } catch (StationNotPassableError e) {
                        e.printStackTrace();
                    }
                    ptr++;
                } else {
                    Station lastStation = lastStation(station);
                    try {
                        result.addStation(lastStation, station.distanceTo(lastStation));
                    } catch (StationNotPassableError e) {
                        e.printStackTrace();
                    }
                    ptr--;
                }
            }

        } else {
            int ptr = start;
            RailPath leftPath = new RailPath(startStation, endStation);
            while (true) {
                Station station = stations.get(ptr);
                // System.out.println("left station: " + station);
                if (station.equals(endStation)) {
                    result = leftPath;
                    break;
                }
                Station nextStation = nextStation(station);
                try {
                    leftPath.addStation(nextStation, station.distanceTo(nextStation));
                } catch (StationNotPassableError e) {
                    e.printStackTrace();
                }
                ptr++;
                if (ptr >= this.getStationCount()) {
                    ptr = 0;
                }
            }
            ptr = start;
            RailPath rightPath = new RailPath(startStation, endStation);
            while (true) {
                Station station = stations.get(ptr);
                // System.out.println("right station: " + station);
                if (stations.get(ptr).equals(endStation)) {
                    result = rightPath.getDistance() > result.getDistance() ? rightPath : result;
                    break;
                }
                Station lastStation = lastStation(station);
                try {
                    rightPath.addStation(lastStation, station.distanceTo(lastStation));
                } catch (StationNotPassableError e) {
                    e.printStackTrace();
                }
                ptr--;
                if (ptr <= 0) {
                    ptr = this.getStationCount() - 1;
                }
            }
            return result;
        }
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder();
        message.append(name).append(", ");
        try {
            message.append("From ").append(getFirstStation().getName()).append(" ");
        } catch (EmptyLineError e) {
            message.append("null ");
        }
        try {
            message.append("to ").append(isLoop() ? getFirstStation().getName() : getLastStation().getName()).append(", ");
        } catch (EmptyLineError e) {
            message.append("null, ");
        }
        message.append("Fare: ").append(fare).append(", ");
        try {
            message.append("Length: ").append(isLoop() ? (double) getDistance(getFirstStation(), getFirstStation()) / 1000 : (double) getDistance(getFirstStation(), getLastStation()) / 1000).append(" km\n");
        } catch (StationNotInLineError | EmptyLineError e) {
            e.printStackTrace();
        }
        for (Station station : stations) {
            message.append(station.getName()).append(", ");
        }
        return message.toString();
    }
}
