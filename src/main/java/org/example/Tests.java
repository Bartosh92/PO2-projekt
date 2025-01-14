package org.example;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Tests {

    private Server server;
    private Thread serverThread;

    @BeforeAll
    void setup() {
        server = new Server();
        serverThread = new Thread(server);
        serverThread.start();
    }

    @AfterAll
    void teardown() {
        server.shutDown();
        try {
            serverThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //Test laczenia sie klienta z serwerem
    @Test
    void testClientServerConnection() {
        Server server = new Server();
        Thread serverThread = new Thread(server);
        serverThread.start();

        try {
            Thread.sleep(100);

            try (Socket socket = new Socket("localhost", 9000);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                String welcomeMessage = in.readLine();
                assertEquals("Prosze wprowadzic swoj pseudonim: ", welcomeMessage);


            } catch (IOException e) {
                System.out.println("Nie udało się połączyć z serwerem: " + e.getMessage());
            }
        } catch (InterruptedException e) {
            System.out.println("Wątek został przerwany: " + e.getMessage());
        } finally {
            server.shutDown();
        }
    }



    //Test dla funkcji czyszczacej chat
    @Test
    void testClearChatFunctionality() {
        Client client = new Client();
        Chat chat = new Chat(client);

        chat.getMessageArea().setText("Testowa wiadomość\nDruga linia\n");
        assertFalse(chat.getMessageArea().getText().isEmpty());

        chat.clearChat();

        assertTrue(chat.getMessageArea().getText().isEmpty(), "Obszar wiadomości powinien być pusty po wyczyszczeniu.");
    }


    //Test dla metody shutdown
    @Test
    void testServerShutdown() {

        try {
            Thread.sleep(100);

            // Zamknij serwer
            server.shutDown();

            Thread.sleep(100);

            // Sprawdź, czy gniazdo serwera jest zamknięte
            assertFalse(serverThread.isAlive(), "Wątek serwera powinien zostać zakończony");

        } catch (Exception e) {
            System.out.println("Test zakończony niepowodzeniem: " + e.getMessage());
        }
    }
}
