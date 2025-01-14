package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class Client implements Runnable {

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private boolean isRunning;
    GUI gui;
    Login login;

    @Override
    public void run() {
        try {
            client = new Socket("localhost", 9000);
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            gui = new GUI(this);  // Przekazanie `Client` do GUI
            login = new Login();

            String inMessage;
            while ((inMessage = in.readLine()) != null) {
                System.out.println(inMessage);
                if (inMessage.startsWith("USERS_LIST:")) {
                    String[] users = inMessage.substring(11).split(",");
                    ArrayList<String> userList = new ArrayList<>(Arrays.asList(users));
                    gui.users_online.setUsers(userList);
                } else if (inMessage.endsWith("opuscil pokoj!")) {
                    String userLeaving = inMessage.split(" ")[0];
                    gui.users_online.removeUser(userLeaving);
                }else if (inMessage.startsWith("Twój nick został zmieniony na: ")) {
                    String newNick = inMessage.substring(27); // Pobiera nowy nick z wiadomości
                    System.out.println("Nowy nick: " + newNick);
                    JOptionPane.showMessageDialog(gui.frame, "Twój nick został zmieniony na: " + newNick, "Zmiana nicku", JOptionPane.INFORMATION_MESSAGE);
                }
                else {
                    gui.getChat().getMessageArea().append(inMessage + "\n");
                    if (gui.isMinimized()) {
                        SoundPlayer.playNotificationSound("src/main/java/resources/gg.wav");
                    }

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            shutdown();
        }
    }

    public void sendMessageToServer() {
        String message = gui.getChat().getMessage();
        out.println(message);
        if (!message.isEmpty()) {
            gui.getChat().getChatArea().setText("");
        }
    }

    public void shutdown() {
        try {
            if (in != null) in.close();
            if (out != null) out.close();
            if (gui != null) gui.closeGui();
            if (client != null && !client.isClosed()) client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        isRunning = false;
    }
    public void clearChat() {
        gui.getChat().clearChat(); // Wywołanie metody czyszczenia czatu w GUI
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }
}
