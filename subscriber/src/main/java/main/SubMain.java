package main;

import main.controller.MessageReceiver;
import main.service.LoggerService;
import main.service.SocketService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.Socket;

@SpringBootApplication
public class SubMain implements CommandLineRunner {
    @Autowired
    private LoggerService loggerService;
    @Autowired
    private MessageReceiver messageReceiver;

    public static void main(String[] args) {
        SpringApplication.run(SubMain.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


        Logger logger = loggerService.getLogger();
        logger.info("Subscriber started");
        messageReceiver.getMessage();
    }
}
