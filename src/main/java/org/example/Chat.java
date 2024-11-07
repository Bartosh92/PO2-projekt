package org.example;

import javax.swing.JTextArea;

//Klasa służąca do stworzenia chatboxa

public class Chat {
        private JTextArea chatArea;
    public Chat(){
        createChat();
    }
    protected JTextArea createChat()
    {
        this.chatArea = new JTextArea(20,50); //tworzy chatbox o podanej wielkosci
        return this.chatArea;
    }
}
