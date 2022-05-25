package com.samsung.libraryandroid.domain;

public class Type {
    private int id;
    private String name;

    public Type(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public Type(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Type{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
