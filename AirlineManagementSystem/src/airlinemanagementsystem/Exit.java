package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;

public class Exit extends JFrame {
    public Exit(){

        setLayout(new BorderLayout());
        ImagePanel imagePanel = new ImagePanel("airlinemanagementsystem/icons/ExitLogo.jpg");
        add(imagePanel, BorderLayout.CENTER);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        new Exit();
    }
}
