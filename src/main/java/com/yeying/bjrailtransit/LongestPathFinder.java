package com.yeying.bjrailtransit;

import com.yeying.bjrailtransit.stations.StationInitializer;
import com.yeying.bjrailtransit.system.RailPath;
import com.yeying.bjrailtransit.system.RailSystem;

public class LongestPathFinder {
    public static void main(String[] args) {
        StationInitializer.initializeAllStations();
        RailPath longestPath = RailSystem.getInstance().longestPath();
        System.out.println(longestPath);
    }
}
