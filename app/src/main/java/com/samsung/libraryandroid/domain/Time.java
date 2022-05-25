package com.samsung.libraryandroid.domain;

public class Time {
    private int id;
    private String name;

    public Time(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Time(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Time{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
