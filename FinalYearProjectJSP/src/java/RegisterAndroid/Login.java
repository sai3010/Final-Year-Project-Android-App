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
        String arr[]= new String[10];
        String a[]=new String[10];
        try (PrintWriter out = response.getWriter()) {            
           String usn=request.getParameter("usn");
           String pass=request.getParameter("pass");
           System.out.println(usn);
           System.out.println(pass);
           
           String ch= usn.charAt(0)+""; 
           if(ch.matches("[0-9]"))
          {
               String[] detail=db.get_stud_login_data(usn);
               arr[0]="sok";
               for(int i=0; i < detail.length; i++)
               {
                   arr[i+1]= detail[i];
               }
               
               if(pass.equals(detail[0]))
               {
                    System.out.println("passwords match");
                    for(String str:arr)
                    {
                        System.out.println("str = " + str);
                    }
                    out.print(arr[0]+"\t"+arr[1]+"\t"+arr[2]+"\t"+arr[3]+"\t"+arr[4]+"\t"+arr[5]+"\t"+arr[6]+"\t"+arr[7]+"\t"+arr[8]+"\t"+arr[9]);
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
               a[0]="fok";
               System.out.println("password = " + password);
               for(int i=0; i < password.length; i++)
               {
                   a[i+1]= password[i];
               }
               if(pass.equals(password[0]))
           {
               System.out.println("passwords match");
               out.print(a[0]+"\t"+a[1]+"\t"+a[2]+"\t"+a[3]+"\t"+a[4]+"\t"+a[5]+"\t"+a[6]+"\t"+a[7]+"\t"+a[8]+"\t"+a[9]);
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
