import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddStudent extends Frame {
    TextField tfRollNo, tfName, tfContactNumber, tfSemester, tfFathersName, tfMothersName;
    Button btnAddStudent, btnBack;
    Label lblStatus;

    public AddStudent() {
        setTitle("Add Student");
        setSize(400, 350);
        setLocationRelativeTo(null);

        // Creating GUI components
        Label lblTitle = new Label("Add Student", Label.CENTER);
        Label lblRollNo = new Label("Roll No:");
        Label lblName = new Label("Name:");
        Label lblContactNumber = new Label("Contact Number:");
        Label lblSemester = new Label("Semester:");
        Label lblFathersName = new Label("Fathers Name:");
        Label lblMothersName = new Label("Mothers Name:");
        lblStatus = new Label("");

        tfRollNo = new TextField();
        tfName = new TextField();
        tfContactNumber = new TextField();
        tfSemester = new TextField();
        tfFathersName = new TextField();
        tfMothersName = new TextField();
        btnAddStudent = new Button("Add Student");
        btnBack = new Button("Back to Main");
        // Adding components to the frame
        setLayout(new GridLayout(10, 2, 10, 10)); // 6 rows, 2 columns, with gaps between components
        add(lblTitle);
        add(new Label("")); // Empty label for spacing
        add(lblRollNo);
        add(tfRollNo);
        add(lblName);
        add(tfName);
        add(lblContactNumber);
        add(tfContactNumber);
        add(lblSemester);
        add(tfSemester);
        add(lblFathersName);
        add(tfFathersName);
        add(lblMothersName);
        add(tfMothersName);
        add(new Label(""));
        add(btnAddStudent);
        add(new Label("")); // Empty label for spacing
        add(btnBack);
        add(lblStatus);
        // Handle add student button click
        btnAddStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int rollNo = Integer.parseInt(tfRollNo.getText());
                String name = tfName.getText();
                String fathersName = tfFathersName.getText();
                String mothersName = tfMothersName.getText();
                int contactNumber = Integer.parseInt(tfContactNumber.getText());
                int semester = Integer.parseInt(tfSemester.getText());

                try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SMS", "postgres", "Jalu@1234")) {
                    String query = "INSERT INTO studentdetails (roll_no, name, contact, sem, fathers_name, mothers_name) VALUES (?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement stmt = conn.prepareStatement(query)) {
                        stmt.setInt(1, rollNo);
                        stmt.setString(2, name);
                        stmt.setInt(3, contactNumber);
                        stmt.setInt(4, semester);
                        stmt.setString(5, fathersName);
                        stmt.setString(6, mothersName);
                        int rowsAffected = stmt.executeUpdate();
                        if (rowsAffected > 0) {
                            System.out.println("Student added successfully");
                            lblStatus.setText("Student added successfully"); // Set status message
                            clearFields();
                        } else {
                            System.out.println("Failed to add student");
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
                // Close the current window (AddStudentPage) and open the main page (MainApp)
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
        tfName.setText("");
        tfContactNumber.setText("");
        tfSemester.setText("");
        tfFathersName.setText("");
        tfMothersName.setText("");

    }

    public static void main(String[] args) {
        new AddStudent();
    }
}
