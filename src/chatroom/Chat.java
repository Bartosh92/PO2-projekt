package chatroom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;

//Klasa służąca do stworzenia chatboxa

public class Chat {
    private JTextArea chatArea = new JTextArea();; //Obszar do wprowadzania wiadomości
    private JTextArea MessageArea = new JTextArea();
    private JPanel chat_panel = new JPanel(); //Główny panel chatu, w którym znajdują się pozostałe obiekty
    private JPanel message_panel = new JPanel(); // Panel w którym znajduje się obszar wyświetlanych wiadomości
    private Client client;

    public Chat(Client client){
        this.client = client;
        createChatArea();
        createMessagePanel();
        createChatPanel();
        createMessageArea();
        renderPanel();
    }


    //Metoda tworząca obszar do wprowadzania wiadomości
    public void createChatArea()
    {
        this.chatArea.setSize(new Dimension(600, 120));
        this.chatArea.setLineWrap(true); // Ustawia zwijanie tekstu, gdy tekst jest na końcu ekranu aplikacji
        this.chatArea.setFocusTraversalKeysEnabled(false); //Wyłącza możliwość używania klawiszy nawigacji, takich jak Tab, Shift + Tab, Enter
        this.chatArea.setFont(new Font("Serif", Font.PLAIN, 22));
        this.chatArea.setBackground(new Color(	249, 234, 225));

        // Rejestracja KeyboardHandling jako nasłuchiwacza
        KeyboardHandling eventHandling = new KeyboardHandling(client); // Przekazujemy obiekt Client
        this.chatArea.addKeyListener(eventHandling);

        // Rejestracja EventHandling jako nasłuchiwacza zdarzeń klawiatury
        //KeyboardHandling eventHandling = new KeyboardHandling(this);
        //this.chatArea.addKeyListener(eventHandling);
    }


    //Metoda tworząca panel, w którym będzie znajdować się MessageArea
    public void createMessagePanel()
    {
        this.message_panel = new JPanel();
        this.message_panel.setLayout(new BoxLayout(message_panel, BoxLayout.X_AXIS)); // Ustawienie BoxLayout poziomo (X)
        this.message_panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Usunięcie marginesów
        this.message_panel.setPreferredSize(new Dimension(600, 580));
        this.message_panel.setBackground(new Color(132, 175, 245));
        this.message_panel.add(new JScrollPane(this.MessageArea), BorderLayout.CENTER);
    }

    //Metoda tworząca Obszar w którym będą wyświetlane wysłane wiadomości
    public void createMessageArea()
    {
        this.MessageArea.setSize(new Dimension(600,580));
        this.MessageArea.setBackground(new Color(132, 175, 245));
        this.MessageArea.setEditable(false); // Blokuje możliwość edycji tekstu dla MessageArea
        this.MessageArea.setFocusable(false); //Usuwa możliwość skupienia na MessageArea
    }

    //Metoda dodająca panel createMessageArea i chatArea do chat_panel
    public void createChatPanel(){
        this.chat_panel.setLayout(new BorderLayout()); //ustawia layout
        this.chat_panel.add(this.message_panel, BorderLayout.NORTH);
        this.chat_panel.add(new JScrollPane(this.chatArea), BorderLayout.CENTER);
    }





    //Metoda zwracająca chat_panel
    public JPanel getChatPanel()
    {
        return this.chat_panel;
    }

    //Metoda zwracająca ChatArea
    public JTextArea getChatArea(){
        return this.chatArea;
    }

    //Metoda zwracająca MessageArea
    public JTextArea getMessageArea(){
        return this.MessageArea;
    }

    //Metoda zwracająca tekst znajdujący się w MessageArea
    public String getMessage(){return this.chatArea.getText().trim();}



    public void renderPanel()
    {
        this.chat_panel.revalidate();
        this.chat_panel.repaint();
    }




}