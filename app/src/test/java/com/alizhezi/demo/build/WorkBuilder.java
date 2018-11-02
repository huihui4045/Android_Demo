package com.alizhezi.demo.build;

public class WorkBuilder {

    private RoomParams params;

    public WorkBuilder( ) {
        this.params = new RoomParams();
    }

    public WorkBuilder makeWindow(String window){

        this.params.window=window;

        return  this;
    }

    public WorkBuilder makeFloor(String floor){

        this.params.floor=floor;

        return this;
    }

    public Room build(){

        Room room=new Room();

        room.apply(params);
        return  room;
    }


     class RoomParams {

       public String window;

       public   String floor;

    }

}
