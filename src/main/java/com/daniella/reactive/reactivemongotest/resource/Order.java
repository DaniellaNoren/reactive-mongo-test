package com.daniella.reactive.reactivemongotest.resource;

import org.springframework.data.mongodb.core.mapping.Document;


public class Order {

    private String id;
    private String dish;
    private int price;
    private String extra;
    private Station station;

    public Order(String id, String dish, int price, String extra, Station station) {
        this.id = id;
        this.dish = dish;
        this.price = price;
        this.extra = extra;

        this.station = station;
    }

    public Order() {
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getExtra(){
        return extra;
    }

    public void setExtra(String extra){
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", dish='" + dish + '\'' +
                ", price=" + price + '\'' +
                ", extra=" + extra + '\'' +
                ", station="+ station.getName() +
                '}';
    }
}
