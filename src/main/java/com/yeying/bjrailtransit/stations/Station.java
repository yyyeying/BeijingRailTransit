package com.yeying.bjrailtransit.stations;

import com.yeying.bjrailtransit.exceptions.lines.StationNotInLineError;
import com.yeying.bjrailtransit.exceptions.stations.EmptyStationInfoError;
import com.yeying.bjrailtransit.exceptions.stations.StationNotFoundError;
import com.yeying.bjrailtransit.exceptions.stations.StationNotPassableError;
import com.yeying.bjrailtransit.lines.RailLine;
import com.yeying.bjrailtransit.system.RailPath;
import com.yeying.bjrailtransit.system.RailSystem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Station {
    private int id;
    private String name;
    private RailLine line;
    private boolean open;
    private boolean passable;
    private Map<Station, Integer> links;

    public Station(String name, String line) {
        this.setName(name);
        this.setLine(line);
        this.setId();
        this.setOpen(true);
        this.setPassable(true);
        links = new HashMap<>();
        // System.out.println(this);
    }

    private void setId() {
        if (name == null || line == null) {
            return;
        }
        id = StationIDHandler.generateID(name, line.getName());
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

    public RailLine getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = RailSystem.getInstance().getLine(line);
    }

    /**
     * Whether the station is open.
     * 未开通的车站是false
     *
     * @return boolean
     */
    public boolean isOpen() {
        return open;
    }

    /**
     * Set whether the station is open
     * 未开通的车站是false
     *
     * @param open boolean
     */
    public void setOpen(boolean open) {
        this.open = open;
    }

    /**
     * Whether the station is passable.
     * 未开通但位于线路中间的车站是true
     *
     * @return boolean
     */
    public boolean isPassable() {
        return passable;
    }

    /**
     * Whether the station is passable.
     * 未开通但位于线路中间的车站是true
     *
     * @param passable boolean
     */
    public void setPassable(boolean passable) {
        this.passable = passable;
    }

    public Map<Station, Integer> getLinks() {
        return links;
    }

    public int getLinksCount() {
        int linksCount = 0;
        for (Map.Entry<Station, Integer> element :
                links.entrySet()) {
            Station station = element.getKey();
            if (station.isPassable()) {
                linksCount++;
            }
        }
        return linksCount;
    }

    /**
     * Whether the station is end.
     * 一般的终点站会被列为end。
     * 如环球度假区，不是end。
     *
     * @return boolean
     */
    public boolean isEnd() {
        return getLinksCount() == 1;
    }

    /**
     * Whether station is transfer station
     *
     * @return boolean
     */
    public boolean isTransferStation() {
        for (Map.Entry<Station, Integer> element : links.entrySet()) {
            Station station = element.getKey();
            if (!station.getLine().equals(this.getLine())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Get critical stations.
     * Critical station is transfer station or end station.
     *
     * @return A list of stations.
     */
    public List<Station> getNextCriticalStation() {
        return this.line.getNextCriticalStation(this);
    }

    /**
     * Get distance to another station.
     *
     * @param station A station links to this station.
     * @return distance
     */
    public int distanceTo(Station station) {
        return this.links.get(station);
    }

    public Station nextStation() {
        try {
            return this.line.nextStation(this);
        } catch (StationNotInLineError e) {
            e.printStackTrace();
        }
        return null;
    }

    public Station lastStation() {
        try {
            return this.line.lastStation(this);
        } catch (StationNotInLineError e) {
            e.printStackTrace();
        }
        return null;
    }

    public RailPath getPath(Station station) {
        try {
            return this.line.getPath(this, station);
        } catch (StationNotInLineError e) {
            e.printStackTrace();
        }
        return null;
    }

    private void setLinkWithoutCatch(String name, String line, int distance) throws StationNotFoundError, EmptyStationInfoError, StationNotPassableError {
        if (name == null || line == null) {
            throw new EmptyStationInfoError(name, line);
        }
        Station destinationStation = RailSystem.getInstance().getStation(name, line);
        if (destinationStation == null) {
            throw new StationNotFoundError(name, line);
        }
        if (!RailSystem.getInstance().getLine(line).equals(this.getLine()) && !destinationStation.isPassable()) {
            throw new StationNotPassableError(name, line);
        }
        links.put(destinationStation, distance);
    }

    public void setLink(String name, String line, int distance) {
        try {
            setLinkWithoutCatch(name, line, distance);
        } catch (EmptyStationInfoError | StationNotFoundError e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (StationNotPassableError e) {
            System.out.println("set a link with not passable station");
        }
    }

    public String fullInfo() {
        StringBuilder message = new StringBuilder();
        message.append("ID: ").append(id).append(", ");
        message.append("name: ").append(name).append(", ");
        message.append("line: ").append(line.getName()).append(", ");
        message.append("links: ").append(links.size()).append(", ");
        for (Map.Entry<Station, Integer> link :
                links.entrySet()) {
            String linkMsg = "[" + link.getKey().name + ", " + link.getKey().line.getName() + ", " + link.getValue() + "]";
            message.append(linkMsg);
        }
        return message.toString();
    }

    @Override
    public String toString() {
        return "[" + name + ", " + line.getName() + "]";
    }
}
