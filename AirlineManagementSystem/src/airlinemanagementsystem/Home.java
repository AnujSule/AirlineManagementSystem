package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener {

    public Home() {
        setLayout(new BorderLayout());


        ImagePanel imagePanel = new ImagePanel("airlinemanagementsystem/icons/HAYY.jpeg");
        add(imagePanel, BorderLayout.CENTER);

        JLabel heading = new JLabel("HAYY AIRLINES WELCOMES YOU", SwingConstants.CENTER);
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Tahoma", Font.BOLD, 36));
        heading.setPreferredSize(new Dimension(0, 50));
        imagePanel.setLayout(new BorderLayout());
        imagePanel.add(heading, BorderLayout.SOUTH);

        JMenuBar menubar = new JMenuBar();
        setJMenuBar(menubar);

        JMenu details = new JMenu("Options");
        menubar.add(details);

        JMenuItem flightDetails = new JMenuItem("Flight Details");
        flightDetails.addActionListener(this);
        details.add(flightDetails);

        JMenuItem customerDetails = new JMenuItem("Add Customer Details");
        customerDetails.addActionListener(this);
        details.add(customerDetails);

        JMenuItem bookFlight = new JMenuItem("Book Flight");
        bookFlight.addActionListener(this);
        details.add(bookFlight);

        JMenuItem journeyDetails = new JMenuItem("Journey Details");
        journeyDetails.addActionListener(this);
        details.add(journeyDetails);

        JMenuItem ticketCancellation = new JMenuItem("Cancel Ticket");
        ticketCancellation.addActionListener(this);
        details.add(ticketCancellation);

        JMenuItem Exit = new JMenuItem("exit");
        Exit.addActionListener(this);
        details.add(Exit);

        JMenu ticket = new JMenu("Ticket");
        menubar.add(ticket);

        JMenuItem boardingPass = new JMenuItem("Boarding Pass");
        boardingPass.addActionListener(this);  // Add ActionListener to Boarding Pass
        ticket.add(boardingPass);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        String text = ae.getActionCommand();

        if (text.equals("Add Customer Details")) {
            new AddCustomer();
        } else if (text.equals("Flight Details")) {
            new FlightInfo();
        } else if (text.equals("Book Flight")) {
            new BookFlight();
        } else if (text.equals("Journey Details")) {
            new JourneyDetails();
        } else if (text.equals("Cancel Ticket")) {
            new Cancel();
        } else if (text.equals("Boarding Pass")) {
            new BoardingPass();  //
        }
        else if (text.equals("exit")) {
            int a = JOptionPane.showConfirmDialog(null, "Are you sure you want to Quit?", "Exit Window", JOptionPane.YES_NO_OPTION);
            if (a == JOptionPane.YES_OPTION) {
                dispose();
                new Exit();
            }
//            } else if (a == JOptionPane.NO_OPTION) {
//                JOptionPane.showMessageDialog(null, "Returning to the previous screen.");
//                new Home();
//            }


        }
    }

    public static void main(String[] args) {
        new Home();
    }
}

// Custom JPanel to handle dynamic image resizing
class ImagePanel extends JPanel {
    private final ImageIcon imageIcon;

    public ImagePanel(String imagePath) {
        imageIcon = new ImageIcon(ClassLoader.getSystemResource(imagePath));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image image = imageIcon.getImage();
        // Scale the image to fit the panel dimensions
        g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
    }
}