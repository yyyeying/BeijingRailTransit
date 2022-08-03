package com.yeying.bjrailtransit.system;

import com.yeying.bjrailtransit.stations.StationInitializer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RailSystemTest {

    @Test
    public void shortestPath() {
        StationInitializer.initializeAllStations();
        List<String[]> testCase = new ArrayList<>();
        testCase.add(new String[]{"东单","1号线","西直门","2号线"});
        testCase.add(new String[]{"环球度假区","1号线","西直门","2号线"});
        testCase.add(new String[]{"朱辛庄","8号线","瀛海","8号线"});
        for (String[] aCase:
             testCase) {
            RailPath shortestPath = RailSystem.getInstance().shortestPath(aCase[0], aCase[1], aCase[2], aCase[3]);
            System.out.println(shortestPath);
        }
    }

    @Test
    public void longestPath() {
        StationInitializer.initializeAllStations();
        RailPath longestPath = RailSystem.getInstance().longestPath();
        System.out.println(longestPath);
    }
}