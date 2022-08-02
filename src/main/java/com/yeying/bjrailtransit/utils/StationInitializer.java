package com.yeying.bjrailtransit.utils;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.yeying.bjrailtransit.exceptions.DuplicateIDError;
import com.yeying.bjrailtransit.exceptions.EmptyStationInfoError;
import com.yeying.bjrailtransit.exceptions.StationNotFoundError;
import org.json.JSONArray;
import org.json.JSONObject;

public class StationInitializer {
    private static final String stationsDirectoryPath = ClassLoader.getSystemResource("stations").getPath();

    public static void initializeAllStations() throws IOException, EmptyStationInfoError, StationNotFoundError, DuplicateIDError {
        System.out.println(stationsDirectoryPath);
        File stationsDirectory = new File(stationsDirectoryPath);
        if (stationsDirectory.isDirectory()) {
            String[] stationsFilePath = stationsDirectory.list();
            assert stationsFilePath != null;
            for (String path :
                    stationsFilePath) {
                System.out.println(path);
                String content = new String(Files.readAllBytes(Paths.get(stationsDirectory + "/" + path)));
                // System.out.println(content);
                JSONObject obj = new JSONObject(content);
                String line = obj.getString("line");
                System.out.println(line);
                JSONArray stationsData = obj.getJSONArray("stations");
                for (Object stationData :
                        stationsData) {
                    JSONObject stationObject = (JSONObject) stationData;
                    String name = stationObject.getString("name");
                    Station currentStation = RailSystem.getInstance().getStation(name, line);
                    JSONArray linksData = stationObject.getJSONArray("links");
                    for (Object linkData:
                         linksData) {
                        JSONObject linkObject = (JSONObject) linkData;
                        String linkName = linkObject.getString("name");
                        String linkLine = linkObject.getString("line");
                        int distance = linkObject.getInt("distance");
                        currentStation.setLink(linkName, linkLine, distance);
                    }
                    System.out.println(currentStation);
                }
            }
        }
    }
}
