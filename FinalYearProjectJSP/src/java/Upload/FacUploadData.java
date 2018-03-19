/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Upload;

import static Upload.GetInfoFrom.fsusn;
import static Upload.GetInfoFrom.sem;
import com.oreilly.servlet.MultipartRequest;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author saipr
 */
public class FacUploadData extends HttpServlet {

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
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ServletContext context= request.getServletContext();
        try (PrintWriter out = response.getWriter()) {
            String dir= context.getRealPath("/");
            System.out.println("dir = " + dir);
      out.print("Success");
    String tempDir=dir+"Notes";
            System.out.println("tempDir = " + tempDir);
    String fileDir="";
    String usn="image";
    String branch="";
    String sfsem="";
    String paramname=null,fname="",file="",filePath="";
    if(fsusn.contains("CSE")){
                branch="cse";
            }
            else if(fsusn.contains("ECE")){
                branch="ece";
            }
            else if(fsusn.contains("EEE")){
                branch="eee";
            }
            else if(fsusn.contains("ISE")){
                branch="ise";
            }
            else if(fsusn.contains("ME")){
                branch="mech";
            }
            else if(fsusn.contains("CIV")){
                branch="civil";
            }
            else if(fsusn.contains("EIE")){
                branch="eie";
            }
            
            switch(Integer.parseInt(sem))
            {
                case 1:sfsem="sem1";
                        break;
                case 2:sfsem="sem2";
                        break;
                case 3:sfsem="sem3";
                        break;
                case 4:sfsem="sem4";
                        break;
                case 5:sfsem="sem5";
                        break;
                case 6:sfsem="sem6";
                        break;
                case 7:sfsem="sem7";
                        break;
                case 8:sfsem="sem8";
                        break;
            }
            fileDir=tempDir+"\\"+branch+"\\"+sfsem+"\\";
    try
    {
        MultipartRequest multi = new MultipartRequest(request, fileDir,	10 * 1024 * 1024); // 10MB
        System.out.println("fileDir = " + fileDir);
        Enumeration files = multi.getFileNames();	
        while (files.hasMoreElements()) 
	{
            paramname = (String) files.nextElement();
            String fPath="";
//            System.out.println("usn = " + multi.getParameter("usn"));
//            usn= multi.getParameter("usn");
//            System.out.println("usn = " + usn);
            if(paramname != null && paramname.equals("uploaded_file"))
            {
		filePath = multi.getFilesystemName(paramname);
                System.out.println("fPath-bytes = " + paramname);
                fPath = fileDir+filePath;
                System.out.println("::::::::::::-"+filePath);
                System.out.println("::::::::::::="+fPath);
                
//                File f=new File(fPath);
//                Image image = ImageIO.read(f);
//                BufferedImage cpimg=bufferImage(image);
//
//                Graphics g = cpimg.createGraphics();
//                File f1 = new File("C:/Users/saipr/Documents/NetBeansProjects/Final-Year-Project-Android-App/FinalYearProjectJSP/web/profile_photos/renamed photos/"+usn+".png");
//                ImageIO.write(cpimg, "png", f1);

                
            }
            
        }
        
        
    }
    catch(Exception e)
    {
        e.printStackTrace();
    }
        }
    }
    
    public static BufferedImage bufferImage(Image image) {
return bufferImage(image,BufferedImage.TYPE_INT_RGB);
} public static BufferedImage bufferImage(Image image, int type) {
BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
Graphics2D g = bufferedImage.createGraphics();
g.drawImage(image, null, null);
return bufferedImage;
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