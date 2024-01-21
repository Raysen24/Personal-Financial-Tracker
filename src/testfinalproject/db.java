
package testfinalproject;

import java.sql.*;

//Database connection

public class db {
    
    public static Connection mycon(){
        Connection con = null;
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/final_project_sem1","root","");
            
        } catch (ClassNotFoundException | SQLException e) {
            
            System.out.println(e);
            
        }
        
        
        
        return con;
    }
        
    
    
}
