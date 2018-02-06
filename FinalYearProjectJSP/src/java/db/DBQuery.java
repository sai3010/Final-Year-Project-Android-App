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
    public String get_login_data(String usn) throws ClassNotFoundException, SQLException
    {
        String val= null;
		con=DBConnection.getDBConn();
		st= con.createStatement();
		String query="select * from student_information where usn='"+usn+"'";
		rs= st.executeQuery(query);
		while(rs.next())
		{
			val= rs.getString("password");
		}
		
		con.close();
		return val;
	
    }

    public int add_stud_data(String fname,String lname, String usn, String sem, String email, String address, String pass, String phone, String dob, String gender) throws ClassNotFoundException, SQLException {
                con= DBConnection.getDBConn();
		st= con.createStatement();
		String query="insert into student_information values('"+fname+"','"+lname+"','"+usn+"','"+sem+"','"+email+"','"+address+"','"+pass+"','"+phone+"','"+dob+"','"+gender+"')";
		int i= st.executeUpdate(query);
		con.close();
		return i;
    }

    
    
    
}
