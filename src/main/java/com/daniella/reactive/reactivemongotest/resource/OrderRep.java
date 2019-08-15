package com.daniella.reactive.reactivemongotest.resource;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OrderRep extends ReactiveMongoRepository<Order, String> {


}
