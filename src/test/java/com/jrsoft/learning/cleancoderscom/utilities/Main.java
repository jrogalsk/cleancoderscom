package com.jrsoft.learning.cleancoderscom.utilities;

import com.jrsoft.learning.cleancoderscom.business.acceptancetests.TestSetup;
import com.jrsoft.learning.cleancoderscom.socketserver.SocketServer;
import com.jrsoft.learning.cleancoderscom.socketserver.SocketService;

import java.io.IOException;

public class Main {
    private SocketService mainService;
    private SocketServer server;

    public static void main(String[] args) throws Exception {
        TestSetup.setupContext();
        SocketServer server = new SocketServer(8080, s -> {
            try {
                String frontPage = getFrontPage();
                String response = makeResponse(frontPage);
                s.getOutputStream().write(response.getBytes());
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        });
        server.start();
    }

    private static String makeResponse(String content) {
        return  "HTTP/1.1 200 OK\n" +
                "Content-Length: " + content.length() + "\n" +
                "\n" +
                content;
    }

    public static String getFrontPage() {
        return "Gunk";
    }
}
