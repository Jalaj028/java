import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DeleteStudent extends Frame {
    TextField tfRollNo;
    Button btnDeleteStudent, btnBack;
    Label lblStatus;

    public DeleteStudent() {
        setTitle("Delete Student");
        setSize(400, 250);
        setLocationRelativeTo(null);

        // Creating GUI components
        Label lblTitle = new Label("Delete Student", Label.CENTER);
        Label lblRollNo = new Label("Roll No:");
        lblStatus = new Label("");

        tfRollNo = new TextField();
        btnDeleteStudent = new Button("Delete Student");
        btnBack = new Button("Back to Main");

        // Adding components to the frame
        setLayout(new GridLayout(5, 2, 10, 10)); // 5 rows, 2 columns, with gaps between components
        add(lblTitle);
        add(new Label("")); // Empty label for spacing
        add(lblRollNo);
        add(tfRollNo);
        add(new Label("")); // Empty label for spacing
        add(btnDeleteStudent);
        add(new Label("")); // Empty label for spacing
        add(btnBack);
        add(lblStatus);

        // Handle delete student button click
        btnDeleteStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int rollNo = Integer.parseInt(tfRollNo.getText());

                try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SMS", "postgres", "Jalu@1234")) {
                    String query = "DELETE FROM studentdetails WHERE roll_no = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(query)) {
                        stmt.setInt(1, rollNo);
                        int rowsAffected = stmt.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Student deleted successfully");
                            lblStatus.setText("Student deleted successfully"); // Set status message
                            clearFields();
                        } else {
                            System.out.println("No student found with Roll Number " + rollNo);
                            lblStatus.setText("No student found with Roll Number " + rollNo);
                        }
                    }
                } catch (SQLException e) {
                    System.out.println("Database error: " + e.getMessage());
                }
            }
        });

        // Handle back button click
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // Close the current window (DeleteStudent) and open the main page (Main)
                dispose(); // Close the current window
                new Main(); // Open the main page
            }
        });

        // Handle window closing event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });

        setVisible(true);
    }

    private void clearFields() {
        tfRollNo.setText("");
    }

    public static void main(String[] args) {
        new DeleteStudent();
    }
}
