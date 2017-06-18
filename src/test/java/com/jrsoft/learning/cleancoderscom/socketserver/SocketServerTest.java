package com.jrsoft.learning.cleancoderscom.socketserver;

import de.bechte.junit.runners.context.HierarchicalContextRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(HierarchicalContextRunner.class)
public class SocketServerTest {
    private ClosingSocketService service;
    private SocketServer server;
    private int port;

    @Before
    public void setUp() {
        port = 8042;
    }

    public class WithClosingSocketsService {

        @Before
        public void setUp() throws Exception {
            service = new ClosingSocketService();
            server = new SocketServer(port, service);
        }

        @After
        public void tearDown() throws Exception {
            server.stop();
        }

        @Test
        public void instantiate() {

            assertEquals(port, server.getPort());
            assertEquals(service, server.getService());
        }

        @Test
        public void canStartAndStopServer() throws Exception {
            server.start();
            assertTrue(server.isRunning());
            server.stop();
            assertFalse(server.isRunning());
        }

        @Test
        public void acceptIncommingConnection() throws Exception {
            server.start();
            new Socket("localhost", port);
            synchronized (service) {
                service.wait();
            }
            server.stop();

            assertEquals(1, service.connections);
        }

        @Test
        public void acceptMultipleIncommingConnections() throws Exception {
            server.start();
            new Socket("localhost", port);
            synchronized (service) {
                service.wait();
            }
            new Socket("localhost", port);
            synchronized (service) {
                service.wait();
            }
            server.stop();

            assertEquals(2, service.connections);
        }
    }

    public class WithReadingSockerService {
        private ReadingSocketService readingService;

        @Before
        public void setUp() throws Exception {
            readingService = new ReadingSocketService();
            server = new SocketServer(port, readingService);
        }

        @After
        public void tearDown() throws Exception {
            server.stop();
        }

        @Test
        public void canSendAndReceiveData() throws Exception {
            server.start();
            Socket socket = new Socket("localhost", port);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("hello\n" .getBytes());
            synchronized (readingService) {
                readingService.wait();
            }
            server.stop();

            assertEquals("hello", readingService.message);

        }
    }

    public class WithEchoSockerService {
        private EchoSocketService echoService;

        @Before
        public void setUp() throws Exception {
            echoService = new EchoSocketService();
            server = new SocketServer(port, echoService);
        }

        @After
        public void tearDown() throws Exception {
            server.stop();
        }

        @Test
        public void canEcho() throws Exception {
            server.start();
            Socket socket = new Socket("localhost", port);
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("echo\n" .getBytes());
            synchronized (echoService) {
                echoService.wait();
            }
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String response = br.readLine();

            assertEquals("echo", response);

            server.stop();
        }
    }

    public static class ClosingSocketService extends TestSocketService {
        public int connections;

        @Override
        protected void doService(Socket s) throws IOException {
            connections++;
        }
    }

    public static class ReadingSocketService extends TestSocketService {
        public String message;

        @Override
        protected void doService(Socket s) throws IOException {
            InputStream is = s.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);

            message = br.readLine();
        }
    }

    public static class EchoSocketService extends TestSocketService {

        @Override
        protected void doService(Socket s) throws IOException {
            InputStream is = s.getInputStream();
            BufferedReader br = new BufferedReader( new InputStreamReader(is));
            String message = br.readLine();
            OutputStream os = s.getOutputStream();
            os.write(message.getBytes());
            os.flush();
        }
    }

    public static abstract class TestSocketService implements SocketService {
        @Override
        public void serve(Socket s) {
            try {
                doService(s);
                synchronized (this) {
                    notify();
                }
                s.close();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }

        protected abstract void doService(Socket s) throws IOException;
    }
}
