package com.daniella.reactive.reactivemongotest.resource;

import java.util.Date;

public class OrderEvent {

    private Order order;
    private Date date;

    public OrderEvent(Order order, Date date) {
        this.order = order;
        this.date = date;
    }

    public OrderEvent() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
