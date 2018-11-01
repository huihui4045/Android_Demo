package com.alizhezi.demo.build;

public class Room {

    private String window;

    private String floor;

    public void apply(WorkBuilder.RoomParams params) {
        this.window = params.window;
        this.floor = params.floor;
    }

    @Override
    public String toString() {
        return "Room{" +
                "window='" + window + '\'' +
                ", floor='" + floor + '\'' +
                '}';
    }
}
