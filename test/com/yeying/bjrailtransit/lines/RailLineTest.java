package com.yeying.bjrailtransit.lines;

import com.yeying.bjrailtransit.stations.Station;
import com.yeying.bjrailtransit.stations.StationInitializer;
import com.yeying.bjrailtransit.system.RailSystem;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class RailLineTest {
    private boolean initialized = false;

    @Test
    public void testInitialize() {
        if (!initialized) {
            StationInitializer.initializeAllStations();
            initialized = true;
        }
        System.out.println(RailSystem.getInstance().getLine("1号线"));
        System.out.println(RailSystem.getInstance().getLine("2号线"));
        System.out.println(RailSystem.getInstance().getLine("4号线"));
        System.out.println(RailSystem.getInstance().getLine("5号线"));
        System.out.println(RailSystem.getInstance().getLine("6号线"));
        System.out.println(RailSystem.getInstance().getLine("7号线"));
        System.out.println(RailSystem.getInstance().getLine("8号线"));
        System.out.println(RailSystem.getInstance().getLine("9号线"));
        System.out.println(RailSystem.getInstance().getLine("10号线"));
        System.out.println(RailSystem.getInstance().getLine("11号线"));
        System.out.println(RailSystem.getInstance().getLine("13号线"));
        System.out.println(RailSystem.getInstance().getLine("14号线"));
        System.out.println(RailSystem.getInstance().getLine("15号线"));
        System.out.println(RailSystem.getInstance().getLine("16号线"));
        System.out.println(RailSystem.getInstance().getLine("17号线"));
        System.out.println(RailSystem.getInstance().getLine("19号线"));
        System.out.println(RailSystem.getInstance().getLine("房山线"));
        System.out.println(RailSystem.getInstance().getLine("昌平线"));
        System.out.println(RailSystem.getInstance().getLine("亦庄线"));
        System.out.println(RailSystem.getInstance().getLine("燕房线"));
        System.out.println(RailSystem.getInstance().getLine("S1线"));
    }

    @Test
    public void getStationIndex() {
        if (!initialized) {
            StationInitializer.initializeAllStations();
            initialized = true;
        }
        List<String[]> testCases = new ArrayList<>();
        testCases.add(new String[]{"1号线", "古城"});
        testCases.add(new String[]{"2号线", "西直门"});
        testCases.add(new String[]{"4号线", "安河桥北"});
        testCases.add(new String[]{"5号线", "天通苑北"});
        testCases.add(new String[]{"6号线", "金安桥"});
        testCases.add(new String[]{"7号线", "北京西站"});
        testCases.add(new String[]{"8号线", "朱辛庄"});
        testCases.add(new String[]{"9号线", "国家图书馆"});
        testCases.add(new String[]{"10号线", "巴沟"});
        testCases.add(new String[]{"11号线", "模式口"});
        testCases.add(new String[]{"13号线", "西直门"});
        testCases.add(new String[]{"14号线", "善各庄"});
        for (String[] aCase :
                testCases) {
            int index = RailSystem.getInstance().getLine(aCase[0]).getStationIndex(aCase[1]);
            System.out.println(aCase[0] + ", " + aCase[1] + ", index: " + index);
        }
    }

    @Test
    public void nextStation() {
        if (!initialized) {
            StationInitializer.initializeAllStations();
            initialized = true;
        }

        List<String[]> testCases = new ArrayList<>();
        testCases.add(new String[]{"1号线", "古城"});
        testCases.add(new String[]{"2号线", "西直门"});
        testCases.add(new String[]{"2号线", "积水潭"});
        testCases.add(new String[]{"4号线", "安河桥北"});
        testCases.add(new String[]{"5号线", "天通苑北"});
        testCases.add(new String[]{"6号线", "金安桥"});
        testCases.add(new String[]{"7号线", "北京西站"});
        testCases.add(new String[]{"8号线", "朱辛庄"});
        testCases.add(new String[]{"9号线", "国家图书馆"});
        testCases.add(new String[]{"10号线", "巴沟"});
        testCases.add(new String[]{"11号线", "模式口"});
        testCases.add(new String[]{"13号线", "西直门"});
        testCases.add(new String[]{"14号线", "善各庄"});
        for (String[] aCase :
                testCases) {
            Station nextStation = RailSystem.getInstance().getLine(aCase[0]).nextStation(aCase[1]);
            System.out.println("next station of " + aCase[1] + ": " + nextStation);
        }
    }

    @Test
    public void lastStation() {
        if (!initialized) {
            StationInitializer.initializeAllStations();
            initialized = true;
        }
        List<String[]> testCases = new ArrayList<>();
        testCases.add(new String[]{"1号线", "古城"});
        testCases.add(new String[]{"2号线", "西直门"});
        testCases.add(new String[]{"2号线", "鼓楼大街"});
        testCases.add(new String[]{"4号线", "西苑"});
        testCases.add(new String[]{"5号线", "磁器口"});
        testCases.add(new String[]{"6号线", "北海北"});
        testCases.add(new String[]{"7号线", "广渠门外"});
        testCases.add(new String[]{"8号线", "南锣鼓巷"});
        testCases.add(new String[]{"9号线", "北京西站"});
        testCases.add(new String[]{"10号线", "火器营"});
        testCases.add(new String[]{"11号线", "金安桥"});
        testCases.add(new String[]{"13号线", "东直门"});
        testCases.add(new String[]{"14号线", "善各庄"});
        for (String[] aCase :
                testCases) {
            Station nextStation = RailSystem.getInstance().getLine(aCase[0]).lastStation(aCase[1]);
            System.out.println("last station of " + aCase[1] + ": " + nextStation);
        }
    }

    @Test
    public void getNextCriticalStation() {
        if (!initialized) {
            StationInitializer.initializeAllStations();
            initialized = true;
        }
        List<String[]> testCases = new ArrayList<>();
        testCases.add(new String[]{"1号线", "古城"});
        testCases.add(new String[]{"2号线", "西直门"});
        testCases.add(new String[]{"2号线", "鼓楼大街"});
        testCases.add(new String[]{"4号线", "西苑"});
        testCases.add(new String[]{"5号线", "磁器口"});
        testCases.add(new String[]{"6号线", "北海北"});
        testCases.add(new String[]{"7号线", "广渠门外"});
        testCases.add(new String[]{"8号线", "南锣鼓巷"});
        testCases.add(new String[]{"9号线", "北京西站"});
        testCases.add(new String[]{"10号线", "火器营"});
        testCases.add(new String[]{"11号线", "金安桥"});
        testCases.add(new String[]{"13号线", "东直门"});
        testCases.add(new String[]{"14号线", "善各庄"});
        for (String[] aCase :
                testCases) {
            List<Station> nextStation = RailSystem.getInstance().getStation(aCase[1], aCase[0]).getNextCriticalStation();
            StringBuilder message = new StringBuilder();
            message.append("next critical station of [").append(aCase[1]).append(", ").append(aCase[0]).append("]: ");
            for (Station station: nextStation){
                message.append(station.getName()).append(", ");
            }
            System.out.println(message);
        }
    }

    @Test
    public void getDistance() {
        if (!initialized) {
            StationInitializer.initializeAllStations();
            initialized = true;
        }
    }
}