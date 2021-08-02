package main.model;

import lombok.Getter;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import java.io.IOException;
import java.net.Socket;

@Getter
public class ClientSocket {
    private Socket socket;

    public ClientSocket(final String host, final int port) throws IOException {
        try {
            this.socket = new Socket(host, port);
        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    public void closeClientSocket(){
        IOUtils.closeQuietly(socket);
    }
}
