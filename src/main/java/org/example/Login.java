package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Login {
    JFrame loginFrame = new JFrame();
    JPanel mainPanel = new JPanel();
    JPanel loginPanel = new JPanel();
    JPanel LoginLabelPanel = new JPanel();
    JPanel PasswordPanel = new JPanel();
    JLabel LoginLabel = new JLabel("Log in");
    JTextField login = new JTextField();
    JPasswordField password = new JPasswordField();

    public Login() {
        Initalize_Window();
        CreateLabel();
        CreateLoginChatArea();
        CreatePasswordChatArea();
        loginFrame.add(mainPanel);


    }

    //Inicjalizacja i prametryzowacja okna i paneli
    public void Initalize_Window()
    {
        this.loginFrame.setSize(600,400);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (int) ((screenSize.getWidth() - loginFrame.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - loginFrame.getHeight()) / 2);
        loginFrame.setLocation(x, y);
        loginFrame.setTitle("ChatRoom");  // Nazwa okna
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Bez tego okno się nie zamyka
        loginFrame.setResizable(false);
        loginFrame.setVisible(true);
        mainPanel.setBackground(new Color(45, 116, 224));

    }


    //Funkcja tworzaca etykiete
    public void CreateLabel()
    {
        this.LoginLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        this.LoginLabel.setFont(this.LoginLabel.getFont().deriveFont(Font.BOLD, 20));
        this.LoginLabel.setForeground(new Color(219, 214, 61));
        this.LoginLabelPanel.add(this.LoginLabel);
        LoginLabelPanel.setBackground(new Color(45, 116, 224));
        this.mainPanel.add(this.LoginLabelPanel, BorderLayout.NORTH);

    }


    //Funkcja Tworzaca pole na login, dodaje obsluge klawisza enter
    public void CreateLoginChatArea(){

        this.login.setPreferredSize(new Dimension(550, 30));
        this.login.setFont(new Font("Tahoma", Font.PLAIN, 12));
        this.login.setForeground(Color.BLACK);
        this.login.setBackground(Color.WHITE);
        this.loginPanel.add(this.login);
        this.loginPanel.setBackground(new Color(45, 116, 224));
        this.mainPanel.add(this.loginPanel);



        //Obsluga klawisza enter
        this.login.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        login.setText(" ");
                }
            }

            public void keyReleased(KeyEvent e) {}

        });




    }

    //Funkcja Tworzaca pole na haslo, dodaje obsluge klawisza enter
    public void CreatePasswordChatArea()
    {
        this.password.setPreferredSize(new Dimension(550, 30));
        this.password.setFont(new Font("Tahoma", Font.PLAIN, 12));
        this.password.setForeground(Color.BLACK);
        this.password.setBackground(Color.WHITE);
        this.password.setEchoChar('*');
        this.PasswordPanel.add(this.password);
        this.PasswordPanel.setBackground(new Color(45, 116, 224));

        this.mainPanel.add(this.PasswordPanel);

        this.password.addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) {}
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_ENTER:
                        password.setText(" ");
                }
            }

            public void keyReleased(KeyEvent e) {}

        });

    }




}
