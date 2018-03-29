/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Admin;

import db.DBQuery;
import java.awt.Component;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.exit;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.StringBinding;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.*;


/**
 *
 * @author DELL
 */
public class Placementadmin extends HttpServlet {

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
        DBQuery db= new DBQuery();
        String [] branch=new String[9];
        try (PrintWriter out = response.getWriter()) {
            JFrame f;  
            int count=0;
           String  cname =request.getParameter("cname");   
           String cse=request.getParameter("cse");
            System.out.println("cse = " + cse);
             String ise=request.getParameter("ise");
               String ece=request.getParameter("ece");
                 String eee=request.getParameter("eee");
                   String civ=request.getParameter("civ");
                     String mech=request.getParameter("mech");
                       String eie=request.getParameter("eie");
              String  ctier =request.getParameter("ctier");
              String  ccode=request.getParameter("ccode");
              if(cse!=null)
              {
                  count+=1;
                  branch[count]=cse;
              }
               if(ise!=null)
              {
                  count+=1;
                  branch[count]=ise;
              }
               if(ece!=null)
              {
                  count+=1;
                  branch[count]=ece;
              }
               if(eee!=null)
              {
                  count+=1;
                  branch[count]=eee;
              }
               if(mech!=null)
              {
                  count+=1;
                  branch[count]=mech;
              }
               if(eee!=null)
              {
                  count+=1;
                  branch[count]=eee;
              }
               if(civ!=null)
              {
                  count+=1;
                  branch[count]=civ;
              }
             
              if(count==0)
              {
//                //out.print ("fail");
                   f=new JFrame();  
                 JOptionPane.showMessageDialog(f,"FAILED");
//                  //System.out.println("nononononoonnoon");
                  return ;
                 
              }
              else
              {
                for(int i =1;i<=count;i++)
                {
                System.out.println("branch = " + branch[i]);
                int j = db.add_placements_data(ccode,branch[i]);
                }
              }
              System.out.println("count = " + count);
              System.out.println("branch = " + branch[0]+branch[1]);
              System.out.println("ccode = " + ccode);
              System.out.println("cname = " + cname);
              int i=db.add_placement_data(ccode,cname,ctier);
            
            
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
            Logger.getLogger(Placementadmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Placementadmin.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Placementadmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Placementadmin.class.getName()).log(Level.SEVERE, null, ex);
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
