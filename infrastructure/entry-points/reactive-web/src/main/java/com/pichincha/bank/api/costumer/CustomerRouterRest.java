package com.pichincha.bank.api.costumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class CustomerRouterRest {
@Bean
public RouterFunction<ServerResponse> customerRouterFunction(CustomerHandler handler) {
    return route(GET("/api/v1/customer"), request -> handler.getAll())
            .andRoute(POST("/api/v1/customer"), handler::save)
            .andRoute(PUT("/api/v1/customer"), handler::update)
            .andRoute(DELETE("/api/v1/customer/{id}"), handler::delete);
    }
}
