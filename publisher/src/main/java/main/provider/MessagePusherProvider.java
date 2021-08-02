package main.provider;

import main.gear.JsonConverter;
import main.gear.MessagePusher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
public class MessagePusherProvider {
    private final MessageProvider messageProvider;
    private final SocketProvider socketProvider;
    private final JsonConverter jsonConverter;

    public MessagePusherProvider(final MessageProvider messageProvider,
                                 final SocketProvider socketProvider,
                                 final JsonConverter jsonConverter) {
        this.messageProvider = messageProvider;
        this.socketProvider = socketProvider;
        this.jsonConverter = jsonConverter;
    }

    @Bean
    @Scope("prototype")
    public MessagePusher getMessageGenerator() {
        return new MessagePusher(messageProvider, socketProvider, jsonConverter);
    }
}
