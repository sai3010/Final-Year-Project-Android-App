/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author saipr
 */
public class DBConnection {
    static Connection getDBConn() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "root");
                //Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3307/student", "root", "root");
		return con;
	}
}
