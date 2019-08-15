package com.daniella.reactive.reactivemongotest.resource;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/orders")
public class OrderResource {

    private OrderRep orderRep;

    public OrderResource(OrderRep orderRep){
        this.orderRep = orderRep;
    }

    @GetMapping
    public Flux<Order> getAll(){
        return orderRep.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Order> getOne(@PathVariable("id") final String id){
        return orderRep.findById(id);
    }

    @GetMapping("/{id}/events")
    public Flux<OrderEvent> getEvents(@PathVariable("id") final String id){
        return orderRep.findById(id)
                .flatMapMany(order -> {
                    Flux<Long> interval = Flux.interval(Duration.ofSeconds(2));
                    Flux<OrderEvent> orderEventFlux =
                            Flux.fromStream(
                                    Stream.generate(() -> {
                                       return new OrderEvent(order, new Date());
                                    })
                            );
                return Flux.zip(interval, orderEventFlux)
                        .map(obj -> {
                           return obj.getT2();
                        });
                });
    }

//    @PostMapping
//    public Mono<Order> postOne(){
//        return orderRep.
//    }
}
