package main.provider;

import lombok.Setter;
import main.model.ClientSocket;
import main.service.LoggerService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class SocketProvider {

    @Value("${server.host:localhost}")
    @Setter
    private String serverHost;

    @Value("${server.port:8000}")
    @Setter
    private int serverPort;

    private final Logger logger;

    @Autowired
    public SocketProvider(LoggerService loggerService) {
        this.logger = loggerService.getLogger();
    }

    @Bean
    @Scope("prototype")
    public Optional<ClientSocket> getClientSocket() {
        try {
            return Optional.of(new ClientSocket(serverHost, serverPort));
        } catch (IOException e) {
            logger.info("Can't create client socket for host: " + serverHost + ", port: " + serverPort
                    + " " + e.getMessage());
        }
        return Optional.empty();
    }
}
