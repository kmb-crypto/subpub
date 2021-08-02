package main.gear;

import main.service.LoggerService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.atomic.AtomicLong;


@Component
public class MessageIdHolder {
    private AtomicLong id;
    private final Logger logger;

    @Autowired
    public MessageIdHolder(final LoggerService loggerService) {
        this.logger = loggerService.getLogger();
    }

    @PostConstruct
    private void setId() {
        id = new AtomicLong(1);
        logger.info("MessageHolder initialized with start value: " + id.get());
    }

    public long getId() {
        return id.getAndIncrement();
    }

}
