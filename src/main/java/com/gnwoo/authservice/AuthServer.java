package com.gnwoo.authservice;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class AuthServer {
    private Server server;
    private static final int port = 8081;

    @Autowired
    private AuthServiceImpl authService;

    public void start() throws IOException {
        server = ServerBuilder.forPort(port).addService(this.authService).build().start(); // loading service
        System.out.println("server started");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> { // adding termination hook
            try {
                AuthServer.this.stop(); // calling stop() to terminate the server
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
        }));
    }

    private void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS); // shut down with await (why?)
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}
