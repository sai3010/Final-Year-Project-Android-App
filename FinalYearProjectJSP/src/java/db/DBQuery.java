/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author saipr
 */
public class DBQuery {
        static Connection con= null;
	static Statement st= null;
	static ResultSet rs= null;
    public int add_data(String uname, String pass) throws SQLException, ClassNotFoundException
	{
		con= DBConnection.getDBConn();
		st= con.createStatement();
		String query="insert into studinfo values('"+uname+"','"+pass+"')";
		int i= st.executeUpdate(query);
		con.close();
		return i;
	}
    
    
}
