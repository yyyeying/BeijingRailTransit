package com.yeying.bjrailtransit.system;

import com.yeying.bjrailtransit.stations.StationInitializer;
import org.junit.Test;

public class RailSystemTest {

    @Test
    public void shortestPath() {
        StationInitializer.initializeAllStations();
        RailPath shortestPath1 = RailSystem.getInstance().shortestPath("东单","1号线","西直门","2号线");
        System.out.println(shortestPath1);
        RailPath shortestPath2 = RailSystem.getInstance().shortestPath("环球度假区","1号线","西直门","2号线");
        System.out.println(shortestPath2);
    }

    @Test
    public void longestPath() {
        StationInitializer.initializeAllStations();
        RailPath longestPath = RailSystem.getInstance().longestPath();
        System.out.println(longestPath);
    }
}