package com.pichincha.bank.jpa.config;

import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class H2Server {
    Logger log = LoggerFactory.getLogger(H2Server.class);
    private Server webServer;

    @Value("${h2-server.port}")
    Integer h2ConsolePort;

    @EventListener(ContextRefreshedEvent.class)
    public void start() throws java.sql.SQLException {
        log.info("starting h2 console at port " + h2ConsolePort);
        this.webServer = Server.createWebServer("-webPort", h2ConsolePort.toString(), "-tcpAllowOthers").start();
        log.info(webServer.getURL());
    }

    @EventListener(ContextClosedEvent.class)
    public void stop() {
        log.info("stopping h2 console at port " + h2ConsolePort);
        this.webServer.stop();
    }
}