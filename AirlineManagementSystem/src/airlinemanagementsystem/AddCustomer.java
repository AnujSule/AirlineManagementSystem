


package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AddCustomer extends JFrame implements ActionListener {

    JTextField tfname, tfphone, tfaadhar, tfnationality, tfaddress;
    JRadioButton rbmale, rbfemale;

    public AddCustomer() {
        setTitle("Add Customer Details");
        setLayout(null);

        // Set up the main background panel with a gradient
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(255, 204, 204); // Light pink
                Color color2 = new Color(102, 178, 255); // Light blue
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        backgroundPanel.setLayout(null);
        setContentPane(backgroundPanel);

        // Heading
        JLabel heading = new JLabel("ADD CUSTOMER DETAILS");
        heading.setBounds(220, 20, 500, 35);
        heading.setFont(new Font("Tahoma", Font.BOLD, 32));
        heading.setForeground(Color.DARK_GRAY);
        backgroundPanel.add(heading);

        // Name Label and TextField
        JLabel lblname = new JLabel("Name:");
        lblname.setBounds(60, 80, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        backgroundPanel.add(lblname);

        tfname = new JTextField();
        tfname.setBounds(220, 80, 200, 25);
        backgroundPanel.add(tfname);

        // Add a KeyListener to ensure only alphabetic characters are entered
        tfname.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && !Character.isWhitespace(c) && c != KeyEvent.VK_BACK_SPACE) {
                    e.consume(); // Prevent invalid input
                    Toolkit.getDefaultToolkit().beep(); // Audible feedback for invalid input
                }
            }
        });

        // Nationality Label and TextField
        JLabel lblnationality = new JLabel("Nationality:");
        lblnationality.setBounds(60, 130, 150, 25);
        lblnationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        backgroundPanel.add(lblnationality);

        tfnationality = new JTextField();
        tfnationality.setBounds(220, 130, 200, 25);
        backgroundPanel.add(tfnationality);

        // Aadhar Label and TextField
        JLabel lblaadhar = new JLabel("Aadhar Number:");
        lblaadhar.setBounds(60, 180, 150, 25);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        backgroundPanel.add(lblaadhar);

        tfaadhar = new JTextField();
        tfaadhar.setBounds(220, 180, 200, 25);
        backgroundPanel.add(tfaadhar);

        // Address Label and TextField
        JLabel lbladdress = new JLabel("Address:");
        lbladdress.setBounds(60, 230, 150, 25);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        backgroundPanel.add(lbladdress);

        tfaddress = new JTextField();
        tfaddress.setBounds(220, 230, 200, 25);
        backgroundPanel.add(tfaddress);

        // Gender Label and RadioButtons
        JLabel lblgender = new JLabel("Gender:");
        lblgender.setBounds(60, 280, 150, 25);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        backgroundPanel.add(lblgender);

        ButtonGroup gendergroup = new ButtonGroup();

        rbmale = new JRadioButton("Male");
        rbmale.setBounds(220, 280, 70, 25);
        rbmale.setBackground(new Color(255, 204, 204));
        backgroundPanel.add(rbmale);

        rbfemale = new JRadioButton("Female");
        rbfemale.setBounds(300, 280, 100, 25);
        rbfemale.setBackground(new Color(255, 204, 204));
        backgroundPanel.add(rbfemale);

        gendergroup.add(rbmale);
        gendergroup.add(rbfemale);

        // Phone Label and TextField
        JLabel lblphone = new JLabel("Phone:");
        lblphone.setBounds(60, 330, 150, 25);
        lblphone.setFont(new Font("Tahoma", Font.PLAIN, 16));
        backgroundPanel.add(lblphone);

        tfphone = new JTextField();
        tfphone.setBounds(220, 330, 200, 25);
        backgroundPanel.add(tfphone);

        // Add a KeyListener to ensure only numeric input and 10 digits
        tfphone.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || tfphone.getText().length() >= 10) {
                    e.consume(); // Prevent invalid input or extra digits
                    Toolkit.getDefaultToolkit().beep(); // Audible feedback for invalid input
                }
            }
        });

        // Save Button
        JButton save = new JButton("SAVE");
        save.setBounds(220, 380, 200, 40);
        save.setFont(new Font("Tahoma", Font.BOLD, 16));
        save.setBackground(Color.ORANGE);
        save.setForeground(Color.BLACK);
        save.setFocusPainted(false);
        save.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        save.addActionListener(this);
        backgroundPanel.add(save);

        // Insert logo image
        ImageIcon logoImage = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/HAYYlogo.png"));

        // Resize the image to fit properly while maintaining aspect ratio
        Image img = logoImage.getImage();
        Image newImg = img.getScaledInstance(280, 280, Image.SCALE_SMOOTH); // Resizing the logo
        logoImage = new ImageIcon(newImg);  // Set the resized image

        // Create a label for the logo image
        JLabel logoLabel = new JLabel(logoImage);
        logoLabel.setBounds(450, 80, 280, 280); // Adjust size and position as needed
        backgroundPanel.add(logoLabel);

        // Frame Settings
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        // Validate fields
        String name = tfname.getText().trim();
        String nationality = tfnationality.getText().trim();
        String phone = tfphone.getText().trim();
        String address = tfaddress.getText().trim();
        String aadhar = tfaadhar.getText().trim();
        String gender = null;

        if (rbmale.isSelected()) {
            gender = "Male";
        } else if (rbfemale.isSelected()) {
            gender = "Female";
        }

        if (name.isEmpty() || nationality.isEmpty() || phone.isEmpty() || address.isEmpty() || aadhar.isEmpty() || gender == null) {
            JOptionPane.showMessageDialog(this, "All fields are mandatory!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate phone number length (must be 10 digits)
        if (phone.length() != 10) {
            JOptionPane.showMessageDialog(this, "Phone number must be 10 digits!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Validate Aadhar number (must be 12 digits)
        if (aadhar.length() != 12) {
            JOptionPane.showMessageDialog(this, "Aadhar number must be 12 digits!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Conn conn = new Conn();

            // Check for duplicate Aadhar number
            String checkQuery = "SELECT * FROM passenger WHERE aadhar = '" + aadhar + "'";
            var rs = conn.s.executeQuery(checkQuery);
            if (rs.next()) {
                JOptionPane.showMessageDialog(this, "This Aadhar number is already registered!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Insert new customer details
            String query = "INSERT INTO passenger (name, nationality, phone, address, aadhar, gender) VALUES ('" +
                    name + "', '" + nationality + "', '" + phone + "', '" + address + "', '" + aadhar + "', '" + gender + "')";
            conn.s.executeUpdate(query);

            JOptionPane.showMessageDialog(this, "Customer Details Added Successfully");
            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error Adding Customer Details", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new AddCustomer();
    }
}