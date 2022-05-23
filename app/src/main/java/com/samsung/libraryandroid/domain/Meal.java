package com.samsung.libraryandroid.domain;

public class Meal {
    private int id;

    private String name;

    private Country country;

    private Type type;

    private Time time;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public Type getType() {
        return type;
    }

    public Time getTime() {
        return time;
    }

    public Meal(int id, String name, Country country, Type type, Time time) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.type = type;
        this.time = time;
    }

    public Meal(String name, Country country, Type type, Time time) {
        this.name = name;
        this.country = country;
        this.type = type;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country +
                ", type=" + type +
                ", time=" + time +
                '}';
    }
}
