/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Aggregate;

import db.DBQuery;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdk.nashorn.internal.objects.NativeString;

/**
 *
 * @author DELL PC
 */
public class stud_aggregate extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, ClassNotFoundException {
        
        response.setContentType("text/html;charset=UTF-8");
     
        try (PrintWriter out = response.getWriter())
        {
            DBQuery db=new DBQuery();
            
            System.out.println(" =============================== ");
            String usn1 =request.getParameter("usn");
            
           
            System.out.println("usn1 = " + usn1);
            
            String branch="";
            if(usn1.contains("CS"))
                    {branch="CSE";}
            else if(usn1.contains("EC"))
            {
                branch="ECE";
                
            }
            else if(usn1.contains("ME"))
            {
                branch="MECH";
            }
            else if(usn1.contains("EE")){branch="EEE";}
            else if(usn1.contains("IS")){branch="ISE";}
            else if(usn1.contains("CV")){branch="CIV";}
            else if(usn1.contains("EI")){branch="EIE";}
            
           
           String[] value= db.get_student_aggregate_data(usn1,branch);
           
          
           
             System.out.println("value = " + value);  
             out.print(value[0]+","+value[1]+","+value[2]+","+value[3]+","+value[4]+","+value[5]+","+value[6]+","+value[7]+","+value[8]+","+value[9]+","+value[10]);
            
             
            
           
        }
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(stud_aggregate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(stud_aggregate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(stud_aggregate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(stud_aggregate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
