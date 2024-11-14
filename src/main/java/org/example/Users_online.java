package org.example;


import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Users_online {
    private JPanel users_online_panel;
    private JLabel users_online_title;
    private Border border;

    public Users_online() {
        create_Users_online();
    }

    public void create_Users_online() {
        this.users_online_panel = new JPanel(); //Tworzy panel
        this.users_online_panel.setPreferredSize(new Dimension(300, 760));
        this.users_online_panel.setLayout(new BorderLayout()); //Ustawia layout
        this.users_online_panel.setBackground(new Color(132, 175, 245)); //Ustawia kolor tła
        this.users_online_title = new JLabel("Users Online"); //Tworzy etykietę
        this.users_online_panel.add(users_online_title, BorderLayout.NORTH); //Dodaje etykietę do panelu i ustawia jej położenie

        this.border = BorderFactory.createMatteBorder(0, 0, 0, 2, new Color(69, 140, 255));
        this.users_online_panel.setBorder(border);
    }

    public JPanel get_panel(){
        return users_online_panel;
    }
}


