package org.example;

import javax.swing.*;
import java.awt.*;

public class Login {
    JFrame loginFrame = new JFrame();
    JPanel mainPanel = new JPanel();
    JPanel loginPanel = new JPanel();
    JPanel passwordPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel formPanel = new JPanel();

    JLabel mainLabel = new JLabel("Logowanie");
    JLabel loginLabel = new JLabel("Login:");
    JLabel passwordLabel = new JLabel("Hasło:");

    JTextField login = new JTextField();
    JPasswordField password = new JPasswordField();

    JButton loginButton = new JButton("Zaloguj");
    JButton registerButton = new JButton("Zarejestruj");

    public Login() {
        initializeWindow();
        createMainLabel();
        createForm();
        createButtons();
        loginFrame.add(mainPanel);
    }

    // Inicjalizacja i parametryzacja okna i paneli
    public void initializeWindow() {
        this.loginFrame.setSize(600, 400);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (int) ((screenSize.getWidth() - loginFrame.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - loginFrame.getHeight()) / 2);
        loginFrame.setLocation(x, y);
        loginFrame.setTitle("ChatRoom"); // Nazwa okna
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Bez tego okno się nie zamyka
        loginFrame.setResizable(false);
        loginFrame.setVisible(true);

        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(new Color(45, 116, 224));
    }

    // Funkcja tworząca główną etykietę
    public void createMainLabel() {
        mainLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
        mainLabel.setForeground(new Color(219, 214, 61));
        mainLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel labelPanel = new JPanel(new BorderLayout());
        labelPanel.setBackground(new Color(45, 116, 224));
        labelPanel.add(mainLabel, BorderLayout.CENTER);

        mainPanel.add(labelPanel, BorderLayout.NORTH);
    }

    // Funkcja tworząca formularz logowania
    public void createForm() {
        formPanel.setLayout(new GridLayout(4, 1, 10, 10));
        formPanel.setBackground(new Color(45, 116, 224));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        // Login
        loginLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        loginLabel.setForeground(Color.WHITE);
        login.setPreferredSize(new Dimension(550, 30));
        formPanel.add(loginLabel);
        formPanel.add(login);

        // Hasło
        passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        passwordLabel.setForeground(Color.WHITE);
        password.setPreferredSize(new Dimension(550, 30));
        password.setEchoChar('*');
        formPanel.add(passwordLabel);
        formPanel.add(password);

        mainPanel.add(formPanel, BorderLayout.CENTER);
    }

    // Funkcja tworząca przyciski
    public void createButtons() {
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(45, 116, 224));

        loginButton.setPreferredSize(new Dimension(120, 40));
        registerButton.setPreferredSize(new Dimension(120, 40));

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }
}