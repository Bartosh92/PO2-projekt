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

public class Server  implements Runnable{

    private ServerSocket server;
    private ArrayList<ConnectionHandler> connections;
    private boolean done;
    private ExecutorService pool;

    public Server(){
        connections = new ArrayList<ConnectionHandler>();
        done = false;
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(9999); //Tworzy gniazdo serwera na porcie 9999
            pool = Executors.newCachedThreadPool(); //Tworzy pulę wątków

            while (!done) {
                Socket client = server.accept(); //Czeka na połączenie od klienta
                ConnectionHandler handler = new ConnectionHandler(client); //Tworzy nowego handlera
                connections.add(handler); //Dodaje handler do listy połączeń
                pool.execute(handler); //Uruchamia handler w osobnym wątku
            }
        } catch (Exception e) {
            shutDown(); //Zamyka serwer w przypadku błędu
        }
    }

    public void broadcast(String message) {
        for (ConnectionHandler handler : connections) {
            if (handler != null) {
                handler.sendMessage(message); //Wysyła wiadomość do każdego połączonego klienta
            }
        }
    }

    public void shutDown(){
        try {
            done = true; //Zatrzymuje serwer
            if (!server.isClosed()) {
                server.close(); //Zamyka gniazdo serwera
            }
            for (ConnectionHandler handler : connections) {
                handler.shutdown(); //Zamyka wszystkie połączenia z klientami
            }
        } catch (IOException e) {
            //
        }
    }



    class ConnectionHandler implements Runnable{

        private Socket client;
        private BufferedReader in; //Pobiera informacje przekazane przez proces (od klienta)
        private PrintWriter out; //Wysyła informacje do procesu (do klienta)
        private String nickname;

        public ConnectionHandler(Socket client)
        {
            this.client = client;
        }

        @Override
        public void run() {
            try {
                out = new PrintWriter(client.getOutputStream(), true); //Inicjalizacja PrintWriter
                in = new BufferedReader(new InputStreamReader(client.getInputStream())); //Inicjalizacja BufferedReader
                out.println("Please enter a nickname: "); //Prośba o nick
                nickname = in.readLine(); //Pobieranie nicku od klienta
                System.out.println(nickname + " connected!");
                broadcast(nickname + " joined the chat!"); //Informowanie o dołączeniu klienta
                String message;
                while ((message = in.readLine()) != null) { //Czekanie na wiadomości
                    if (message.startsWith("/nick")) {
                        //Obsługuje zmianę nicku
                    } else if (message.startsWith("/quit")) {
                        //Obsługuje zakończenie połączenia
                        broadcast(nickname + " left the chat!");
                        shutDown();
                    } else {
                        //Przesyła wiadomość do wszystkich klientów
                        broadcast(nickname + ": " + message);
                    }
                }
            } catch (IOException e) {
                shutdown();
            }
        }

        public void sendMessage(String message) {
            out.println(message); //Wysyła wiadomość do klienta
        }

        public void shutdown() {
            try {
                in.close(); //Zamyka strumień wejściowy
                out.close(); //Zamyka strumień wyjściowy
                if (!client.isClosed()) {
                    client.close(); //Zamyka połączenie
                }
            } catch (IOException e) {
                //
            }
        }
    }

    public static void main(String[] args){
        Server server = new Server();
        server.run(); //Uruchamia serwer
    }
}
