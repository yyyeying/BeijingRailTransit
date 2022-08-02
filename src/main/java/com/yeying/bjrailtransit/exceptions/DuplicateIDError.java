package com.yeying.bjrailtransit.exceptions;

public class DuplicateIDError extends Exception{
    private final String[] names;
    private final String[] lines;

    public DuplicateIDError(String[] names, String[] lines) {
        this.names = names;
        this.lines = lines;
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder("Duplicate ID: ");
        for (int i = 0; i < names.length; i++) {
            message.append(names[i]).append(" ");
            message.append(lines[i]).append(", ");
        }
        return message.toString();
    }
}
