package com.example.demo2_1;

public class Players {
    private final int id;
    private  final String figure;

    public Players(int id, String figure) {
        this.id = id;
        this.figure = figure;
    }

    public int getId() {
        return id;
    }
    public String getImage() {
        return figure;
    }

}
