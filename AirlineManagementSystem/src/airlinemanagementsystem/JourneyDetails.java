
package airlinemanagementsystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class JourneyDetails extends JFrame implements ActionListener {
    JTable table;
    JComboBox<String> pnrDropdown;
    JButton show;

    public JourneyDetails() {
        setTitle("Journey Details");
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

        JLabel lblpnr = new JLabel("PNR Details");
        lblpnr.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblpnr.setBounds(50, 50, 100, 25);
        gradientPanel.add(lblpnr);

        pnrDropdown = new JComboBox<>();
        pnrDropdown.setBounds(160, 50, 120, 25);
        gradientPanel.add(pnrDropdown);

        show = new JButton("Show Details");
        show.setBackground(new Color(0, 102, 204));
        show.setForeground(Color.WHITE);
        show.setFont(new Font("Tahoma", Font.BOLD, 14));
        show.setFocusPainted(false);
        show.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        show.setBounds(290, 50, 140, 35);
        show.addActionListener(this);
        gradientPanel.add(show);

        table = new JTable();

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(20, 120, 900, 50);
        jsp.setBackground(Color.WHITE);
        gradientPanel.add(jsp);

        // Fetch available PNRs to populate the dropdown list
        try {
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select PNR from reservation");
            while (rs.next()) {
                pnrDropdown.addItem(rs.getString("PNR"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setSize(950, 500);
        setLocation(400, 150);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            String selectedPNR = (String) pnrDropdown.getSelectedItem();
            Conn conn = new Conn();
            ResultSet rs = conn.s.executeQuery("select * from reservation where PNR = '" + selectedPNR + "'");

            if (!rs.isBeforeFirst()) {
                JOptionPane.showMessageDialog(null, "No Information Found");
                return;
            }
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new JourneyDetails();
    }
}

