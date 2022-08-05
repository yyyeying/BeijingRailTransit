package com.yeying.bjrailtransit.exceptions.lines;

import com.yeying.bjrailtransit.lines.RailLine;

public class EmptyLineError extends LineError {
    public EmptyLineError(String line) {
        super(line);
    }

    public EmptyLineError(RailLine line) {
        super(line);
    }

    @Override
    public String toString() {
        return line + " is empty.";
    }
}
