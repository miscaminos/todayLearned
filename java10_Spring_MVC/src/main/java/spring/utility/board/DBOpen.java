package spring.utility.board;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 
public class DBOpen {
 
    static{
        try {
            Class.forName(Constant.driver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public static Connection open() {
        Connection con = null;
    
        try {
           con = DriverManager.getConnection(Constant.url, Constant.user, Constant.passwd);
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
             
        return con;
        
    }
}