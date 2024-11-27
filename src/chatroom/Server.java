package chatroom;

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

    private ServerSocket server; //Soket serwera
    private ArrayList<ClientHandler> connections; //Lista połaczonych klinetow
    private boolean isRunning; //Flaga infromująca dzialaniu serwera
    private ExecutorService pool; // Odpowiada za tworzenie watkow

    public Server(){
        connections = new ArrayList<ClientHandler>();
        isRunning = true;
    }


    //Metoda wwywolujaca sie w osobnym watku
    @Override
    public void run() {
        try {
            server = new ServerSocket(5000 ); //Tworzy gniazdo serwera na porcie 3333
            pool = Executors.newCachedThreadPool(); //Tworzy pulę wątków

            while (isRunning) {
                Socket client = server.accept(); //Czeka na połączenie od klienta
                ClientHandler handler = new ClientHandler(client); //Tworzy nowy obiekt obslugujacy klienta
                connections.add(handler); //Dodaje handler do listy połączeń
                pool.execute(handler); //Uruchamia handler w osobnym wątku
            }
        } catch (Exception e) {
            shutDown(); //Zamyka serwer w przypadku błędu
        }
    }



    //Metoda zatrzymujaca dzialanie serwera
    public void shutDown(){
        try {
            isRunning = false; //Zatrzymuje serwer
            if (!server.isClosed()) {
                server.close(); //Zamyka gniazdo serwera
            }
            for (ClientHandler handler : connections) {
                handler.shutdown(); //Zamyka wszystkie połączenia z klientami
            }
        } catch (IOException e) {
        }
    }

    //Metoda wyowołująca funkcję sendMessage dla każego połączonego klienta
    public void sendToAllClients(String message) {
        for (ClientHandler handler : connections) {
            if (handler != null) {
                handler.sendMessage(message); //Wysyła wiadomość do każdego połączonego klienta
            }
        }
    }


    //Klasa obslugujaca klienta
    class ClientHandler implements Runnable{

        private Socket client; //gniazdo klienta
        private BufferedReader in; //Pobiera informacje przekazane przez proces (od klienta)
        private PrintWriter out; //Wysyła informacje do procesu (do klienta)
        private String nick; //Pseudonim klienta

        public ClientHandler(Socket client)
        {
            this.client = client;
        }

        @Override
        public void run() {

            try {
                in = new BufferedReader(new InputStreamReader(client.getInputStream())); //Inicjalizacja BufferedReader
                out = new PrintWriter(client.getOutputStream(), true); //Inicjalizacja PrintWriter
                out.println("Prosze wprowadzic swoj pseudonim: "); //Prośba o nick
                nick = in.readLine(); //Pobieranie nicku od klienta
                System.out.println(nick + " polaczyl sie!");
                sendToAllClients(nick + " dolaczyl do pokoju!"); //Informowanie o dołączeniu klienta
                String message;
                while ((message = in.readLine()) != null) { //Czekanie na wiadomości
                    if (message.startsWith("/exit")) {
                        //Obsługuje zakończenie połączenia
                        sendToAllClients(nick + " opuscil pokoj!");
                        shutDown();
                    } else {
                        //Przesyła wiadomość do wszystkich klientów
                        sendToAllClients(nick + ": " + message);
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
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        Server server = new Server();
        server.run(); //Uruchamia serwer
    }
}