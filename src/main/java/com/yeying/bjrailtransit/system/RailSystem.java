package com.yeying.bjrailtransit.system;

import com.yeying.bjrailtransit.exceptions.DuplicateIDError;
import com.yeying.bjrailtransit.exceptions.stations.StationNotPassableError;
import com.yeying.bjrailtransit.stations.Station;
import com.yeying.bjrailtransit.stations.StationIDHandler;

import java.util.*;

public class RailSystem {
    private static RailSystem instance = new RailSystem();
    private Map<Integer, Station> stations;

    private RailSystem() {
        this.stations = new HashMap<>();
    }

    public static RailSystem getInstance() {
        return instance;
    }

    private Station getStationWithoutCatch(String name, String line) throws DuplicateIDError {
        int id = StationIDHandler.generateID(name, line);
        if (!stations.containsKey(id)) {
            Station newStation = new Station(name, line);
            stations.put(id, newStation);
        } else if (!stations.get(id).getName().equals(name)) {
            throw new DuplicateIDError(new String[]{stations.get(id).getName(), name}, new String[]{stations.get(id).getLine(), line});
        }
        return stations.get(id);
    }

    public Station getStation(String name, String line) {
        Station result = null;
        try {
            result = getStationWithoutCatch(name, line);
        } catch (DuplicateIDError e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Get the shortest path from one station to another
     *
     * @param startStation start station
     * @param endStation   end station
     * @return RailPath
     */
    public RailPath shortestPath(Station startStation, Station endStation) {
        Queue<RailPath> queue = new LinkedList<>();
        RailPath basicPath = new RailPath(startStation);
        RailPath result = basicPath;
        int distance = -1;
        queue.offer(basicPath);
        // queue.push(basicPath);
        while (!queue.isEmpty()) {
            RailPath currentPath = queue.poll();
            if (distance > 0 && currentPath.getDistance() > distance) {
                continue;
            }
            Station currentStation = currentPath.getNewestStation();
            if (currentStation.equals(endStation)) {
                if (distance < 0 || currentPath.getDistance() < distance) {
                    result = currentPath;
                    distance = currentPath.getDistance();
                    continue;
                }
            }
            Map<Station, Integer> links = currentStation.getLinks();
            for (Map.Entry<Station, Integer> link :
                    links.entrySet()) {
                Station linkStation = link.getKey();
                int linkDistance = link.getValue();
                if (currentPath.containsStation(linkStation)) {
                    continue;
                }
                RailPath newPath = (RailPath) currentPath.clone();
                try {
                    newPath.addStation(linkStation, linkDistance);
                } catch (StationNotPassableError e) {
                    continue;
                }
                queue.offer(newPath);
            }
        }
        return result;
    }

    public RailPath shortestPath(String startStation, String startLine, String endStation, String endLine) {
        Station start = getStation(startStation, startLine);
        Station end = getStation(endStation, endLine);
        return shortestPath(start, end);
    }

    /**
     * Get the longest path in the system
     *
     * @return RailPath
     */
    public RailPath longestPath() {
        int pathCount = 0;
        RailPath result = new RailPath();
        int distance = -1;
        Stack<RailPath> stack = new Stack<>();
        for (Map.Entry<Integer, Station> element :
                stations.entrySet()) {
            Station station = element.getValue();
            if (station.isOpen() && station.isEnd()) {
                stack.push(new RailPath(station));
            }
        }
        // System.out.println(queue);
        while (!stack.isEmpty()) {
            pathCount++;
            RailPath currentPath = stack.pop();
            Station currentStation = currentPath.getNewestStation();
            // System.out.println("Current station: " + currentStation + "distance: " + currentPath.getDistance());
            if (currentStation.isEnd() && currentPath.getDistance() > 0 && currentPath.getDistance() > distance) {
                result = currentPath;
                distance = currentPath.getDistance();
                System.out.printf("new result: from [%s, %s] to [%s, %s], distance: %d m, stack size: %d\n",
                        result.getStartStation().getName(), result.getStartStation().getLine(),
                        result.getNewestStation().getName(), result.getNewestStation().getLine(),
                        distance, stack.size());
                continue;
            }
            Map<Station, Integer> links = currentStation.getLinks();
            int newPathAppended = 0;
            for (Map.Entry<Station, Integer> link :
                    links.entrySet()) {
                Station linkStation = link.getKey();
                int linkDistance = link.getValue();
                if (currentPath.containsStation(linkStation)) {
                    continue;
                }
                RailPath newPath = (RailPath) currentPath.clone();
                try {
                    newPath.addStation(linkStation, linkDistance);
                } catch (StationNotPassableError e) {
                    continue;
                }
                if (pathCount % 10000000 == 0) {
                    System.out.printf("distance: %d, from [%s, %s] to [%s, %s], longest distance: %d, stack size: %d \n",
                            newPath.getDistance(),
                            newPath.getStartStation().getName(), newPath.getStartStation().getLine(),
                            newPath.getNewestStation().getName(), newPath.getNewestStation().getLine(),
                            distance, stack.size());
                }
                stack.push(newPath);
                newPathAppended++;
            }
            if (newPathAppended == 0 && currentPath.getDistance() > distance) {
                result = currentPath;
                distance = currentPath.getDistance();
                System.out.printf("new result: from [%s, %s] to [%s, %s], distance: %d m, stack size: %d\n",
                        result.getStartStation().getName(), result.getStartStation().getLine(),
                        result.getNewestStation().getName(), result.getNewestStation().getLine(),
                        distance, stack.size());
            }
        }
        return result;
    }
}
