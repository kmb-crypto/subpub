package main.provider;

import main.gear.MessageHandler;
import main.service.LoggerService;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.net.Socket;

@Component
public class MessageHandlerProvider {
    private final MessageReceiverProvider messageReceiverProvider;
    private final MessageRecorderProvider messageRecorderProvider;
    private final Logger logger;

    public MessageHandlerProvider(final MessageReceiverProvider messageReceiverProvider,
                                  final MessageRecorderProvider messageRecorderProvider,
                                  final LoggerService loggerService) {
        this.messageReceiverProvider = messageReceiverProvider;
        this.messageRecorderProvider = messageRecorderProvider;
        this.logger = loggerService.getLogger();
    }

    @Bean
    @Scope("prototype")
    public MessageHandler getMessageHandler(Socket socket) {
        return new MessageHandler(socket, messageReceiverProvider, messageRecorderProvider, logger);
    }
}
