package main.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class LoggerService {
    private final Logger logger;

    public LoggerService() {
        this.logger = LogManager.getLogger("Publisher INFO");
    }

    public Logger getLogger() {
        return logger;
    }
}
