package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardHandling implements KeyListener {
    private Client client;

    // Konstruktor przyjmujący obiekt Client
    public KeyboardHandling(Client client) {
        this.client = client;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                client.sendMessageToServer(); // Wywołuje metodę wysyłającą wiadomość do serwera
                break;
            case KeyEvent.VK_RIGHT:
                client.clearChat(); // Wywołuje metodę do czyszczenia czatu
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyTyped(KeyEvent e) {}
}