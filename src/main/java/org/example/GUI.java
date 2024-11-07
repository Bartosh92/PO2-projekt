package org.example;

import javax.swing.*;
import java.awt.*;

//Klasa obsługi okna i wywołania wszystkich pozostałych funkcji

public class GUI {

    JFrame frame = new JFrame(); //Tworzy okno
    Panels panel = new Panels();
    Chat chat = new Chat();

    public GUI(){
        Initalize_Window();
        panel.addChat(chat.createChat());
        frame.add(panel);
        panel.setUpPanel();
        frame.setVisible(true);

    }

    void Initalize_Window()
    {
        this.frame.setSize(1200,800);
        Toolkit toolkit = Toolkit.getDefaultToolkit(); // Ta klasa zapewnia dostęp do funkcji zależnych od systemu operacyjnego
        Dimension screenSize = toolkit.getScreenSize(); // Dimension przechowuje width i height, getScreenSize() pobiera wymiary ekranu
        int x = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y); // ustawia lokalizacje okna na x i y (w tym przypadku na środku)
        frame.setVisible(true);
        frame.setTitle("ChatRoom");  //nazwa okna
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Bez tego okno sie nie zamyka ,,X"
    }

}
