import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class Database {
    public static Connection connectDb() throws SQLException{
       Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/SMS", "postgres", "Jalu@1234");
               return con;
      }

    public static void closeConnection(Connection conn) {
        try{if(conn != null) conn.close();}catch(SQLException e){
            System.out.println(e.getStackTrace());
        }
    }

}


