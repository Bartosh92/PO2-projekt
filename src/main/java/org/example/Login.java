package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Login {
    JFrame loginFrame = new JFrame();
    JPanel loginPanel = new JPanel();
    JPanel LoginLabelPanel = new JPanel();
    JLabel LoginLabel = new JLabel("Log in");
    JTextField login = new JTextField();

    public Login() {
        Initalize_Window();
        CreateLoginChatArea();
        CreateLoginPanel();

    }

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
        loginFrame.add(loginPanel);
        this.loginPanel.setBackground(Color.WHITE);
    }

    //Tworzy glowny panel i etykiete
    public void CreateLoginPanel()
    {
        this.LoginLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        this.LoginLabel.setFont(this.LoginLabel.getFont().deriveFont(Font.BOLD, 20));
        this.LoginLabel.setForeground(Color.BLACK);
        this.LoginLabelPanel.add(this.LoginLabel);
        this.loginFrame.add(this.LoginLabelPanel, BorderLayout.NORTH);
    }



    public void CreateLoginChatArea(){

        this.login.setPreferredSize(new Dimension(550, 30));
        this.login.setFont(new Font("Tahoma", Font.PLAIN, 12));
        this.login.setForeground(Color.BLACK);
        this.login.setBackground(Color.WHITE);
        this.loginPanel.add(this.login, BorderLayout.CENTER);


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

        login.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e)
            {
                login.setText("Login");
            }

            public void focusLost(FocusEvent e){}
        });



    }

    public static void main(String[] args) {
        Login login = new Login();


    }



}
