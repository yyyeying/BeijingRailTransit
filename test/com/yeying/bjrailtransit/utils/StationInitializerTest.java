package com.yeying.bjrailtransit.utils;

import com.yeying.bjrailtransit.exceptions.DuplicateIDError;
import com.yeying.bjrailtransit.exceptions.EmptyStationInfoError;
import com.yeying.bjrailtransit.exceptions.StationNotFoundError;
import org.junit.Test;

import java.io.IOException;

public class StationInitializerTest {

    @Test
    public void initializeAllStations() throws IOException, EmptyStationInfoError, StationNotFoundError, DuplicateIDError {
        StationInitializer.initializeAllStations();
    }
}