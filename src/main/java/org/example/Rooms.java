package org.example;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Rooms {
    private JPanel room_main_panel;
    private Border border;

    public Rooms() {
        createRoomsPanel();
    }

    public void createRoomsPanel()
    {
        this.room_main_panel = new JPanel();
        this.room_main_panel.setLayout(new BorderLayout());
        this.room_main_panel.setPreferredSize(new Dimension(300, 760));
        this.room_main_panel.setBackground(new Color(132, 175, 245)); //Ustawia kolor tła

        this.border = BorderFactory.createMatteBorder(0, 2, 0, 0, new Color(69, 140, 255));
        this.room_main_panel.setBorder(border);
    }

    public JPanel getPanel(){
        return this.room_main_panel;
    }

}
