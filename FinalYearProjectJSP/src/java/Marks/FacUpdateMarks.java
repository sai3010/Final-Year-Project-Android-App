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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

/**
 *
 * @author saipr
 */
public class FacUpdateMarks extends HttpServlet {

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
            DBQuery db=new DBQuery();
            String val= request.getParameter("upmarks");
            String scode=request.getParameter("scode");
            System.out.println("scode = " + scode);
            String sem=request.getParameter("sem");
            System.out.println("sem = " + sem);
            String ia=request.getParameter("ia");
            System.out.println("ia = " + ia);
            int k=0;
            if(val.length() > 0)
            {
                val= val.substring(1,val.length()-1);
            }
            System.out.println(val);
           String valarr[]= val.split(",");
           for(int i=0; i < valarr.length; i++){
               valarr[i]= valarr[i].trim();
               System.out.println("valarr = " + valarr[i]);
               String arr[]= valarr[i].split("=");
               for(int j=0;j<arr.length;j=j+2)
               {
                   System.out.println(arr[j]+"\t" + arr[j+1]);
                   k=db.update_stud_marks(arr[j],arr[j+1],scode,ia);
               }
           }
           //               
            
//            
//            DBQuery db=new DBQuery();
//            int i=db.update_stud_marks();
//            if(i==0)
//            {
//                out.print("Updated Successfully");
//            }
//            else
//            {
//                out.print("Update Failed");
//            }
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
            Logger.getLogger(FacUpdateMarks.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FacUpdateMarks.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(FacUpdateMarks.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FacUpdateMarks.class.getName()).log(Level.SEVERE, null, ex);
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
