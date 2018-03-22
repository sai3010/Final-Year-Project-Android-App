/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Marks;

import db.DBQuery;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author spoorthi s
 */
public class Student extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     * 
     */
            
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
               DBQuery db=new DBQuery();
            String usn=request.getParameter("usn");
            String sem=request.getParameter("sem");
            String ia=request.getParameter("ia");
            String branch="null";
            ArrayList<String> scode=new ArrayList<String>();
            ArrayList<String>marks=new ArrayList<>();
            ArrayList<String> IA= new ArrayList<>();
            
            String sub="";
            ArrayList<String> val= new ArrayList<>();
            System.out.println("usn = " + usn);
            String susn=usn.substring(5,7);
             switch(susn)
            {
                case "CS":branch="CSE";
                          break;
                case "EC":branch="ECE";
                          break;
                case "EE":branch="EEE";
                          break;
                case "IS":branch="ISE";
                          break;
                case "ME":branch="MECH";
                          break;
                case "CV":branch="CIV";
                          break;
                case "EI":branch="EIE";
                          break;
            }
             
              System.out.println("usn = " + usn);
               System.out.println("sem = " +sem);
               System.out.println("branch = " + branch);
            String  section = db.get_stud_section(usn);
            System.out.println("section = " + section);
            
            ArrayList<String> subjects= db.get_stud_sub(usn,branch,sem);
           System.out.println("subjects = " + subjects);
            
            int size= subjects.size();
            System.out.println("size = " + size);
            for(int i=0;i<size;i++)
            {
                val.add(subjects.get(i));
                System.out.println("sub = " + subjects.get(i));
                marks=db.get_studmarks(usn,subjects.get(i));
                val.addAll(marks);
                System.out.println("marks = " + marks);
            }
            System.out.println("val = " + val);
            out.print(val);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
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
