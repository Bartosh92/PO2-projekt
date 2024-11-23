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
        this.users_online_panel = new JPanel();
        this.users_online_panel.setPreferredSize(new Dimension(300, 760));
        this.users_online_panel.setLayout(new BorderLayout());
        this.users_online_panel.setBackground(new Color(113, 71, 225)); // Fioletowe tło

        // Nagłówek
        this.users_online_title = new JLabel("Users Online", SwingConstants.CENTER); // Wyrównanie do środka
        this.users_online_title.setForeground(Color.WHITE); // Kolor tekstu
        this.users_online_title.setFont(new Font("Arial", Font.BOLD, 20)); // Gruba czcionka
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
}
