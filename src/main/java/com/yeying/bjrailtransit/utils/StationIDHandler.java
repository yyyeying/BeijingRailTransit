package com.yeying.bjrailtransit.utils;

public class StationIDHandler {
    public static int generateID(String name, String line) {
        int id = 0;
        for (int i = 0; i < name.length(); i++) {
            id += name.charAt(i);
        }
        for (int i = 0; i < line.length(); i++) {
            id += line.charAt(i);
        }
        // System.out.println(id);
        return id;
    }
}
