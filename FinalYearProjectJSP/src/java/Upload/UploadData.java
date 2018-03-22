/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Upload;

import com.oreilly.servlet.MultipartRequest;
import com.sun.org.apache.xerces.internal.impl.xs.XSDDescription;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author saipr
 */
public class UploadData extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
 
    String usn="image";
    String paramname=null,fname="",file="",filePath="";
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        ServletContext context = getServletContext();
    
           String fileDir = context.getRealPath("/").replace("\\build", "");
            System.out.println("fileDir = " + fileDir);
        
        String image= request.getParameter("img");
        String usn= request.getParameter("usn");
//        String usn= "1RNSCCSE";
            String ch= usn.charAt(0)+""; 
            System.out.println("usn = " + usn);
            System.out.println("Success");
            if(ch.matches("[0-9]"))
            {
                fileDir = fileDir+"Photos"+"\\"+"studprofile_photos"+"\\" ;
                System.out.println("fileDir = " + fileDir);
                System.out.println("stud image uploaded");
            }
            else if(ch.matches("[A-Z]"))
            {
                fileDir = fileDir+"Photos"+"\\"+"facprofile_photos"+"\\" ;
                System.out.println("fac image uploaded");
                System.out.println("fileDir = " + fileDir);
            }
            else
            {
                System.out.println("fail = ");
            }
        byte[] decodedString;
        decodedString= Base64.decodeBase64(image);
        
        File f= new File(fileDir+usn+".png");
            FileOutputStream fout= new FileOutputStream(f);
            fout.write(decodedString);
            fout.close();
            
    
        out.print("Uploaded Successfully");
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
        processRequest(request, response);
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
        processRequest(request, response);
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