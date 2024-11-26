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



    @Override
    public void run(){
        try{
            client = new Socket("192.168.56.1", 9999 );
            out = new PrintWriter(client.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            gui = new GUI(this); // Tworzymy obiekt GUI i przekazujemy Client

            String inMessage;
            while((inMessage = in.readLine()) != null){
                System.out.println(inMessage);
                gui.getChat().getMessageArea().append(inMessage + "\n");
            }

        }catch(IOException e){
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
        isRunning = false;
        try{
            in.close();
            out.close();
            gui.closeGui();
            if(!client.isClosed()){
                client.close();
            }
        }catch(IOException e){
            // ignore
        }
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
