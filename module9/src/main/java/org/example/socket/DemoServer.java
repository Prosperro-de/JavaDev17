package org.example.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DemoServer {
    private static ExecutorService pool;
    private static final String END_OF_MESSAGE_MARK = "\n";

    public static void main(String[] args) {
        final int MAX_CONNECTIONS = 100;
        final int PORT = 9999;

        startServer(MAX_CONNECTIONS, PORT);
    }

    private static void startServer(int maxConnections, int port) {
        pool = Executors.newFixedThreadPool(maxConnections);

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port: " + port);

            while (true) {
                listenForClients(serverSocket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void listenForClients(ServerSocket serverSocket) {
        try {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected");

            pool.submit(() -> handleClient(clientSocket));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                OutputStream out = clientSocket.getOutputStream()) {
            String receivedMessage = in.readLine();

            if (receivedMessage != null) {
                System.out.println("Client request message: " + receivedMessage);
                String htmlBody = getHtmlResponse();
                String httpResponse = "HTTP/1.1 200 OK" + END_OF_MESSAGE_MARK +
                                      "Content-Type: text/html" + END_OF_MESSAGE_MARK +
                                      "Content-Length: " + htmlBody.length() + END_OF_MESSAGE_MARK +
                                      END_OF_MESSAGE_MARK +
                                      htmlBody;

                out.write(httpResponse.getBytes());
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getHtmlResponse() {
        return """
                        <!DOCTYPE html>
                        <html lang="en">
                        <head>
                            <meta charset="UTF-8">
                            <title>Title</title>
                        </head>
                        <body>
                            <h1>My valid server response</h1>   
                <a href='https://http.cat/200,jpg'>200</a>         
                        </body>
                        </html>
                        """;
    }
}