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
import java.util.ArrayList;
/**
 *
 * @author saipr
 */
public class DBQuery {
        static Connection con= null;
	static Statement st= null;
	static ResultSet rs= null;
        ArrayList<String> alist= null;

        
    public ArrayList<String[]> get_stud_login_data(String usn) throws ClassNotFoundException, SQLException
    {
                ArrayList<String[]> arr = new ArrayList<>();
		con=DBConnection.getDBConn();
		st= con.createStatement();
		String query="select * from student_information where usn='"+usn+"'";
		rs= st.executeQuery(query);
		while(rs.next())
		{
                        String val[] = new String[4];
			val[0]= rs.getString("password");
                        val[1]=rs.getString("firstname");
                        val[2]=rs.getString("email");
                        val[3]=rs.getString("usn");
                        arr.add(val);
		}
		con.close();
		return arr;
	
    }
    public String [] get_fac_login_data(String usn) throws ClassNotFoundException, SQLException
    {
       String [] val= new String[4];
		con=DBConnection.getDBConn();
		st= con.createStatement();
		String query="select * from faculty_information where usn='"+usn+"'";
		rs= st.executeQuery(query);
		while(rs.next())
		{
			val[0]= rs.getString("password");
                        val[1]=rs.getString("firstname");
                        val[2]=rs.getString("email");
                        val[3]=rs.getString("usn");
		}
		
		con.close();
		return val;
    }


    public int add_stud_data(String fname,String lname, String usn, String sem, String email, String address, String pass, String phone, String dob, String gender,String branch) throws ClassNotFoundException, SQLException {
                con= DBConnection.getDBConn();
		st= con.createStatement();
		String query="insert into student_information values('"+fname+"','"+lname+"','"+usn+"','"+sem+"','"+email+"','"+address+"','"+pass+"','"+phone+"','"+dob+"','"+gender+"','"+branch+"')";
		int i= st.executeUpdate(query);
		con.close();
		return i;
    }
    public int add_fac_data(String fname,String lname, String usn, String qual, String email, String address, String pass, String phone, String dob, String gender) throws ClassNotFoundException, SQLException {
                con= DBConnection.getDBConn();
		st= con.createStatement();
		String query="insert into faculty_information values('"+fname+"','"+lname+"','"+usn+"','"+qual+"','"+email+"','"+address+"','"+pass+"','"+phone+"','"+dob+"','"+gender+"')";
		int i= st.executeUpdate(query);
		con.close();
		return i;
    }

    /**
     *
     * @param query
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public String getCompany_Details(String query) throws ClassNotFoundException, SQLException 
    {
        String cname="";
        con= DBConnection.getDBConn();
        st= con.createStatement();
        
        rs= st.executeQuery(query);
        while(rs.next())
        {
            if(cname.equals(""))
            {
                cname=rs.getString("cname");
            }
            else
            {
                cname=rs.getString("cname")+":"+cname;
            }
            
        }
        System.out.println("cname = " + cname);
        return cname;
    }

    
    
    
    
}
