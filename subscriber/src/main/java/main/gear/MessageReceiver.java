package main.gear;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class MessageReceiver {
    private final Socket socket;

    public MessageReceiver(Socket socket) {
        this.socket = socket;
    }

    public String getMessage() throws IOException {
        StringBuilder jsonMessage = new StringBuilder("");
        try (InputStream in = socket.getInputStream()) {
            int i;
            while ((i = in.read()) != -1) {
                jsonMessage.append((char)i);
            }
            return jsonMessage.toString();
        } catch (IOException e) {
            throw new IOException(e);
        }
    }
}
