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

/**
 *
 * @author DELL PC
 */
public class Receive_update extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
               DBQuery db= new DBQuery();
           String usn1=request.getParameter("usn");
           Double ssem1 =Double.parseDouble(request.getParameter("sem1"));
             Double ssem2 =Double.parseDouble(request.getParameter("sem2"));
            Double ssem3 =Double.parseDouble(request.getParameter("sem3"));
            Double ssem4 =Double.parseDouble(request.getParameter("sem4"));
            Double ssem5 =Double.parseDouble(request.getParameter("sem5"));
             Double ssem6 =Double.parseDouble(request.getParameter("sem6"));
             Double ssem7 =Double.parseDouble(request.getParameter("sem7"));
              Double ssem8 =Double.parseDouble(request.getParameter("sem8"));
              Double aggsum=Double.parseDouble(request.getParameter("agg"));
              System.out.println(usn1);
             System.out.println("ssem1 = "+ ssem1);
            System.out.println("ssem2 = "+ ssem2);
            System.out.println("ssem3 = "+ ssem3);
            System.out.println("ssem4 = "+ ssem4);
            System.out.println("ssem5 = "+ ssem5);
            System.out.println("ssem6 = "+ ssem6);
            System.out.println("ssem7 = "+ ssem7);
            System.out.println("ssem8 = "+ ssem8);
            
            int i= db.update_student_sem_marks(ssem1,ssem2,ssem3,ssem4,ssem5,ssem6,ssem7,ssem8,usn1,aggsum);
            if(i==0)
            {
                out.print("Updated Succesfully");
        }
            else
            {
                out.print("Updated Succesfully");
            }
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
            Logger.getLogger(Receive_update.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Receive_update.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Receive_update.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Receive_update.class.getName()).log(Level.SEVERE, null, ex);
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
