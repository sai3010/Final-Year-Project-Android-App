/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RegisterAndroid;

import db.DBQuery;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class Login extends HttpServlet {

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
           String usn=request.getParameter("usn");
           String pass=request.getParameter("pass");
           System.out.println(usn);
           System.out.println(pass);
           String[] ar = new String[4];
           String ch= usn.charAt(0)+""; 
           if(ch.matches("[0-9]"))
          {
               ArrayList<String[]> detail=db.get_stud_login_data(usn);
               System.out.println(detail);
               
               for(int i = 0; i<detail.size(); i++){
                   ar = detail.get(i);
               }
               
               if(pass.equals(ar[0]))
           {
               System.out.println("passwords match");
               out.print("sok"+"\t"+ar[1]+"\t"+ar[2]+"\t"+ar[3]);
           }
           else
           {
               System.out.println("Mismatch");
               out.print("notok");
           }
          }
           else if(ch.matches("[A-Z]"))
           {
               String [] password=db.get_fac_login_data(usn);
               if(pass.equals(password[0]))
           {
               System.out.println("passwords match");
               out.print("fok"+"\t"+password[1]+"\t"+password[2]+"\t"+password[3]);
           }
           else
           {
               System.out.println("Mismatch");
               out.print("notok");
           }
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
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
