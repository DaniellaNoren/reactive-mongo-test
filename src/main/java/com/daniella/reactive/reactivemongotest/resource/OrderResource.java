package com.daniella.reactive.reactivemongotest.resource;

import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.*;

import java.awt.*;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/orders")
public class OrderResource {

    private OrderRep orderRep;
    final FluxProcessor processor;
    final FluxSink sink;

    public OrderResource(OrderRep orderRep){
        this.orderRep = orderRep;
        this.processor = DirectProcessor.create().serialize();
        this.sink = processor.sink();
    }

    @GetMapping
    public Flux<Order> getAll(){
        return orderRep.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Order> getOne(@PathVariable("id") final String id){
        return orderRep.findById(id);
    }

    @GetMapping(value = "/{id}/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
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

    @PostMapping
    public Mono<Order> createOrder(@RequestBody Order order){
        sink.next(order);
        return orderRep.save(order);
    }

    @RequestMapping(value = "/test", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent> sse(){
        return processor.map(o -> {
            return ServerSentEvent.builder(o).build();
        });
    }

//    @PostMapping
//    public Mono<Order> postOne(){
//        return orderRep.
//    }
}
