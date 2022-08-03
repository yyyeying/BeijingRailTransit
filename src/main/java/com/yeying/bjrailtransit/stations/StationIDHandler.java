package com.yeying.bjrailtransit.stations;

public class StationIDHandler {
    public static int generateID(String name, String line) {
        int id = 0;
        for (int i = 0; i < name.length(); i++) {
            id *= 2;
            id += name.charAt(i);
        }
        for (int i = 0; i < line.length(); i++) {
            id *= 2;
            id += line.charAt(i);
        }
        // System.out.println(id);
        return id;
    }
}
