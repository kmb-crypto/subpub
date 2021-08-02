package main.service;

import main.gear.MessageHandler;
import main.provider.MessageHandlerProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.Socket;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class MessagePersistService {
    private static final int NUMBER_OF_THREADS = 5;
    private final ExecutorService executorService;
    private final SocketService socketService;
    private final MessageHandlerProvider messageHandlerProvider;

    @Autowired
    public MessagePersistService(SocketService socketService, MessageHandlerProvider messageHandlerProvider) {
        this.socketService = socketService;
        this.messageHandlerProvider = messageHandlerProvider;
        this.executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    }

    public void startMessagePersistServer() {
        while (true) {
            Optional<Socket> optionalSocket = socketService.getOptionalSocket();
            if (optionalSocket.isPresent()) {
                MessageHandler messageHandler = messageHandlerProvider.getMessageHandler(optionalSocket.get());
                executorService.execute(messageHandler);
            } else {
                continue;
            }
        }
    }
}
