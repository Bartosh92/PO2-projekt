package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {

    private ServerSocket server;
    private ArrayList<ClientHandler> connections;
    private ArrayList<String> users;
    private boolean isRunning;
    private ExecutorService pool;

    public Server() {
        connections = new ArrayList<>();
        users = new ArrayList<>();
        isRunning = true;
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(9000);
            pool = Executors.newCachedThreadPool();

            while (isRunning) {
                System.out.println("Czekam na połączenie...");
                Socket client = server.accept();
                System.out.println("Połączono z: " + client.getInetAddress());
                ClientHandler handler = new ClientHandler(client);
                connections.add(handler);
                pool.execute(handler);
            }
        } catch (Exception e) {
            shutDown();
        }
    }

    public void shutDown() {
        try {
            isRunning = false;
            if (!server.isClosed()) {
                server.close();
            }
            for (ClientHandler handler : connections) {
                handler.shutdown();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendToAllClients(String message) {
        for (ClientHandler handler : connections) {
            if (handler != null) {
                handler.sendMessage(message);
            }
        }
    }

    class ClientHandler implements Runnable {

        private Socket client;
        private BufferedReader in;
        private PrintWriter out;
        private String nick;

        public ClientHandler(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                out = new PrintWriter(client.getOutputStream(), true);
                out.println("Prosze wprowadzic swoj pseudonim: ");
                nick = in.readLine();
                System.out.println(nick + " polaczyl sie!");

                synchronized (users) {
                    users.add(nick);
                    updateUsersOnline();
                }

                sendToAllClients(nick + " dolaczyl do pokoju!");
                String message;
                while ((message = in.readLine()) != null) {
                    if (message.startsWith("/exit")) {
                        sendToAllClients(nick + " opuscil pokoj!");
                        synchronized (users) {
                            users.remove(nick);
                            updateUsersOnline();
                        }
                        sendToAllClients(nick + " opuścił czat.");
                        shutdown();
                        break;
                    }
                    // Obsługa prywatnych wiadomości
                    else if (message.startsWith("/priv")) {
                        handlePrivateMessage(message);
                    }
                    else if (message.startsWith("/nickname")) {
                        handleNicknameChange(message);
                    }


                    // Wysyłanie wiadomości do wszystkich
                    else {
                        sendToAllClients(nick + ": " + message);
                    }
                }
            } catch (IOException e) {
                synchronized (users) {
                    users.remove(nick);
                    updateUsersOnline();
                }
                sendToAllClients(nick + " opuścił czat.");
                shutdown();
            }
        }

        // Obsługa prywatnych wiadomości
        private void handlePrivateMessage(String message) {
            String[] parts = message.split(" ", 3);
            if (parts.length == 3) {
                String recipient = parts[1];
                String privateMessage = parts[2];
                sendPrivateMessage(recipient, privateMessage);
            } else {
                out.println("Nieprawidłowy format wiadomości prywatnej.");
            }
        }




        // Wysyłanie prywatnych wiadomości
        private void sendPrivateMessage(String recipient, String message) {
            for (ClientHandler handler : connections) {
                if (handler.nick.equals(recipient)) {
                    handler.sendMessage("(Priv od " + nick + "): " + message);
                    out.println("(Ty do " + recipient + "): " + message);
                    return;
                }
            }
            out.println("Użytkownik " + recipient + " jest offline lub nie istnieje.");
        }
        private void handleNicknameChange(String message) {
            String[] parts = message.split(" ", 2);
            if (parts.length == 2) {
                String newNick = parts[1].trim();
                synchronized (users) {
                    if (!users.contains(newNick)) {
                        sendToAllClients(nick + " zmienia nick na " + newNick);
                        users.remove(nick);
                        users.add(newNick);
                        nick = newNick;
                        updateUsersOnline();
                        out.println("Twój nick został zmieniony na: " + newNick);
                    } else {
                        out.println("Nick " + newNick + " jest już zajęty!");
                    }
                }
            } else {
                out.println("Niepoprawne użycie komendy. Użyj: /nickname <nowy_nick>");
            }
        }

        // Aktualizacja listy użytkowników online
        private void updateUsersOnline() {
            StringBuilder userList = new StringBuilder("USERS_LIST:");
            for (String user : users) {
                userList.append(user).append(",");
            }
            sendToAllClients(userList.toString());
        }

        public void sendMessage(String message) {
            out.println(message);
        }

        public void shutdown() {
            try {
                in.close();
                out.close();
                if (!client.isClosed()) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.run();
    }
}
