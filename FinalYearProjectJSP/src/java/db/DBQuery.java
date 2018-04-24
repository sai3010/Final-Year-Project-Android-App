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
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author saipr
 */
public class DBQuery {
        static Connection con= null;
	static Statement st= null;
	static ResultSet rs= null;
        String str1="",str2="",regex=";";
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
			val[0]=rs.getString("password");
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


    public int add_stud_data(String fname,String lname, String usn, String sem, String email, String address, String pass, String phone, String dob, String gender,String branch) throws ClassNotFoundException {
        int i=-1;    
        try {
                con= DBConnection.getDBConn();
                st= con.createStatement();
		String query="insert into student_information values('"+fname+"','"+lname+"','"+usn+"','"+sem+"','"+email+"','"+address+"','"+pass+"','"+phone+"','"+dob+"','"+gender+"','"+branch+"')";
		i= st.executeUpdate(query);
		
            } catch (SQLException ex) {
               i=100;
            }
        finally
        {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
		return i;
    }
    public int add_fac_data(String fname,String lname, String usn, String qual, String email, String address, String pass, String phone, String dob, String gender,String branch) throws ClassNotFoundException {
        int i=-1;    
        try {
                con= DBConnection.getDBConn();
                st= con.createStatement();
		String query="insert into faculty_information values('"+fname+"','"+lname+"','"+usn+"','"+qual+"','"+email+"','"+address+"','"+pass+"','"+phone+"','"+dob+"','"+gender+"','"+branch+"')";
		i= st.executeUpdate(query);
		
            } catch (SQLException ex) {
                i=100;
            }
        finally
        {
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(DBQuery.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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

    public int add_placement_data(String ccode, String cname, String ctier) throws SQLException,ClassNotFoundException {
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
                    String query = "insert into ctype(ccode,cbranch) values('"+ccode+"','"+cbranch+"')"; 
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
        System.out.println("query = " + query);
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
        System.out.println("val[0] = " + val[0]);
        con.close();
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
        con.close();
        return val;
    }

    public ArrayList getFacSecDetails(String usn) throws SQLException, ClassNotFoundException {
        con= DBConnection.getDBConn();
        st= con.createStatement();
       ArrayList<String>sb=new ArrayList<>();
        String query="select scode,section from assign_fac where fusn='"+usn+"'";
        rs= st.executeQuery(query);
        while(rs.next())
        {
            sb.add(rs.getString("scode"));
            sb.add(rs.getString("section"));
        }
        con.close();
        return sb;
    }

    public ArrayList<String> get_stud_det(String branch, String sem) throws ClassNotFoundException, SQLException {
        con= DBConnection.getDBConn();
        st= con.createStatement();
        ArrayList<String> names=new ArrayList<>();
        System.out.println("branch = " + branch);
        System.out.println("sem = " + sem);
        String query="select susn from academic_student_details where section='"+sem.trim()+"' and sbranch='"+branch.trim()+"'";
        rs=st.executeQuery(query);
        while(rs.next())
        {
            names.add(rs.getString("susn"));
//            System.out.println("names="+names);
        }

        System.out.println("names="+names);
        con.close();
        return names;
    }

    public int update_about_fac(String fname, String lname, String qual, String email, String address, String password,String usn) throws SQLException, ClassNotFoundException {
        con= DBConnection.getDBConn();
        st= con.createStatement();
        String query ="update  faculty_information set firstname='"+fname+"',lastname='"+lname+"',qual='"+qual+"',email='"+email+"',address='"+address+"',password='"+password+"'where usn='"+usn+"'";
        int i= st.executeUpdate(query);
        con.close();
        return i;

    }

    public int update_stud_fac(String fname, String lname, String qual, String email, String address, String password, String usn) throws ClassNotFoundException, SQLException {
          con= DBConnection.getDBConn();
        st= con.createStatement();
        String query ="update  student_information set firstname='"+fname+"',lastname='"+lname+"',qual='"+qual+"',email='"+email+"',address='"+address+"',password='"+password+"'where usn='"+usn+"'";
        int i= st.executeUpdate(query);
        con.close();
        return i;

           }

    public int add_aggregate_marks(String usn,String branch) throws ClassNotFoundException, SQLException
    {
        System.out.println("usn = " + usn);
        System.out.println("branch = " + branch);
        con= DBConnection.getDBConn();
        st= con.createStatement();
        String query ="insert into  student_aggregate(usn,branch) values ('"+usn+"','"+branch+"')";
        int j= st.executeUpdate(query);
        con.close();
	return j;
    }
    
    public String[] get_student_aggregate_data(String usn1,String branch) throws SQLException, ClassNotFoundException
    {
        String[] val=new String [11];
        
        con= DBConnection.getDBConn();
        st= con.createStatement();
         String query="select * from  student_aggregate where usn='"+usn1+"'and branch='"+branch+"'";
        
        rs= st.executeQuery(query);
        while(rs.next())
        {
            val[0]= rs.getString("usn");
            val[1]= rs.getString("branch");
            val[2]=rs.getString("sem1");
            val[3]=rs.getString("sem2");
            val[4]=rs.getString("sem3");
            val[5]=rs.getString("sem4");
            val[6]=rs.getString("sem5");
            val[7]=rs.getString("sem6");
            val[8]=rs.getString("sem7");
            val[9]=rs.getString("sem8");
            val[10]=rs.getString("aggregate");
            
        }
		
		con.close();
		return val;
      
        }
    
    public int update_student_sem_marks( Double ssem1, Double ssem2,Double ssem3,Double ssem4,Double ssem5,Double ssem6,Double ssem7,Double ssem8,String usn1,Double aggsum) throws SQLException, ClassNotFoundException
    {
        con= DBConnection.getDBConn();
        st= con.createStatement();
        String query="update student_aggregate set sem1='"+ssem1+"',sem2='"+ssem2+"',sem3='"+ssem3+"',sem4='"+ssem4+"',sem5='"+ssem5+"',sem6='"+ssem6+"',sem7='"+ssem7+"',sem8='"+ssem8+"',aggregate='"+aggsum+"' where usn='"+usn1+"'";
        int i= st.executeUpdate(query);
         con.close();
         return i;   
        
    }

    public ArrayList get_fac_assign(String usn1) throws ClassNotFoundException, SQLException {
        con= DBConnection.getDBConn();
        st= con.createStatement();
        ArrayList<String> arr=new ArrayList<>();
        String s="assign_fac";
        String query="select scode, section from "+s+" where fusn='"+usn1+"'";
       rs= st.executeQuery(query);
         while(rs.next())
		{
			arr.add(rs.getString("scode"));
                        arr.add(rs.getString("section"));
                            
		}
		
		con.close();
		return arr;
      
    }   

    public String get_stud_section(String usn) throws ClassNotFoundException, SQLException {
         String val=null;
       con= DBConnection.getDBConn();
       st= con.createStatement();
       
       String query="select section from academic_student_details where susn='"+usn+"'";
       rs= st.executeQuery(query); 
       while(rs.next())
       {
       val=rs.getString("section");
    }
       con.close();
       return val;
    }

    public ArrayList get_stud_sub(String usn, String branch, String sem) throws ClassNotFoundException, SQLException {
    ArrayList<String> val= new ArrayList<>();
       con= DBConnection.getDBConn();
       st= con.createStatement();
        String query="select scode from subjects where brname='"+branch+"' and sem='"+sem+"' ";
        rs= st.executeQuery(query); 
        while(rs.next())
       {
       val.add(rs.getString("scode"));
        }
        con.close();
        return val;
    }

    public ArrayList<String> get_studmarks(String usn, String scode) throws ClassNotFoundException, SQLException {
        ArrayList<String>val=new ArrayList<>();
       con= DBConnection.getDBConn();
       st= con.createStatement();
       String query="select * from "+scode+" where usn='"+usn+"'";
        rs= st.executeQuery(query); 
        while(rs.next())
       {
       val.add(rs.getString("IA1"));
       val.add(rs.getString("IA2"));
       val.add(rs.getString("IA3"));
        
        }
        
        con.close();
        System.out.println("query = " + query);
        return val;
        
    }
        

      public ArrayList<String> get_student_marks(String scode1,String sec) throws ClassNotFoundException, SQLException
      {
          con= DBConnection.getDBConn();
        st= con.createStatement();
        ArrayList<String> arr=new ArrayList<>();
          System.out.println("Scode= = " +scode1 );
         String query="select usn,IA1,IA2,IA3 from "+scode1.toLowerCase()+" where section='"+sec.trim()+"'"; 
         System.out.println("query = " + query);
         rs= st.executeQuery(query);
          while(rs.next())
		{   
                    arr.add(rs.getString("usn"));
                    
                        arr.add(rs.getString("IA1"));
                         arr.add(rs.getString("IA2"));
                          arr.add(rs.getString("IA3"));
                            
		}
		 System.out.println("arr = " + arr);
		con.close();
		return arr;
      }


    public int update_stud_marks(String usn,String mark,String tabname,String ia) throws SQLException, ClassNotFoundException {
        con= DBConnection.getDBConn();
        st= con.createStatement();
        String query ="update "+tabname+" set "+ia+"='"+mark+"' where usn='"+usn+"'";
        System.out.println("query = " + query);
        int i= st.executeUpdate(query);
        con.close();
        return i;
    }

    public ArrayList getFacSecSub(String usn) throws ClassNotFoundException, SQLException {
        con= DBConnection.getDBConn();
        st= con.createStatement();
        ArrayList<String> list=new ArrayList<>();
        String query ="select * from assign_fac where fusn='"+usn+"'";
        rs= st.executeQuery(query);
        while(rs.next())
        {
            list.add(rs.getString("scode"));
            list.add(rs.getString("section"));
        }
        con.close();
        return list;
    }

    public String[] getstudusn(String rfno) throws ClassNotFoundException, SQLException {
       con= DBConnection.getDBConn();
        st= con.createStatement();
        String [] det=new String[4];
        String query ="select susn,sname,sbranch,section from academic_student_details where rfid_tag='"+rfno+"'";
        rs= st.executeQuery(query);
        while(rs.next())
        {
            det[0]=rs.getString("susn");
            det[1]=rs.getString("sname");
            det[2]=rs.getString("sbranch");
            det[3]=rs.getString("section");
        }
        con.close();
        return det;
    }

    public int temp_att(String rfno, String usn, String name,String branch,String sec) throws ClassNotFoundException, SQLException {
        con= DBConnection.getDBConn();
        st= con.createStatement();	
        String query ="insert into temp_attendance values('"+usn+"','"+name+"','"+rfno+"','"+branch+"','"+sec+"')";
        int i= st.executeUpdate(query);
        con.close();
        return i;
    }

    public ArrayList<String> get_scode(String sem,String branch) throws SQLException, ClassNotFoundException {
        ArrayList<String> scode=new ArrayList<>();
        con= DBConnection.getDBConn();
        st= con.createStatement();
        String query ="select scode from subjects where sem='"+sem+"' and brname='"+branch+"'";
        System.out.println("query = " + query);
        rs= st.executeQuery(query);
        while(rs.next())
        {
            scode.add(rs.getString("scode"));
        }
        con.close();
        return scode;
     }

    public StringBuilder get_att(String tabname,String usn) throws ClassNotFoundException, SQLException {
        con= DBConnection.getDBConn();
        st= con.createStatement();
        StringBuilder s=new StringBuilder();
        String query ="select Attendance from "+tabname+" where usn='"+usn+"'";
        System.out.println("query = " + query);
        rs= st.executeQuery(query);
        while(rs.next())
        {
            s.append(rs.getString("Attendance"));
        }
        con.close();
        return s;
    }

    public ArrayList<String> getTempAtt(String sec, String branch) throws ClassNotFoundException, SQLException {
        con= DBConnection.getDBConn();
        st= con.createStatement();
        ArrayList<String> a=new ArrayList<>();
        String query ="select usn from temp_attendance where section='"+sec+"' and branch='"+branch+"'";
        System.out.println("query = " + query);
        rs= st.executeQuery(query);
        while(rs.next())
        {
            a.add(rs.getString("usn"));
//            a.add(rs.getString("name"));
        }
        con.close();
        return a;
    }

    public int updateatt(String scode, String string, String sec) throws SQLException, ClassNotFoundException {
        con= DBConnection.getDBConn();
        st= con.createStatement();
        String string1 =string.trim();
         String query ="update "+scode+" set Attendance=Attendance+1 where usn='"+string1+"'";
        System.out.println("query = " + query);
        int i = st.executeUpdate(query);
        con.close();
        return i;
        
       
    }

    public int updatecc(String scode, String sec) throws SQLException, ClassNotFoundException {
        con= DBConnection.getDBConn();
        st= con.createStatement();
        String query="update classcount set count=count+1 where scode='"+scode.trim()+"' and section='"+sec.trim()+"'";
        int i = st.executeUpdate(query);
        con.close();
        return i;
    }

    public int getcc(String scode, String sec) throws SQLException, ClassNotFoundException {
        con= DBConnection.getDBConn();
        st= con.createStatement();
        String res=null;
        String query="select count from classcount where scode='"+scode.trim()+"' and section='"+sec.trim()+"'";
        rs= st.executeQuery(query);
        while(rs.next())
        {
            res=rs.getString("count");
        }
        con.close();
        return Integer.parseInt(res);
    }
    
     public ArrayList<String> getattofstud(String scode, String sec) throws ClassNotFoundException, SQLException {
        con= DBConnection.getDBConn();
        st= con.createStatement();
        ArrayList<String> res=new ArrayList<>();
        String query="select Attendance from "+scode+"";
        rs= st.executeQuery(query);
        while(rs.next())
        {
            res.add(rs.getString("Attendance"));
        }
        con.close();
        return res;
     }

    public int updateattper(ArrayList avg, ArrayList usn,String scode, String sec) throws ClassNotFoundException, SQLException {
        con= DBConnection.getDBConn();
        st= con.createStatement();
        String query=null;
        int kj=-1;
        for(int i=0;i<avg.size()&&i<usn.size();i++)
        {
        query="update "+scode+" set attavg='"+avg.get(i)+"' where usn='"+usn.get(i)+"'";
         System.out.println("query = " + query);
        kj = st.executeUpdate(query);
        }
       
        con.close();
        return kj;
    }

    public ArrayList<String> getusnofstud(String scode, String sec) throws ClassNotFoundException, SQLException {
        con= DBConnection.getDBConn();
        st= con.createStatement();
        ArrayList<String> res=new ArrayList<>();
        String query="select usn from "+scode+"";
        rs= st.executeQuery(query);
        while(rs.next())
        {
            res.add(rs.getString("usn"));
        }
        con.close();
        return res;
    }
           
    }
    
