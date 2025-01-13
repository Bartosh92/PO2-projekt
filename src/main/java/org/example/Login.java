package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Login {
    JFrame loginFrame = new JFrame();
    JPanel mainPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel formPanel = new JPanel();

    JLabel mainLabel = new JLabel("Logowanie");
    JLabel loginLabel = new JLabel("Login:");
    JLabel passwordLabel = new JLabel("Hasło:");

    JTextField login = new JTextField();
    JPasswordField password = new JPasswordField();

    JButton loginButton = new JButton("Zaloguj");
    JButton registerButton = new JButton("Zarejestruj");

    private static final String DATA_FILE = "user_data.json";

    public Login() {
        initializeWindow();
        createMainLabel();
        createForm();
        createButtons();
        loginFrame.add(mainPanel);

        addListeners();
    }

    public void initializeWindow() {
        this.loginFrame.setSize(600, 400);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (int) ((screenSize.getWidth() - loginFrame.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - loginFrame.getHeight()) / 2);
        loginFrame.setLocation(x, y);
        loginFrame.setTitle("ChatRoom");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setResizable(false);
        loginFrame.setVisible(true);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(45, 116, 224));
    }

    public void createMainLabel() {
        mainLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        mainLabel.setForeground(new Color(219, 214, 61));
        mainLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.setBackground(new Color(45, 116, 224));
        labelPanel.add(mainLabel, BorderLayout.CENTER);

        mainPanel.add(labelPanel, BorderLayout.NORTH);
    }

    public void createForm() {
        formPanel.setLayout(new GridLayout(4, 1, 10, 10));
        formPanel.setBackground(new Color(45, 116, 224));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        loginLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        loginLabel.setForeground(Color.WHITE);
        login.setPreferredSize(new Dimension(550, 30));
        formPanel.add(loginLabel);
        formPanel.add(login);

        passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        passwordLabel.setForeground(Color.WHITE);
        password.setPreferredSize(new Dimension(550, 30));
        password.setEchoChar('*');
        formPanel.add(passwordLabel);
        formPanel.add(password);

        mainPanel.add(formPanel, BorderLayout.CENTER);
    }

    public void createButtons() {
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(45, 116, 224));

        loginButton.setPreferredSize(new Dimension(120, 40));
        registerButton.setPreferredSize(new Dimension(120, 40));

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void addListeners() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRegister();
            }
        });
    }

    private void handleLogin() {
        String username = login.getText().trim();
        String pass = new String(password.getPassword()).trim();

        if (username.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(loginFrame, "Wypełnij wszystkie pola!", "Błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (validateCredentials(username, pass)) {
            JOptionPane.showMessageDialog(loginFrame, "Zalogowano pomyślnie!", "Sukces", JOptionPane.INFORMATION_MESSAGE);
            loginFrame.dispose(); // Zamyka okno logowania
        } else {
            JOptionPane.showMessageDialog(loginFrame, "Nieprawidłowy login lub hasło!", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleRegister() {
        String username = login.getText().trim();
        String pass = new String(password.getPassword()).trim();

        if (username.isEmpty() || pass.isEmpty()) {
            JOptionPane.showMessageDialog(loginFrame, "Wypełnij wszystkie pola!", "Błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }

        saveToJSON(username, pass);
        JOptionPane.showMessageDialog(loginFrame, "Zarejestrowano pomyślnie!", "Sukces", JOptionPane.INFORMATION_MESSAGE);
    }

    private void saveToJSON(String username, String password) {
        Gson gson = new Gson();
        List<User> users = loadUsers();

        users.add(new User(username, password));

        try (FileWriter writer = new FileWriter(DATA_FILE)) {
            gson.toJson(users, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean validateCredentials(String username, String password) {
        List<User> users = loadUsers();
        for (User user : users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                return true;
            }
        }
        return false;
    }

    private List<User> loadUsers() {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(DATA_FILE)) {
            Type userListType = new TypeToken<ArrayList<User>>() {}.getType();
            return gson.fromJson(reader, userListType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    class User {
        String username;
        String password;

        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }
}
