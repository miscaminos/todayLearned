package spring.utility.board;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
 
public class DBClose {
 
    public static void close(Connection con, PreparedStatement pstmt){
        if(pstmt != null){
            try {
                pstmt.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
  
    }
    
   public static void close(Connection con, PreparedStatement pstmt, ResultSet rs){
      if(rs != null){
        try {
            rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
      }
      
      if(pstmt != null){
          try {
              pstmt.close();
          } catch (SQLException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
      }
      
      if(con != null){
          try {
              con.close();
          } catch (SQLException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
      }
       
   }
}
 