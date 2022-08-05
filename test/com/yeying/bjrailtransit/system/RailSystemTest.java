package com.yeying.bjrailtransit.system;

import com.yeying.bjrailtransit.stations.StationInitializer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RailSystemTest {
    private boolean initialized = false;

    @Test
    public void shortestPath() {
        if (!initialized) {
            StationInitializer.initializeAllStations();
            initialized = true;
        }
        List<String[]> testCase = new ArrayList<>();
        testCase.add(new String[]{"东单", "1号线", "西直门", "2号线"});
        testCase.add(new String[]{"环球度假区", "1号线", "西直门", "2号线"});
        testCase.add(new String[]{"古城", "1号线", "柳芳", "13号线"});
        for (String[] aCase :
                testCase) {
            RailPath shortestPath = RailSystem.getInstance().shortestPath(aCase[0], aCase[1], aCase[2], aCase[3]);
            System.out.println(shortestPath);
        }
    }

    @Test
    public void longestPath() {
        if (!initialized) {
            StationInitializer.initializeAllStations();
            initialized = true;
        }
        RailPath longestPath = RailSystem.getInstance().longestPath();
        System.out.println(longestPath);
    }
}