package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Modyfikacja klasy Users_online do dynamicznego dodawania użytkowników
public class Users_online {
    private JPanel users_online_panel;
    private JLabel users_online_title;
    private DefaultListModel<String> usersListModel;
    private JList<String> usersList;

    public Users_online() {
        create_Users_online();
    }

    public void create_Users_online() {
        this.users_online_panel = new JPanel();
        this.users_online_panel.setPreferredSize(new Dimension(300, 760));
        this.users_online_panel.setLayout(new BorderLayout());
        this.users_online_panel.setBackground(new Color(132, 175, 245));
        this.users_online_title = new JLabel("Users Online");

        usersListModel = new DefaultListModel<>();
        usersList = new JList<>(usersListModel);

        this.users_online_panel.add(users_online_title, BorderLayout.NORTH);
        this.users_online_panel.add(new JScrollPane(usersList), BorderLayout.CENTER);
    }

    public JPanel get_panel() {
        return users_online_panel;
    }

    public void addUser(String username) {
        if (!usersListModel.contains(username)) {
            usersListModel.addElement(username);
        }
    }

    public void removeUser(String username) {
        usersListModel.removeElement(username);
    }

    public void setUsers(ArrayList<String> users) {
        usersListModel.clear();
        for (String user : users) {
            usersListModel.addElement(user);
        }
    }

    public DefaultListModel<String> getUsersListModel() {
        return usersListModel;
    }
}
