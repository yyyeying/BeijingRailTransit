package com.yeying.bjrailtransit.stations;


import com.yeying.bjrailtransit.system.RailSystem;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class StationInitializer {
    private static final String stationsDirectoryPath = ClassLoader.getSystemResource("stations").getPath();

    public static void initializeAllStations() {
        System.out.println(stationsDirectoryPath);
        File stationsDirectory = new File(stationsDirectoryPath);
        if (stationsDirectory.isDirectory()) {
            String[] stationsFilePath = stationsDirectory.list();
            assert stationsFilePath != null;
            for (String path :
                    stationsFilePath) {
                System.out.println(path);
                String content = "";
                try {
                    content = new String(Files.readAllBytes(Paths.get(stationsDirectory + "/" + path)));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // System.out.println(content);
                JSONObject obj = new JSONObject(content);
                String line = obj.getString("line");
                System.out.println(line);
                boolean loop = obj.getBoolean("loop");
                JSONArray distances = obj.getJSONArray("distances");
                JSONArray stationsData = obj.getJSONArray("stations");
                for (int i = 0; i < stationsData.length(); i++) {
                    JSONObject stationObject = stationsData.getJSONObject(i);
                    String name = stationObject.getString("name");
                    Station currentStation = RailSystem.getInstance().getStation(name, line);

                    boolean open = stationObject.optBoolean("open", true);
                    if (!open) {
                        assert currentStation != null;
                        currentStation.setOpen(false);
                    }

                    // Last station link
                    int lastStationIndex = -1;
                    int lastDistance = 0;
                    if (i > 0) {
                        lastStationIndex = i - 1;
                        lastDistance = distances.getInt(lastStationIndex);
                    } else if (loop) {
                        lastStationIndex = stationsData.length() - 1;
                        lastDistance = distances.getInt(lastStationIndex);
                    }
                    if (lastStationIndex >= 0) {
                        // System.out.println("Last station index: " + lastStationIndex);
                        String lastStationName = stationsData.getJSONObject(lastStationIndex).getString("name");
                        // int lastDistance = distances.getInt(lastStationIndex);
                        assert currentStation != null;
                        currentStation.setLink(lastStationName, line, lastDistance);
                    }

                    // Next station link
                    int nextStationIndex = -1;
                    int nextDistance = 0;
                    if (i < stationsData.length() - 1) {
                        nextStationIndex = i + 1;
                        nextDistance = distances.getInt(i);
                    } else if (loop) {
                        nextStationIndex = 0;
                        nextDistance = distances.getInt(i);
                    }
                    if (nextStationIndex >= 0) {
                        // System.out.println("Next station index: " + nextStationIndex);
                        String nextStationName = stationsData.getJSONObject(nextStationIndex).getString("name");
                        assert currentStation != null;
                        currentStation.setLink(nextStationName, line, nextDistance);
                    }

                    JSONArray linksData = stationObject.getJSONArray("links");
                    for (Object linkData :
                            linksData) {
                        // Transfer stations
                        JSONObject linkObject = (JSONObject) linkData;
                        String linkName = linkObject.getString("name");
                        String linkLine = linkObject.getString("line");
                        int distance = 0;
                        assert currentStation != null;
                        currentStation.setLink(linkName, linkLine, distance);
                    }
                    System.out.println(currentStation);
                }
            }
        }
    }
}
