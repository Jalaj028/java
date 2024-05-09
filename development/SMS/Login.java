import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Login extends Frame {
    TextField tfUsername, tfPassword;
    Button btnLogin, btnBack;

    Panel fields, button, mainPanel;


    public Login() {

        fields = new Panel();
        button = new Panel();
        mainPanel = new Panel();
        // Creating GUI components
        Label lblUsername = new Label("Username:");
        Label lblPassword = new Label("Password:");
        Label lblTitle = new Label("login Page", Label.CENTER);

        tfUsername = new TextField();
        tfPassword = new TextField();
        tfPassword.setEchoChar('*');
        btnLogin = new Button("Login");
        btnBack = new Button("Back to home");

        // Setting layout
        fields.setLayout(new GridLayout(2, 2));

        fields.add(lblUsername);
        fields.add(tfUsername);
        fields.add(lblPassword);
        fields.add(tfPassword);
//        fields.add(btnLogin);

        button.add(btnLogin);
        button.add(btnBack);
        mainPanel.setPreferredSize(new Dimension(400, 250));
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(lblTitle, BorderLayout.NORTH);
        mainPanel.add(fields, BorderLayout.CENTER);
        mainPanel.add(button, BorderLayout.SOUTH);
        ((GridLayout)fields.getLayout()).setVgap(20);
        ((BorderLayout)mainPanel.getLayout()).setVgap(60);


        setLayout(new FlowLayout());

        add(mainPanel);

        // Adding action listener
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String username = tfUsername.getText();
                String password = tfPassword.getText();
                Boolean flag = false;

                try(Connection conn = Database.connectDb(); Statement st = conn.createStatement()){
                    ResultSet rs = st.executeQuery("SELECT username, password FROM superuser");
                    while (rs.next()) {
                        if (rs.getString("userName").equals(username) && rs.getString("password").equals(password)){flag = true; break;}
                    }
                    if (flag){
                        System.out.println("login successful");
                        dispose();
                        openMain();
                    }else {
                        System.out.println("login failed");
                    }
                }catch(SQLException e){
                    System.out.println(e.getMessage());
                }
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

        // Frame settings
        setTitle("Login");
        setSize(500, 300);
        setLocationRelativeTo(null);
        setVisible(true);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.exit(0);
            }
        });
    }


    private void openMain() {
        new Main(); // Open the main application page
    }
    public static void main(String[] args) {
        new Login();
    }
}
