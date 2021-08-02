package main.service;

import main.gear.MessagePusher;
import main.provider.MessagePusherProvider;
import main.provider.SocketProvider;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class MessageService {
    private static final int NUMBER_OF_THREADS = 5;
    private final ExecutorService executorService;
    private final MessagePusherProvider messagePusherProvider;
    private final SocketProvider socketProvider;

    public MessageService(final MessagePusherProvider messagePusherProvider,
                          final SocketProvider socketProvider) {
        this.messagePusherProvider = messagePusherProvider;
        this.socketProvider = socketProvider;
        this.executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    }

    public void startMessageGeneratorPool() {
        for (int i = 1; i <= NUMBER_OF_THREADS; i++) {
            MessagePusher messagePusher = messagePusherProvider.getMessageGenerator();
            executorService.execute(messagePusher);
        }
    }


}
