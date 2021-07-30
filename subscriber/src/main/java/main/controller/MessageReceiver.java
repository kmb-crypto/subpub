package main.controller;

import main.service.SocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.Socket;
import java.nio.channels.AcceptPendingException;

@Component
public class MessageReceiver {
    private final SocketService socketService;

    @Autowired
    public MessageReceiver(final SocketService socketService) {
        this.socketService = socketService;
    }

    public void getMessage() {
        Socket socket = socketService.getOptionalSocket().orElseThrow(AcceptPendingException::new);

    }
}
