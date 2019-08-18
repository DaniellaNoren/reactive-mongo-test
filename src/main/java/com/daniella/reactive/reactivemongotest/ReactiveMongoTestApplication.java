package com.daniella.reactive.reactivemongotest;

import com.daniella.reactive.reactivemongotest.resource.Order;
import com.daniella.reactive.reactivemongotest.resource.OrderRep;
import com.daniella.reactive.reactivemongotest.resource.Station;
import com.daniella.reactive.reactivemongotest.resource.StationType;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class ReactiveMongoTestApplication {

	@Bean
	CommandLineRunner orders(OrderRep orderRep){
		return arg -> {
			orderRep
					.deleteAll()
					.subscribe(null, null, () -> {

						Stream.of(new Order(UUID.randomUUID().toString(), "Chicken", 60, "extra cheese", new Station(UUID.randomUUID().toString(), StationType.COLD, "COLD")), new Order(UUID.randomUUID().toString(), "Salad", 60, "", new Station(UUID.randomUUID().toString(), StationType.COLD, "COLD")),new Order(UUID.randomUUID().toString(), "Pancakes", 60, "", new Station(UUID.randomUUID().toString(), StationType.COLD, "COLD")),new Order(UUID.randomUUID().toString(), "Beef", 60, "no meat", new Station(UUID.randomUUID().toString(), StationType.COLD, "COLD")))
								.forEach(order -> {
									orderRep.save(order)
											.subscribe(o -> System.out.println(o));
								});

					});
		};
	}

	public static void main(String[] args) {
		SpringApplication.run(ReactiveMongoTestApplication.class, args);
	}

}
