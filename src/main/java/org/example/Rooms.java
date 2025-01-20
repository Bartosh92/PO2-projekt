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

    public void createRoomsPanel() {
        this.room_main_panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                // Ustawienie gradientu
                GradientPaint gradient = new GradientPaint(0, 0, new Color(132, 175, 245), getWidth(), getHeight(), new Color(34, 72, 209));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        this.room_main_panel.setLayout(new BorderLayout());
        this.room_main_panel.setPreferredSize(new Dimension(300, 760));

        // Ustawienie koloru tła, jeśli nie chcemy go zastępować gradientem
        // this.room_main_panel.setBackground(new Color(166, 166, 170));

        this.border = BorderFactory.createMatteBorder(0, 2, 0, 0, Color.BLACK);
        this.room_main_panel.setBorder(border);
    }

    public JPanel getPanel() {
        return this.room_main_panel;
    }
}
