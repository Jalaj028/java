import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SearchStudent extends Frame {
    TextField tfRollNo;
    TextArea taStudentDetails;
    Button btnSearch, btnBack;
    Label lblStatus;

    public SearchStudent() {
        setTitle("Search Student");
        setSize(400, 300);
        setLocationRelativeTo(null);

        // Creating GUI components
        Label lblTitle = new Label("Search Student", Label.CENTER);
        Label lblRollNo = new Label("Enter Roll No:");
        lblStatus = new Label("");

        tfRollNo = new TextField();
        taStudentDetails = new TextArea();
        taStudentDetails.setEditable(false); // Ensure the text area is not editable
        btnSearch = new Button("Search");
        btnBack = new Button("Back to Main");

        // Adding components to the frame
        setLayout(new GridLayout(6, 2, 10, 10)); // 6 rows, 2 columns, with gaps between components
        add(lblTitle);
        add(new Label("")); // Empty label for spacing
        add(lblRollNo);
        add(tfRollNo);
        add(new Label("")); // Empty label for spacing
        add(btnSearch);
        add(new Label("")); // Empty label for spacing
        add(taStudentDetails);
        add(new Label("")); // Empty label for spacing
        add(btnBack);
        add(lblStatus);

        // Handle search button click
        btnSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                int rollNo = Integer.parseInt(tfRollNo.getText());

                try (Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SMS", "postgres", "Jalu@1234")) {
                    String query = "SELECT * FROM studentdetails WHERE roll_no = ?";
                    try (PreparedStatement stmt = conn.prepareStatement(query)) {
                        stmt.setInt(1, rollNo);
                        ResultSet rs = stmt.executeQuery();

                        if (rs.next()) {
                            // Student found, display details
                            String details = "Roll No: " + rs.getInt("roll_no") +
                                    "\nName: " + rs.getString("name") +
                                    "\nContact Number: " + rs.getInt("contact") +
                                    "\nSemester: " + rs.getInt("sem")+
                                    "\nFathers Name: " + rs.getString("fathers_name") +
                                    "\nMothers Name: " + rs.getString("mothers_name") ;
                            taStudentDetails.setText(details);
                            lblStatus.setText("Student found");
                        } else {
                            // Student not found
                            taStudentDetails.setText("");
                            lblStatus.setText("Student not found");
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
                // Close the current window (SearchStudent) and open the main page (MainApp)
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

    public static void main(String[] args) {
        new SearchStudent();
    }
}
