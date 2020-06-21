package com.gnwoo.authservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRuner implements ApplicationRunner {
    @Autowired
    private AuthServer server;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("我自定义的ApplicationRunner事件。。。。。。");
        server.start();
        server.blockUntilShutdown(); // graceful shut down
    }
}