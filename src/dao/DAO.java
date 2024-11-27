/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author admin
 */
import java.sql.Connection;
import java.sql.DriverManager;


public class DAO {
    protected static Connection con;
    
    public DAO() {
        String url = "jdbc:mysql://localhost:3306/my_libary?autoReconnect=true&useSSL=false";
        String username = "root";
        String password = "123456789";
        
        if(con == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(url, username, password);
            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    
}
