package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardHandling implements KeyListener {
    private Chat chat;

    // Konstruktor przyjmujący obiekt Chat
    public KeyboardHandling(Chat chat) {
        this.chat = chat;
    }


    @Override
    public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_ENTER:
                chat.sendMessage();
        }
    }

    @Override
    public void keyReleased(KeyEvent e){}

    @Override
    public void keyTyped(KeyEvent e){}

}
