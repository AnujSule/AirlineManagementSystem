package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JButton submit, reset, close, signup;
    JTextField tfusername;
    JPasswordField tfpassword;

    public Login() {

        getContentPane().setBackground(new Color(255, 182, 193)); // Light pink
        setLayout(null);


        JLabel heading = new JLabel("HAYY AIRLINES WELCOMES YOU");
        heading.setFont(new Font("Arial", Font.BOLD, 32)); // Bold and large text
        heading.setForeground(new Color(0, 0, 0)); // Black text
        heading.setBounds(50, 30, 600, 50); // Adjusted bounds
        add(heading);


        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(100, 120, 100, 30);
        lblusername.setFont(new Font("Arial", Font.PLAIN, 14));
        add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(220, 120, 250, 35);
        tfusername.setFont(new Font("Arial", Font.PLAIN, 14));
        tfusername.setBackground(new Color(245, 245, 245)); // Light grey
        add(tfusername);


        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(100, 180, 100, 30);
        lblpassword.setFont(new Font("Arial", Font.PLAIN, 14));
        add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(220, 180, 250, 35);
        tfpassword.setFont(new Font("Arial", Font.PLAIN, 14));
        tfpassword.setBackground(new Color(245, 245, 245)); // Light grey
        add(tfpassword);


        submit = new JButton("Login");
        submit.setBounds(220, 250, 120, 40);
        submit.setFont(new Font("Arial", Font.BOLD, 16));
        submit.setBackground(new Color(70, 130, 180)); // Blue
        submit.setForeground(Color.BLACK); // Black text
        submit.addActionListener(this);
        add(submit);

        signup = new JButton("Sign Up");
        signup.setBounds(220, 300, 120, 40);
        signup.setFont(new Font("Arial", Font.BOLD, 16));
        signup.setBackground(new Color(70, 130, 180)); // Blue
        signup.setForeground(Color.BLACK); // Black text
        signup.addActionListener(this);
        add(signup);

        reset = new JButton("Reset");
        reset.setBounds(100, 250, 100, 40);
        reset.setFont(new Font("Arial", Font.BOLD, 16));
        reset.setBackground(new Color(255, 105, 180)); // Pink
        reset.setForeground(Color.BLACK); // Black text
        reset.addActionListener(this);
        add(reset);

        close = new JButton("Close");
        close.setBounds(100, 300, 100, 40);
        close.setFont(new Font("Arial", Font.BOLD, 16));
        close.setBackground(new Color(255, 105, 180));
        close.setForeground(Color.BLACK);
        close.addActionListener(this);
        add(close);

        // Frame settings
        setSize(600, 400);
        setLocationRelativeTo(null);
        setTitle("Login - HAYY Airlines");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submit) {
            String username = tfusername.getText();
            String password = new String(tfpassword.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Username and Password cannot be empty!");
                return;
            }

            try {
                Conn c = new Conn();
                Connection con = c.getConnection();

                String query = "SELECT * FROM login WHERE username = ? AND password = ?";
                PreparedStatement stmt = con.prepareStatement(query);
                stmt.setString(1, username);
                stmt.setString(2, password);

                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    new Home();
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Username or Password. Please try again.");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == close) {
            dispose();
        } else if (ae.getSource() == reset) {
            tfusername.setText("");
            tfpassword.setText("");
        } else if (ae.getSource() == signup) {
            String username = tfusername.getText().trim();
            String password = new String(tfpassword.getPassword()).trim();

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Username and Password cannot be empty!");
                return;
            }

            try {
                Conn c = new Conn();
                Connection con = c.getConnection();

                String checkQuery = "SELECT * FROM login WHERE username = ?";
                PreparedStatement checkStmt = con.prepareStatement(checkQuery);
                checkStmt.setString(1, username);

                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Username already exists. Try a different username.");
                } else {
                    String insertQuery = "INSERT INTO login (username, password) VALUES (?, ?)";
                    PreparedStatement insertStmt = con.prepareStatement(insertQuery);
                    insertStmt.setString(1, username);
                    insertStmt.setString(2, password);

                    int rowsInserted = insertStmt.executeUpdate();

                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(null, "Sign Up successful! You can now log in.");
                        new Home();
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Sign Up failed. Try again later.");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}