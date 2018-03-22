/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

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
 * @author spoorthi s
 */
public class Functionality extends HttpServlet {

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
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
         DBQuery db= new DBQuery();
        try (PrintWriter out = response.getWriter()) {
            
//            add faculty

                String addfacfirstname=request.getParameter("addfacfirstname");
                String addfaclastname=request.getParameter("addfaclastname");
                String addfacusn=request.getParameter("addfacusn");
                String addfacdob=request.getParameter("addfacdob");
                String branch=request.getParameter("addfacbranch");
                String addfacgender=request.getParameter("addfacgender");
                String addfacemail=request.getParameter("addfacemail");
                String addfacpassword=request.getParameter("addfacpassword");
                String addfacadd1=request.getParameter("addfacadd1");
                String addfacphone=request.getParameter("addfacphone");
                String addfacqual=request.getParameter("addfacqual");
                
                 addfacusn=addfacusn.toUpperCase();
               
                
                
               System.out.println("addfacmale = " + addfacdob);
          
               
               int i =db.add_fac_data(addfacfirstname,addfaclastname,addfacusn,addfacqual,addfacemail,addfacadd1,addfacpassword,addfacphone,addfacdob,addfacgender,branch);
               //System.out.println("i = " + i);
            
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
            Logger.getLogger(Functionality.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Functionality.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Functionality.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Functionality.class.getName()).log(Level.SEVERE, null, ex);
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
