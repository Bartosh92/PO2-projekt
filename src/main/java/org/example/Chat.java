package org.example;

import javax.swing.*;
import java.awt.*;

//Klasa służąca do stworzenia chatboxa

public class Chat {
        private JTextArea chatArea;
        private JPanel chat_panel = new JPanel();
    public Chat(){
        createChatArea();
        createChatPanel();
        renderPanel();
    }
    public void createChatArea()
    {
        this.chatArea = new JTextArea(); //tworzy chatbox o podanej wielkosci
        this.chatArea.setLineWrap(true); // Ustawia zwijanie tekstu, gdy tekst jest na końcu ekranu aplikacji
        this.chatArea.setFocusTraversalKeysEnabled(false); //Wyłącza możliwość używania klawiszy nawigacji, takich jak Tab, Shift + Tab, Enter
        this.chatArea.setFont(new Font("Serif", Font.PLAIN, 22));
        this.chatArea.setSelectedTextColor(Color.white);
        this.chatArea.setBackground(new Color(83, 35, 217));
    }
    public void createChatPanel(){
        this.chat_panel.setLayout(new BorderLayout()); //ustawia layout
        chat_panel.add(new JScrollPane(this.chatArea), BorderLayout.CENTER); //Dodaje chat do chat_panel
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
