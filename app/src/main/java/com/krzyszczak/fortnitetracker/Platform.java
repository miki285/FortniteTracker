package com.krzyszczak.fortnitetracker;

public enum Platform {
    PC("pc"),
    XBOX("xbl"),
    PS4("psn");

    private final String value;

    Platform(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
