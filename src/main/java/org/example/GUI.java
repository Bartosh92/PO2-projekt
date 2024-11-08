package org.example;

import javax.swing.*;
import java.awt.*;

//Klasa obsługi okna i wywołania wszystkich pozostałych funkcji

public class GUI {

    JFrame frame = new JFrame(); //Tworzy okno
    JPanel mainPanel = new JPanel();
    Chat chat = new Chat();

    public GUI(){

        Initalize_Window();  // Tworzenie okna
        chat.createChatArea();
        frame.add(chat.getChatPanel());

    }

    void Initalize_Window()
    {
        this.frame.setSize(1200,800);
        Toolkit toolkit = Toolkit.getDefaultToolkit(); // Ta klasa zapewnia dostęp do funkcji zależnych od systemu operacyjnego
        Dimension screenSize = toolkit.getScreenSize(); // Dimension przechowuje width i height, getScreenSize() pobiera wymiary ekranu
        int x = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y); // ustawia lokalizacje okna na x i y (w tym przypadku na środku)
        frame.setTitle("ChatRoom");  //nazwa okna
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Bez tego okno sie nie zamyka ,,X"
        frame.setResizable(false);

        frame.setVisible(true);

    }

}
