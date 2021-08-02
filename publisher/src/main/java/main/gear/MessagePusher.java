package main.gear;

import main.model.ClientSocket;
import main.provider.MessageProvider;
import main.provider.SocketProvider;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Optional;

public class MessagePusher extends Thread {
    private static final int PAUSE_BETWEEN_MESSAGE = 1 * 1000; //milliseconds
    private final MessageProvider messageProvider;
    private final SocketProvider socketProvider;
    private final JsonConverter jsonConverter;

    public MessagePusher(final MessageProvider messageProvider,
                         final SocketProvider socketProvider,
                         final JsonConverter jsonConverter) {
        this.messageProvider = messageProvider;
        this.socketProvider = socketProvider;
        this.jsonConverter = jsonConverter;
    }

    @Override
    public void run() {
        while (true) {
            Optional<ClientSocket> optionalSocket = socketProvider.getClientSocket();
            if (optionalSocket.isEmpty()) {
                continue;
            }

            Socket socket = optionalSocket.get().getSocket();
            String jsonMessage = jsonConverter.Message2JsonString(messageProvider.getMessage());
            System.out.println(Thread.currentThread().getName() + " " + jsonMessage);
            pushJsonMessage(socket, jsonMessage);

            try {
                Thread.sleep(PAUSE_BETWEEN_MESSAGE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void pushJsonMessage(Socket socket, String json) {
        try (InputStream in = socket.getInputStream();
             OutputStream out = socket.getOutputStream()) {
            out.write(json.getBytes());
            out.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
