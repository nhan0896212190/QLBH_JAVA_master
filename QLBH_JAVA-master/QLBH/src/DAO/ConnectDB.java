/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Duy
 */
public class ConnectDB {

    public static String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=PROJECTJAVA";
    //Tài khoản SQL Server
    public static String dbUser = "sa";
    //Mật khẩu SQL Server
    public static String dbPass = "password";

    public static Connection getConnect() {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(dbURL,dbUser,dbPass);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("connect database failure!");
        }
        return conn;
    }

}
