import java.awt.*;
import java.awt.event.*;

public class Main extends Frame {
    public Main() {
        setTitle("Student Management System");
        setSize(500, 300);
        setLocationRelativeTo(null);

        // Create buttons for different actions
        Button btnAddStudent = new Button("Add Student");
        Button btnDeleteStudent = new Button("Delete Student");
        Button btnUpdateStudent = new Button("Update Student");
        Button btnSearchStudent = new Button("Search Student");
        Button btnBack = new Button("Logout");


        // Add action listeners to the buttons
        btnAddStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Add Student action
                // Open a new frame or perform the desired action
                System.out.println("Add Student button clicked");
                dispose();
                openAddStudent();
            }
        });

        btnDeleteStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Delete Student action
                // Open a new frame or perform the desired action
                System.out.println("Delete Student button clicked");
                dispose();
                openDeleteStudent();
            }
        });

        btnUpdateStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Update Student action
                // Open a new frame or perform the desired action
                System.out.println("Update Student button clicked");
                dispose();
                openUpdateStudent();
            }
        });

        btnSearchStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle Search Student action
                // Open a new frame or perform the desired action
                System.out.println("Search Student button clicked");
                dispose();
                openSearchStudent();
            }
        });

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // Close the current window (AddStudentPage) and open the main page (MainApp)
                dispose(); // Close the current window
                new FrontPage(); // Open the main page
            }
        });

        // Set layout for the frame
        setLayout(new GridLayout(5, 1, 10, 10)); // 4 rows, 1 column, with gaps between components

        // Add buttons to the frame
        add(btnAddStudent);
        add(btnDeleteStudent);
        add(btnUpdateStudent);
        add(btnSearchStudent);
        add(btnBack);
        // Handle window closing event
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    private void openAddStudent() {
        new AddStudent(); // Open the main application page
    }

    private void openDeleteStudent() {
        new DeleteStudent(); // Open the main application page
    }
    private void openUpdateStudent() {
        new UpdateStudent(); // Open the main application page
    }
    private void openSearchStudent() {
        new SearchStudent(); // Open the main application page
    }




    public static void main(String[] args) {
        new Main();
    }
}
