package airlinemanagementsystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class Cancel extends JFrame implements ActionListener {

    JComboBox<String> pnrDropdown;
    JLabel tfname, cancellationno, lblfcode, lbldateoftravel;
    JButton fetchButton, flight;
    JLabel heading, image, lblaadhar, lblname, lblnationality, lbladdress, lblgender;

    public Cancel() {
        setTitle("Ticket Cancellation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create gradient panel
        JPanel gradientPanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                int width = getWidth();
                int height = getHeight();
                GradientPaint gradient = new GradientPaint(0, 0, new Color(255, 182, 193), width, height, new Color(173, 216, 230));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, width, height);
            }
        };
        setContentPane(gradientPanel);

        Random random = new Random();

        heading = new JLabel("CANCELLATION");
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        gradientPanel.add(heading);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/cancel.jpg"));
        Image i2 = i1.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        image = new JLabel(i3);
        gradientPanel.add(image);

        lblaadhar = new JLabel("PNR Number");
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gradientPanel.add(lblaadhar);


        pnrDropdown = new JComboBox<>();
        gradientPanel.add(pnrDropdown);

        fetchButton = new JButton("Show Details");
        fetchButton.setBackground(new Color(0, 51, 102));
        fetchButton.setForeground(Color.WHITE);
        fetchButton.setFont(new Font("Tahoma", Font.BOLD, 14));
        fetchButton.setFocusPainted(false);
        fetchButton.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        fetchButton.addActionListener(this);
        gradientPanel.add(fetchButton);

        lblname = new JLabel("Name");
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gradientPanel.add(lblname);

        tfname = new JLabel();
        gradientPanel.add(tfname);

        lblnationality = new JLabel("Cancellation No");
        lblnationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gradientPanel.add(lblnationality);

        cancellationno = new JLabel("" + random.nextInt(1000000));
        gradientPanel.add(cancellationno);

        lbladdress = new JLabel("Flight Code");
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gradientPanel.add(lbladdress);

        lblfcode = new JLabel();
        gradientPanel.add(lblfcode);

        lblgender = new JLabel("Date");
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gradientPanel.add(lblgender);

        lbldateoftravel = new JLabel();
        gradientPanel.add(lbldateoftravel);

        flight = new JButton("Cancel");
        flight.setBackground(new Color(153, 0, 0));
        flight.setForeground(Color.WHITE);
        flight.setFont(new Font("Tahoma", Font.BOLD, 14));
        flight.setFocusPainted(false);
        flight.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        flight.addActionListener(this);
        gradientPanel.add(flight);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                resizeComponents();
            }
        });


        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select PNR from reservation");
            while (rs.next()) {
                pnrDropdown.addItem(rs.getString("PNR"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setSize(800, 450);
        setLocation(350, 150);
        setVisible(true);
    }

    private void resizeComponents() {
        int width = getWidth();
        int height = getHeight();

        heading.setBounds(width / 2 - 125, 20, 250, 35);
        image.setBounds(width - 270, height / 4, 250, 250);

        int labelX = width / 10;
        int fieldX = width / 4;

        lblaadhar.setBounds(labelX, height / 10, 150, 25);
        pnrDropdown.setBounds(fieldX, height / 10, 150, 25);
        fetchButton.setBounds(fieldX + 180, height / 10, 140, 35);

        lblname.setBounds(labelX, height / 10 + 50, 150, 25);
        tfname.setBounds(fieldX, height / 10 + 50, 150, 25);

        lblnationality.setBounds(labelX, height / 10 + 100, 150, 25);
        cancellationno.setBounds(fieldX, height / 10 + 100, 150, 25);

        lbladdress.setBounds(labelX, height / 10 + 150, 150, 25);
        lblfcode.setBounds(fieldX, height / 10 + 150, 150, 25);

        lblgender.setBounds(labelX, height / 10 + 200, 150, 25);
        lbldateoftravel.setBounds(fieldX, height / 10 + 200, 150, 25);

        flight.setBounds(fieldX, height / 10 + 260, 120, 35);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetchButton) {
            String pnr = (String) pnrDropdown.getSelectedItem();

            try {
                Conn conn = new Conn();

                String query = "select * from reservation where PNR = '" + pnr + "'";

                ResultSet rs = conn.s.executeQuery(query);

                if (rs.next()) {
                    tfname.setText(rs.getString("name"));
                    lblfcode.setText(rs.getString("flightcode"));
                    lbldateoftravel.setText(rs.getString("ddate"));
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter correct PNR");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == flight) {
            String name = tfname.getText();
            String pnr = (String) pnrDropdown.getSelectedItem();
            String cancelno = cancellationno.getText();
            String fcode = lblfcode.getText();
            String date = lbldateoftravel.getText();

            try {
                Conn conn = new Conn();

                String query = "insert into cancel values('" + pnr + "', '" + name + "', '" + cancelno + "', '" + fcode + "', '" + date + "')";

                conn.s.executeUpdate(query);
                conn.s.executeUpdate("delete from reservation where PNR = '" + pnr + "'");

                JOptionPane.showMessageDialog(null, "Ticket Cancelled");
                setVisible(false);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Cancel();
    }
}