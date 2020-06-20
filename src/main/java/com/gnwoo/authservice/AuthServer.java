package com.gnwoo.authservice;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class AuthServer {
    private Server server;
    private static final int port = 8081;

    // main entry (should use spring convention)
    public static void main(String[] args) throws IOException, InterruptedException {
        final AuthServer server = new AuthServer();
        server.start();
        server.blockUntilShutdown(); // graceful shut down
    }

    private void start() throws IOException {
        server = ServerBuilder.forPort(port).addService(new AuthServiceImpl()).build().start(); // loading service
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
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
}
