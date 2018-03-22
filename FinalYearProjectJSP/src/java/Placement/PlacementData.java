/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Placement;

import db.DBQuery;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author saipr
 */
public class PlacementData extends HttpServlet {
    
    HashSet<String> bset= new HashSet();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) 
        {
            /* TODO output your page here. You may use following sample code. */
            DBQuery db= new DBQuery();
           
            double aggregate=Double.parseDouble(request.getParameter("aggregate").toString());
            System.out.println("aggregate = " + aggregate);
            String usn=request.getParameter("usn");
            String bcode=usn.substring(5,7);
            String query="";
            System.out.println("bcode = " + bcode);
            
            bset.add("CS");
            bset.add("IS");
            bset.add("EC");
            bset.add("EE");
            bset.add("EI");
            bset.add("CV");
            bset.add("ME");
            
            if(!bset.contains(bcode))
            {
                System.out.println("Branch not defined...");
            }
            else
            {
                System.out.println("Branch available...");
                if(aggregate>=75)
                {
                    System.out.println("inside 75");
                    query="select cname from company_details where ccode= any(select ccode from ctype where cbranch='"+bcode+"')";
                }
                else if(aggregate>=60)
                {
                    query="select cname from company_details where (ctier ='t3' or ctier='t2') and ccode= any(select ccode from ctype where cbranch='"+bcode+"')";
                }
                else if(aggregate>=50)
                {
                    query="select * from company_details where ctier='t3'and ccode= any(select ccode from ctype where cbranch='"+bcode+"')";
                }
            }
            String value="";
            value= db.getCompany_Details(query);
            
//            String splitval[]= value.split("\\:");
            out.print(value);
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
            Logger.getLogger(PlacementData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlacementData.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PlacementData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PlacementData.class.getName()).log(Level.SEVERE, null, ex);
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
