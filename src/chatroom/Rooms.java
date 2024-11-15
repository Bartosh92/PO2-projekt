package chatroom;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Rooms {
    private JPanel room_main_panel;
    private Border border;

    public Rooms() {
        createRoomsPanel();
    }

    public void createRoomsPanel()
    {
        this.room_main_panel = new JPanel();
        this.room_main_panel.setLayout(new BorderLayout());
        this.room_main_panel.setPreferredSize(new Dimension(300, 760));
        this.room_main_panel.setBackground(new Color(166, 166, 170)); //Ustawia kolor tła

        this.border = BorderFactory.createMatteBorder(0, 2, 0, 0, Color.BLACK);
        this.room_main_panel.setBorder(border);
    }

    public JPanel getPanel(){
        return this.room_main_panel;
    }

}
