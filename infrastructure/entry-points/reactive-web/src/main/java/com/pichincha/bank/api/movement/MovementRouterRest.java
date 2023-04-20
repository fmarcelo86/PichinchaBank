package com.pichincha.bank.api.movement;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class MovementRouterRest {
    @Bean
    public RouterFunction<ServerResponse> movementRouterFunction(MovementHandler handler) {
        return route(GET("/api/v1/movement"), request -> handler.getAll())
                .andRoute(GET("/api/v1/movement/reports")
                        .and(queryParam("clientId", p -> true))
                        .and(queryParam("startDate", p -> true))
                        .and(queryParam("endDate", p -> true)), handler::report)
                .andRoute(POST("/api/v1/movement"), handler::save)
                .andRoute(PUT("/api/v1/movement"), handler::update)
                .andRoute(DELETE("/api/v1/movement/{id}"), handler::delete);
    }
}
