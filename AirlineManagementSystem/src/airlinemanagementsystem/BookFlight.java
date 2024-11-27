
package airlinemanagementsystem;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Random;

public class BookFlight extends JFrame implements ActionListener {

    JTextField tfaadhar;
    JLabel tfname, tfnationality, tfaddress, labelgender, labelfname, labelfcode;
    JButton bookflight, fetchButton, flight;
    Choice source, destination;
    JDateChooser dcdate;

    public BookFlight() {

        JPanel gradientPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                Color color1 = new Color(102, 178, 255); // Light Blue
                Color color2 = new Color(255, 204, 255); // Light Pink
                GradientPaint gradient = new GradientPaint(0, 0, color1, 0, getHeight(), color2);
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        gradientPanel.setLayout(null);

        setContentPane(gradientPanel);

        JLabel heading = new JLabel("Book Flight");
        heading.setBounds(420, 20, 500, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.BLUE);
        gradientPanel.add(heading);

        JLabel lblaadhar = new JLabel("Aadhar");
        lblaadhar.setBounds(60, 80, 150, 25);
        lblaadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gradientPanel.add(lblaadhar);

        tfaadhar = new JTextField();
        tfaadhar.setBounds(220, 80, 150, 25);
        gradientPanel.add(tfaadhar);

        fetchButton = createStyledButton("Fetch User", 380, 80);
        gradientPanel.add(fetchButton);

        JLabel lblname = new JLabel("Name");
        lblname.setBounds(60, 130, 150, 25);
        lblname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gradientPanel.add(lblname);

        tfname = new JLabel();
        tfname.setBounds(220, 130, 150, 25);
        gradientPanel.add(tfname);

        JLabel lblnationality = new JLabel("Nationality");
        lblnationality.setBounds(60, 180, 150, 25);
        lblnationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gradientPanel.add(lblnationality);

        tfnationality = new JLabel();
        tfnationality.setBounds(220, 180, 150, 25);
        gradientPanel.add(tfnationality);

        JLabel lbladdress = new JLabel("Address");
        lbladdress.setBounds(60, 230, 150, 25);
        lbladdress.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gradientPanel.add(lbladdress);

        tfaddress = new JLabel();
        tfaddress.setBounds(220, 230, 150, 25);
        gradientPanel.add(tfaddress);

        JLabel lblgender = new JLabel("Gender");
        lblgender.setBounds(60, 280, 150, 25);
        lblgender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gradientPanel.add(lblgender);

        labelgender = new JLabel("Gender");
        labelgender.setBounds(220, 280, 150, 25);
        gradientPanel.add(labelgender);

        JLabel lblsource = new JLabel("Source");
        lblsource.setBounds(60, 330, 150, 25);
        lblsource.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gradientPanel.add(lblsource);

        source = new Choice();
        source.setBounds(220, 330, 150, 25);
        gradientPanel.add(source);

        JLabel lbldest = new JLabel("Destination");
        lbldest.setBounds(60, 380, 150, 25);
        lbldest.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gradientPanel.add(lbldest);

        destination = new Choice();
        destination.setBounds(220, 380, 150, 25);
        gradientPanel.add(destination);

        try {
            Conn c = new Conn();
            String query = "select * from flight";
            ResultSet rs = c.s.executeQuery(query);

            while (rs.next()) {
                source.add(rs.getString("source"));
                destination.add(rs.getString("dest"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        flight = createStyledButton("Fetch Flights", 380, 380);
        gradientPanel.add(flight);

        JLabel lblfname = new JLabel("Flight Name");
        lblfname.setBounds(60, 430, 150, 25);
        lblfname.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gradientPanel.add(lblfname);

        labelfname = new JLabel();
        labelfname.setBounds(220, 430, 150, 25);
        gradientPanel.add(labelfname);

        JLabel lblfcode = new JLabel("Flight Code");
        lblfcode.setBounds(60, 480, 150, 25);
        lblfcode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gradientPanel.add(lblfcode);

        labelfcode = new JLabel();
        labelfcode.setBounds(220, 480, 150, 25);
        gradientPanel.add(labelfcode);

        JLabel lbldate = new JLabel("Date of Travel");
        lbldate.setBounds(60, 530, 150, 25);
        lbldate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gradientPanel.add(lbldate);

        dcdate = new JDateChooser();
        dcdate.setBounds(220, 530, 150, 25);
        gradientPanel.add(dcdate);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("airlinemanagementsystem/icons/details.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 320, Image.SCALE_DEFAULT);
        ImageIcon image = new ImageIcon(i2);
        JLabel lblimage = new JLabel(image);
        lblimage.setBounds(550, 80, 500, 410);
        gradientPanel.add(lblimage);

        bookflight = createStyledButton("Book Flight", 220, 580);
        gradientPanel.add(bookflight);

        setSize(1100, 700);
        setLocation(200, 50);
        setVisible(true);
    }

    private JButton createStyledButton(String text, int x, int y) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 150, 25);
        button.setBackground(new Color(0, 102, 204));  // Blue background
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Tahoma", Font.PLAIN, 16));
        button.setFocusPainted(false);

        button.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));  // Padding to simulate rounded corners
        button.addActionListener(this);

        return button;
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetchButton) {
            String aadhar = tfaadhar.getText();

            try {
                Conn conn = new Conn();

                String query = "select * from passenger where aadhar = '" + aadhar + "'";

                ResultSet rs = conn.s.executeQuery(query);

                if (rs.next()) {
                    tfname.setText(rs.getString("name"));
                    tfnationality.setText(rs.getString("nationality"));
                    tfaddress.setText(rs.getString("address"));
                    labelgender.setText(rs.getString("gender"));
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter correct aadhar");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == flight) {
            String src = source.getSelectedItem();
            String dest = destination.getSelectedItem();
            try {
                Conn conn = new Conn();

                String query = "select * from flight where source = '" + src + "' and dest= '" + dest + "'";

                ResultSet rs = conn.s.executeQuery(query);

                if (rs.next()) {
                    labelfname.setText(rs.getString("f_name"));
                    labelfcode.setText(rs.getString("f_code"));
                } else {
                    JOptionPane.showMessageDialog(null, "No Flights Found");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Date selectedDate = dcdate.getDate();
            Date currentDate = new Date();

            if (selectedDate == null || selectedDate.before(currentDate)) {
                JOptionPane.showMessageDialog(null, "Please select a valid date (today or in the future).");
                return;
            }

            Random random = new Random();

            String pnr = "PNR-" + random.nextInt(1000000);
            String ticket = "TIC-" + random.nextInt(10000);
            String aadhar = tfaadhar.getText();
            String name = tfname.getText();
            String nationality = tfnationality.getText();
            String flightname = labelfname.getText();
            String flightcode = labelfcode.getText();
            String src = source.getSelectedItem();
            String des = destination.getSelectedItem();
            String ddate = ((JTextField) dcdate.getDateEditor().getUiComponent()).getText();

            try {
                Conn conn = new Conn();

                String query = "insert into reservation values('" + pnr + "', '" + ticket + "', '" + aadhar + "', '" + name + "', '" + nationality + "', '" + flightname + "', '" + flightcode + "', '" + src + "', '" + des + "', '" + ddate + "')";
                conn.s.executeUpdate(query);

                JOptionPane.showMessageDialog(null, "Ticket Booked Successfully! Your PNR is: " + pnr);

                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new BookFlight();
    }
}