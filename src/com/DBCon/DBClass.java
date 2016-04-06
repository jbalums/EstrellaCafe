package com.DBCon;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBClass{    
     public Connection getConnection() throws ClassNotFoundException, SQLException{       
           Class.forName("com.mysql.jdbc.Driver");
           return (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/group4_db_estrella","root",""); 
     }
}