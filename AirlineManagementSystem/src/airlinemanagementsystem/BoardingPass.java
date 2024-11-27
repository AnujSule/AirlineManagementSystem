package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BoardingPass extends JFrame implements ActionListener {

    JComboBox<String> pnrDropdown;  // Changed to JComboBox for PNR selection
    JLabel tfname, tfnationality, lblsrc, lbldest, labelfname, labelfcode, labeldate;
    JButton fetchButton;

    public BoardingPass() {
        setTitle("Boarding Pass - HAYY Airlines");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a gradient panel for the background
        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                GradientPaint gradient = new GradientPaint(0, 0, new Color(173, 216, 230), width, height, new Color(255, 182, 193));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, width, height);
            }
        };
        gradientPanel.setLayout(null);
        setContentPane(gradientPanel);

        JLabel heading = new JLabel("HAYY AIRLINES");
        heading.setBounds(380, 10, 450, 35);
        heading.setFont(new Font("Canela", Font.BOLD, 32));
        gradientPanel.add(heading);

        JLabel subheading = new JLabel("Boarding Pass");
        subheading.setBounds(420, 50, 300, 30);
        subheading.setFont(new Font("Calibri", Font.PLAIN, 24));
        subheading.setForeground(Color.BLACK);
        gradientPanel.add(subheading);

        JLabel lblaadhar = new JLabel("PNR DETAILS");
        lblaadhar.setBounds(60, 100, 150, 25);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gradientPanel.add(lblaadhar);

        // Initialize the JComboBox for PNR selection
        pnrDropdown = new JComboBox<>();
        pnrDropdown.setBounds(220, 100, 150, 25);
        populatePNRDropdown();
        gradientPanel.add(pnrDropdown);

        fetchButton = new JButton("Enter");
        fetchButton.setBackground(new Color(0, 51, 102)); // Dark Blue
        fetchButton.setForeground(Color.WHITE); // White text
        fetchButton.setFont(new Font("Tahoma", Font.BOLD, 14)); // Bold and larger font
        fetchButton.setBounds(380, 100, 120, 35); // Increased height for visibility
        fetchButton.setFocusPainted(false); // Remove focus border
        fetchButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2)); // White border
        fetchButton.addActionListener(this);
        gradientPanel.add(fetchButton);

        JLabel lblname = new JLabel("NAME");
        lblname.setBounds(60, 140, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gradientPanel.add(lblname);

        tfname = new JLabel();
        tfname.setBounds(220, 140, 150, 25);
        gradientPanel.add(tfname);

        JLabel lblnationality = new JLabel("NATIONALITY");
        lblnationality.setBounds(60, 180, 150, 25);
        lblnationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gradientPanel.add(lblnationality);

        tfnationality = new JLabel();
        tfnationality.setBounds(220, 180, 150, 25);
        gradientPanel.add(tfnationality);

        JLabel lbladdress = new JLabel("SRC");
        lbladdress.setBounds(60, 220, 150, 25);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gradientPanel.add(lbladdress);

        lblsrc = new JLabel();
        lblsrc.setBounds(220, 220, 150, 25);
        gradientPanel.add(lblsrc);

        JLabel lblgender = new JLabel("DEST");
        lblgender.setBounds(380, 220, 150, 25);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gradientPanel.add(lblgender);

        lbldest = new JLabel();
        lbldest.setBounds(540, 220, 150, 25);
        gradientPanel.add(lbldest);

        JLabel lblfname = new JLabel("Flight Name");
        lblfname.setBounds(60, 260, 150, 25);
        lblfname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gradientPanel.add(lblfname);

        labelfname = new JLabel();
        labelfname.setBounds(220, 260, 150, 25);
        gradientPanel.add(labelfname);

        JLabel lblfcode = new JLabel("Flight Code");
        lblfcode.setBounds(380, 260, 150, 25);
        lblfcode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gradientPanel.add(lblfcode);

        labelfcode = new JLabel();
        labelfcode.setBounds(540, 260, 150, 25);
        gradientPanel.add(labelfcode);

        JLabel lbldate = new JLabel("Date");
        lbldate.setBounds(60, 300, 150, 25);
        lbldate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gradientPanel.add(lbldate);

        labeldate = new JLabel();
        labeldate.setBounds(220, 300, 150, 25);
        gradientPanel.add(labeldate);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/HAYYlogo.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 230, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(i2);
        JLabel lblimage = new JLabel(image);
        lblimage.setBounds(600, 100, 300, 300);
        gradientPanel.add(lblimage);

        setSize(1000, 450);
        setLocation(300, 150);
        setVisible(true);
    }

    // Method to populate PNR dropdown with PNR numbers from the database
    private void populatePNRDropdown() {
        try {
            Conn conn = new Conn();
            String query = "select PNR from reservation"; // Query to get all PNR numbers
            ResultSet rs = conn.s.executeQuery(query);

            while (rs.next()) {
                pnrDropdown.addItem(rs.getString("PNR")); // Add each PNR number to the dropdown
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        String pnr = (String) pnrDropdown.getSelectedItem(); // Get selected PNR from the dropdown

        try {
            Conn conn = new Conn();
            String query = "select * from reservation where PNR = '" + pnr + "'";
            ResultSet rs = conn.s.executeQuery(query);

            if (rs.next()) {
                tfname.setText(rs.getString("name"));
                tfnationality.setText(rs.getString("nationality"));
                lblsrc.setText(rs.getString("src"));
                lbldest.setText(rs.getString("des"));
                labelfname.setText(rs.getString("flightname"));
                labelfcode.setText(rs.getString("flightcode"));
                labeldate.setText(rs.getString("ddate"));
            } else {
                JOptionPane.showMessageDialog(null, "Please select a valid PNR");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new BoardingPass();
    }
}