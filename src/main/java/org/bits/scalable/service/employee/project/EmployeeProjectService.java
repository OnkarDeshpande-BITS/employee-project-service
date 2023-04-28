package org.bits.scalable.service.employee.project;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.util.Arrays;
import java.util.logging.Logger;

public class EmployeeProjectService {
    private static final Logger logger = Logger.getLogger(EmployeeProjectService.class.getName());
    private static final int PORT = 34004;
    private Server server;
    public static void main(String[] args) {
        logger.info("Server startup. Args = " + Arrays.toString(args));
        try {
            final EmployeeProjectService employeeProjectService = new EmployeeProjectService();

            employeeProjectService.start();
            employeeProjectService.blockUntilShutdown();
        } catch(Exception e) {

        }
    }

    private void start() throws Exception {
        logger.info("Starting the grpc server");

        server = ServerBuilder.forPort(PORT)
                .addService(new EmployeeProjectImpl())
                .build()
                .start();

        logger.info("Server started. Listening on port " + PORT);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("*** JVM is shutting down. Turning off grpc server as well ***");
            this.stop();
            System.err.println("*** shutdown complete ***");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

}
