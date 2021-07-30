package main.service;

import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicLong;


@Service
public class MessageIdHolder {
    private AtomicLong id;
    private final Logger logger;

    @Autowired
    public MessageIdHolder(LoggerService loggerService) {
        this.logger = loggerService.getLogger();
    }

    @PostConstruct
    private void setId() {
        id = new AtomicLong(0);
        logger.info("MessageHolder initialized with start value: " + id.get());
    }

    public long getId() {
        return id.getAndIncrement();
    }

}
