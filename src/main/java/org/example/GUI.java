package org.example;

import javax.swing.*;
import java.awt.*;

public class GUI {

    JFrame frame = new JFrame();
    JPanel mainPanel = new JPanel();
    Chat chat;
    Users_online users_online;
    Rooms rooms = new Rooms();
    private Client client;

    public GUI(Client client) {
        this.client = client;
        chat = new Chat(client);
        users_online = new Users_online(client);  // Przekazanie obiektu client
        Initalize_Window();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(users_online.get_panel(), BorderLayout.WEST);
        mainPanel.add(chat.getChatPanel(), BorderLayout.CENTER);
        mainPanel.add(rooms.getPanel(), BorderLayout.EAST);

        frame.add(mainPanel, BorderLayout.CENTER);
    }

    void Initalize_Window() {
        this.frame.setSize(1200, 800);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
        frame.setTitle("ChatRoom");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
    }

    public Chat getChat() {
        return chat;
    }

    public void closeGui() {
        frame.setVisible(false);
        frame.dispose();
    }
}
