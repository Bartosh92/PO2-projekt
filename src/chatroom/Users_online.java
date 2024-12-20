package chatroom;

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
        // Tworzymy niestandardowy panel z gradientem
        this.users_online_panel = new GradientPanel();
        this.users_online_panel.setPreferredSize(new Dimension(300, 760));
        this.users_online_panel.setLayout(new BorderLayout());

        // Nagłówek
        this.users_online_title = new JLabel("Users Online", SwingConstants.CENTER); // Wyrównanie do środka
        this.users_online_title.setForeground(Color.BLACK); // Kolor tekstu
        this.users_online_title.setFont(new Font("Arial", Font.BOLD, 24)); // Gruba czcionka
        this.users_online_title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Marginesy
        this.users_online_panel.add(users_online_title, BorderLayout.NORTH); // Dodanie nagłówka na górze

        // Oddzielenie nagłówka od listy użytkowników
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setForeground(Color.BLACK);
        separator.setBackground(Color.BLACK);
        this.users_online_panel.add(separator, BorderLayout.CENTER); // Pozioma linia oddzielająca

        // Ramka
        this.border = BorderFactory.createMatteBorder(0, 0, 0, 2, Color.BLACK);
        this.users_online_panel.setBorder(border);
    }

    public JPanel get_panel() {
        return users_online_panel;
    }

    // Niestandardowy panel z gradientem
    private static class GradientPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            int width = getWidth();
            int height = getHeight();
            GradientPaint gradient = new GradientPaint(0, 0, new Color(189, 19, 59), width, height, new Color(71, 113, 225));
            g2d.setPaint(gradient);
            g2d.fillRect(0, 0, width, height);
        }
    }
}
