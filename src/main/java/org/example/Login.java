package org.example;

import javax.swing.*;
import java.awt.*;

public class Login {
    JFrame loginFrame = new JFrame();
    JPanel loginPanel = new JPanel();
    JPanel LoginLabelPanel = new JPanel();
    JLabel LoginLabel = new JLabel("Login");

    public Login() {
        Initalize_Window();
        CreateLoginPanel();
        setLoginPanel();
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

    public void CreateLoginPanel()
    {
        this.LoginLabelPanel.setLayout(null);
        this.LoginLabel.setBounds(50, 50, 100, 30);
        this.LoginLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
        this.LoginLabel.setForeground(Color.BLACK);
        this.LoginLabelPanel.add(this.LoginLabel);
    }

    public void setLoginPanel(){
        this.loginFrame.add(this.LoginLabelPanel);
    }

    public void C


}
