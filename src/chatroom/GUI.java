package chatroom;
import javax.swing.*;
import java.awt.*;

//Klasa obsługi okna i wywołania wszystkich pozostałych funkcji
public class GUI {

    JFrame frame = new JFrame(); //Tworzy okno
    JPanel mainPanel = new JPanel();
    Chat chat;
    Users_online users_online = new Users_online();
    Rooms rooms = new Rooms();


    public GUI(Client client){
        chat = new Chat(client);
        Initalize_Window();  // Tworzenie okna
        mainPanel.setLayout(new BorderLayout()); // Ustawienie layoutu dla mainPanel
        mainPanel.add(users_online.get_panel(), BorderLayout.WEST); // Panel użytkowników po lewej
        mainPanel.add(chat.getChatPanel(), BorderLayout.CENTER); // Panel czatu w centrum
        mainPanel.add(rooms.getPanel(), BorderLayout.EAST);

        frame.add(mainPanel, BorderLayout.CENTER); // Dodaj mainPanel do okna w centralnym obszarze
    }

    void Initalize_Window() {
        this.frame.setSize(1200, 800);
        Toolkit toolkit = Toolkit.getDefaultToolkit(); // Ta klasa zapewnia dostęp do funkcji zależnych od systemu operacyjnego
        Dimension screenSize = toolkit.getScreenSize(); // Dimension przechowuje width i height, getScreenSize() pobiera wymiary ekranu
        int x = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y); // Ustawia lokalizację okna na środku
        frame.setTitle("ChatRoom");  // Nazwa okna
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Bez tego okno się nie zamyka
        frame.setResizable(false);
        frame.setVisible(true);
    }
    //Metoda zwracająca chat
    public Chat getChat(){
        return chat;
    }

    //Metoda zamykajaca okno
    public void closeGui()
    {
        frame.setVisible(false);
        frame.dispose();
    }

}