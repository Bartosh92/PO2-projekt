package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client implements Runnable {

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private boolean isRunning;
    GUI gui;
    Login login;



    @Override
    public void run(){
        try{
            client = new Socket("10.10.0.109", 9000 );
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            gui = new GUI(this); // Tworzymy obiekt GUI i przekazujemy Client
            login = new Login();

            String inMessage;
            while((inMessage = in.readLine()) != null){
                System.out.println(inMessage);
                gui.getChat().getMessageArea().append(inMessage + "\n");
            }

        }catch(IOException e){
            e.printStackTrace();
            shutdown();
        }
    }



    public void sendMessageToServer(){
        String message = gui.getChat().getMessage(); // Pobiera wiadomość z Chatu
        out.println(message);
        if(!message.isEmpty()){
            gui.getChat().getChatArea().setText("");
        }
    }

    public void shutdown(){
        System.out.println("done...");
        try {
            if (in != null) {
                System.out.println("in null");
                in.close();
            }
            if (out != null) {
                System.out.println("out null");
                out.close();
            }
            if (gui != null) {
                System.out.println("gui null");
                gui.closeGui();
            }
            if (client != null && !client.isClosed()) {
                System.out.println("client null i client.isCLoset null");
                client.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        isRunning = false;
    }

    class InputHandler implements Runnable{

        @Override
        public void run(){

            try{
                BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
                while(isRunning)
                {
                    String message = inReader.readLine();
                    out.println(message);

                }
            }catch(IOException e){
                e.printStackTrace();
                shutdown();
            }
        }
    }

    //Metoda zwracająca gui
    public GUI getGUI()
    {
        return this.gui;
    }


    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

}
