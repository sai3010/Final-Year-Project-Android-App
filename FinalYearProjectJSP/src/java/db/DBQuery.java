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

        
    public String[] get_stud_login_data(String usn) throws ClassNotFoundException, SQLException
    {
           
                String val[] = new String[9];
		con=DBConnection.getDBConn();
		st= con.createStatement();
		String query="select * from student_information where usn='"+usn+"'";
		rs= st.executeQuery(query);
		while(rs.next())
		{
                        
			val[0]= rs.getString("password");
                        val[1]=rs.getString("firstname");
                        val[2]=rs.getString("email");
                        val[3]=rs.getString("usn");
                        val[4]=rs.getString("lastname");
                        val[5]=rs.getString("sem");
                        val[6]=rs.getString("address");
                        val[7]=rs.getString("phone");
                        val[8]=rs.getString("branch");
//                        arr.add(val);
		}
		con.close();
		return val;
	
    }
    public String [] get_fac_login_data(String usn) throws ClassNotFoundException, SQLException
    {
       String [] val= new String[9];
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
                        val[4]=rs.getString("lastname");
                        val[5]=rs.getString("qual");
                        val[6]=rs.getString("address");
                        val[7]=rs.getString("phone");
                        val[8]=rs.getString("branch");      
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
    public int add_fac_data(String fname,String lname, String usn, String qual, String email, String address, String pass, String phone, String dob, String gender,String branch) throws ClassNotFoundException, SQLException {
                con= DBConnection.getDBConn();
		st= con.createStatement();
		String query="insert into faculty_information values('"+fname+"','"+lname+"','"+usn+"','"+qual+"','"+email+"','"+address+"','"+pass+"','"+phone+"','"+dob+"','"+gender+"','"+branch+"')";
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

    public String [] get_admin_login_data(String uname) throws ClassNotFoundException, SQLException
    {
         String [] val= new String[2];
		con=DBConnection.getDBConn();
		st= con.createStatement();
		String query="select * from admin_information where uname='"+uname+"'";
		rs= st.executeQuery(query);
		while(rs.next())
		{
			val[0]= rs.getString("password");
                        val[1]=rs.getString("uname");
                }
		
		con.close();
		return val;
        
    }

    public int add_placement_data(String ccode, String cname, String ctier) throws SQLException, ClassNotFoundException {
                    con= DBConnection.getDBConn();
                    st= con.createStatement();
                    String query = "insert into company_details values('"+ccode+"','"+cname+"','"+ctier+"')"; 
                   int i= st.executeUpdate(query);
                    con.close();
                    return i;
       }

    public int add_placements_data(String ccode, String cbranch) throws ClassNotFoundException, SQLException {
  con= DBConnection.getDBConn();
                    st= con.createStatement();
                    String query = "insert into ctype('"+ccode+"','"+cbranch+"')"; 
                    int i= st.executeUpdate(query);
                    con.close();
                    return i;
    }

    public int update_fac_data(String firstname, String lastname, String usn, String qual, String email, String add1, String password, String phone, String dob, String gender, String branch) throws SQLException, ClassNotFoundException {
                  con= DBConnection.getDBConn();
                    st= con.createStatement();
                    
                    String query ="update  faculty_information set firstname='"+firstname+"',lastname='"+lastname+"',qual='"+qual+"',email='"+email+"',address='"+add1+"',password='"+password+"',dob='"+dob+"',gender='"+gender+"',branch='"+branch+"' where usn='"+usn+"'";
                     int i= st.executeUpdate(query);
                    con.close();
                    return i;    
    }

    public int delete_fac_data(String usn) throws ClassNotFoundException, SQLException {
                con= DBConnection.getDBConn();
		st= con.createStatement();
		String query= "delete from faculty_information where usn='"+usn+"'";
                int i= st.executeUpdate(query);
		
                con.close();
		return i;
		
        
    }
    public int update_stud_data(String firstname, String lastname, String usn, String sem, String email, String add1, String password, String phone, String dob, String gender, String branch) throws SQLException, ClassNotFoundException {
                  con= DBConnection.getDBConn();
                  st= con.createStatement();                    
                    String query ="update  student_information set firstname='"+firstname+"',lastname='"+lastname+"',sem='"+sem+"',email='"+email+"',address='"+add1+"',password='"+password+"',dob='"+dob+"',gender='"+gender+"',branch='"+branch+"' where usn='"+usn+"'";
                     int i= st.executeUpdate(query);
                    con.close();
                    return i;    
    }

    public int delete_stud_data(String usn) throws ClassNotFoundException, SQLException {
           con= DBConnection.getDBConn();
		st= con.createStatement();
		String query= "delete from student_information where usn='"+usn+"'";
                int i= st.executeUpdate(query);
		
                con.close();
		return i;
    }

    public String[] view_stud_data(String usn) throws ClassNotFoundException, SQLException {
        con= DBConnection.getDBConn();
        st= con.createStatement();
        String val[] = new String[9];
        System.out.println("usn = " + usn);
        String query="select * from student_information where usn='"+usn+"'";
        rs= st.executeQuery(query);

        while(rs.next())
        {
                     
                        val[0]=rs.getString("firstname");
                        val[1]=rs.getString("lastname");
                        val[2]=rs.getString("usn");
                        val[3]=rs.getString("sem");
                        val[4]=rs.getString("email");
                        val[5]=rs.getString("address");
                        val[6]=rs.getString("phone");
                        val[7]=rs.getString("gender");
                        val[8]=rs.getString("branch");
                      
           
        }
        return val;
    }

  
      public String[] view_fac_data(String usn) throws ClassNotFoundException, SQLException {
        con= DBConnection.getDBConn();
        st= con.createStatement();
        String val[] = new String[9];
        System.out.println("usn = " + usn);
        String query="select * from faculty_information where usn='"+usn+"'";
        rs= st.executeQuery(query);

        while(rs.next())
        {
                     
                        val[0]=rs.getString("firstname");
                        val[1]=rs.getString("lastname");
                        val[2]=rs.getString("usn");
                        val[3]=rs.getString("qual");
                        val[4]=rs.getString("email");
                        val[5]=rs.getString("address");
                        val[6]=rs.getString("phone");
                        val[7]=rs.getString("gender");
                        val[8]=rs.getString("branch");
                        
                      
           
        }
        return val;
    }

    	
    
    
    
}
