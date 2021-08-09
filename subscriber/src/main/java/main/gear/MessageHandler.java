package main.gear;

import main.provider.MessageReceiverProvider;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.Socket;

public class MessageHandler extends Thread {
    private final MessageReceiverProvider messageReceiverProvider;
    private final Socket socket;
    private final Logger logger;

    public MessageHandler(Socket socket, MessageReceiverProvider messageReceiverProvider, Logger logger) {
        this.messageReceiverProvider = messageReceiverProvider;
        this.socket = socket;
        this.logger = logger;
    }


    @Override
    public void run() {
        String message = "";
        try {
            message = receiveMessage();
        } catch (IOException e) {
            logger.info("MessageHandler: Can't receive message -> " + e.getMessage());
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                logger.info("MessageHandler: Can't close socket -> " + e.getMessage());
            }
        }
        //System.out.println(Thread.currentThread().getName() + " " + message);
        if (!message.equals("")) {
            persistMessage(message);
        } else {
            return;
        }
    }

    private String receiveMessage() throws IOException {
        MessageReceiver messageReceiver = messageReceiverProvider.getMessageReceiver(socket);
        try {
            return messageReceiver.getMessage();
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    private void persistMessage(String message) {
        System.out.println(message);
    }
}
