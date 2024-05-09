import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FrontPage {
    public FrontPage() {
        JFrame frame = new JFrame("Student Management System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Creating buttons
        JButton btnStudentLogin = new JButton("Continue as Student");
        JButton btnAdminLogin = new JButton("Login as Admin");

        // Adding buttons to the frame
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10));
        panel.add(btnStudentLogin);
        panel.add(btnAdminLogin);

        frame.add(panel);

        // Action listener for student login button
        btnStudentLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the login page for student
                frame.dispose(); // Close the current window
                new StudentLogin(); // Open student login page
            }
        });

        // Action listener for admin login button
        btnAdminLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the login page for admin
                frame.dispose(); // Close the current window
                new Login(); // Open admin login page
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new FrontPage();
    }
}
