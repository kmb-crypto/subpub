package main;

import main.service.LoggerService;
import main.service.MessagePersistService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SubMain implements CommandLineRunner {
    @Autowired
    private LoggerService loggerService;

    @Autowired
    private MessagePersistService messagePersistService;


    public static void main(String[] args) {
        SpringApplication.run(SubMain.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Logger logger = loggerService.getLogger();
        logger.info("Subscriber started");
        messagePersistService.startMessagePersistServer();
    }
}
