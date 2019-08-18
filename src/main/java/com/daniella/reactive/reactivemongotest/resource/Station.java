package com.daniella.reactive.reactivemongotest.resource;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Station {

    private String id;
    private StationType type;
    private String name;

    public Station(String id, StationType type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public Station(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public StationType getType() {
        return type;
    }

    public void setType(StationType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
