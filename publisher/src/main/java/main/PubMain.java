package main;

import main.service.MessageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PubMain implements CommandLineRunner {
    @Autowired
    private MessageFactory messageFactory;

    public static void main(String[] args) {
        SpringApplication.run(PubMain.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("PUB is WORKING");
        while (true) {
            System.out.println(messageFactory.getMessage());
            Thread.sleep(1000);
        }
    }
}
