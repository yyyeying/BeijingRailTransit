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
        testCase.add(new String[]{"古城", "1号线", "环球度假区", "1号线"});
        testCase.add(new String[]{"西直门", "2号线", "西直门", "2号线"});
        testCase.add(new String[]{"安河桥北", "4号线", "天宫院", "4号线"});
        testCase.add(new String[]{"天通苑北", "5号线", "宋家庄", "5号线"});
        testCase.add(new String[]{"金安桥", "6号线", "潞城", "6号线"});
        testCase.add(new String[]{"北京西站", "7号线", "环球度假区", "7号线"});
        testCase.add(new String[]{"朱辛庄", "8号线", "瀛海", "8号线"});
        testCase.add(new String[]{"国家图书馆", "9号线", "郭公庄", "9号线"});
        testCase.add(new String[]{"巴沟", "10号线", "巴沟", "10号线"});
        testCase.add(new String[]{"金安桥", "11号线", "新首钢", "11号线"});
        testCase.add(new String[]{"西直门", "13号线", "东直门", "13号线"});
        testCase.add(new String[]{"善各庄", "14号线", "张郭庄", "14号线"});
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