package org.example;

import javax.swing.*;
import java.awt.*;

//Klasa służąca do stworzenia chatboxa

public class Chat {
    private JTextArea chatArea;
    private JPanel chat_panel = new JPanel();
    private JPanel message_panel = new JPanel();
    public Chat(){
        createChatArea();
        createMessageArea();
        createChatPanel();
        renderPanel();
    }

    public void createChatArea()
    {
        this.chatArea = new JTextArea(); //tworzy chatbox o podanej wielkosci
        this.chatArea.setSize(new Dimension(600, 240));
        this.chatArea.setLineWrap(true); // Ustawia zwijanie tekstu, gdy tekst jest na końcu ekranu aplikacji
        this.chatArea.setFocusTraversalKeysEnabled(false); //Wyłącza możliwość używania klawiszy nawigacji, takich jak Tab, Shift + Tab, Enter
        this.chatArea.setFont(new Font("Serif", Font.PLAIN, 22));
        this.chatArea.setBackground(new Color(94, 53, 216));
    }

    public void createMessageArea()
    {
        this.message_panel = new JPanel();
        this.message_panel.setLayout(new BoxLayout(message_panel, BoxLayout.X_AXIS)); // Ustawienie BoxLayout poziomo (X)
        this.message_panel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Usunięcie marginesów
        this.message_panel.setPreferredSize(new Dimension(600, 520));
        this.message_panel.setBackground(new Color(85, 39, 227));
    }

    public void createChatPanel(){
        this.chat_panel.setLayout(new BorderLayout()); //ustawia layout
        chat_panel.add(this.message_panel, BorderLayout.NORTH);
        chat_panel.add(new JScrollPane(this.chatArea), BorderLayout.CENTER);
    }

    public JPanel getChatPanel()
    {
        return this.chat_panel;
    }

    public void renderPanel()
    {
        this.chat_panel.revalidate();
        this.chat_panel.repaint();
    }

}