package main.service;

import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Optional;

@Service
public class SocketService {
    @Value("${socket.server.port:8000}")
    private int serverPort;
    private ServerSocket serverSocket;
    private final Logger logger;

    @Autowired
    public SocketService(final LoggerService loggerService) {
        this.logger = loggerService.getLogger();
    }

    @PostConstruct
    private void createServerSocket() {
        try {
            serverSocket = new ServerSocket(serverPort);
            logger.info("SocketService: socked created on adress: " + serverSocket.getInetAddress()
                    + ", port: " + serverSocket.getLocalPort());
        } catch (IOException e) {
            logger.info("SocketService: Can't create server socket on port: " + serverPort + " -> " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @PreDestroy
    private void closeServerSocket() {
        IOUtils.closeQuietly(serverSocket);
        logger.info("SocketService: server socked closed");
    }

    public Optional<Socket> getOptionalSocket() {
        Socket socket;
        try {
            socket = serverSocket.accept();
        } catch (IOException e) {
            logger.info("SocketService: Can't create socket -> " + e.getMessage());
            return Optional.empty();
        }
        return Optional.of(socket);
    }
}