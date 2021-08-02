package main.provider;

import main.gear.MessageReceiver;
import main.service.LoggerService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.Socket;

@Component
public class MessageReceiverProvider {
    private final Logger logger;

    @Autowired
    public MessageReceiverProvider(LoggerService loggerService) {
        this.logger = loggerService.getLogger();
    }

    @Bean
    @Scope("prototype")
    public MessageReceiver getMessageReceiver(Socket socket) {
        return new MessageReceiver(socket);
    }
}
