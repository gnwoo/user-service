package com.gnwoo.userservice;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class UserServer {
    private Server server;
    private static final int port = 8091;

    @Autowired
    private UserServiceImpl userService;

    public void start() throws IOException {
        server = ServerBuilder.forPort(port).addService(this.userService).build().start(); // loading service
        System.out.println("User RPC server started");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> { // adding termination hook
            try {
                UserServer.this.stop(); // calling stop() to terminate the server
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
