package com.pichincha.bank.api.account;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class AccountRouterRest {
@Bean
@PriceApiInfo
public RouterFunction<ServerResponse> accountRouterFunction(AccountHandler handler) {
    return route(GET("/api/v1/account"), request -> handler.getAll())
            .andRoute(POST("/api/v1/account"), handler::save)
            .andRoute(PUT("/api/v1/account"), handler::update)
            .andRoute(DELETE("/api/v1/account/{id}"), handler::delete);
    }
}
