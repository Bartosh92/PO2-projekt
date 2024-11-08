package org.example;


import javax.swing.*;

//Klasa służąca do stworzenia panelu i jego konfiguracji

public class Panels extends JPanel {
    private JPanel panel;

    public Panels() {
        setUpPanel();
    }
    protected void setUpPanel(){
        this.setBorder(BorderFactory.createEmptyBorder(30,30,10,30)); //Ustawia marginesy góra, lewo, dół, prawo
        this.setLayout(null); //ustawia rozmieszczenie elementów na ekranie, argumenty - wiersze, kolumny
    }


}
