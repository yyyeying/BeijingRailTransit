package com.yeying.bjrailtransit.exceptions.lines;

import com.yeying.bjrailtransit.lines.RailLine;

public class LineError extends Exception {
    protected final String line;

    public LineError(String line) {
        this.line = line;
    }

    public LineError(RailLine line) {
        this.line = line.getName();
    }

    @Override
    public String toString() {
        return "LineError: " + line;
    }
}
