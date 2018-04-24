/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package face_detection;

import static face_detection.Main.path;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.codec.binary.Base64;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

/**
 *
 * @author saipr
 */
public class Headcount extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {            
        ServletContext context = getServletContext();
           String fileDir = context.getRealPath("/").replace("\\build", "");
            System.out.println("fileDir = " + fileDir);
            String img=request.getParameter("img");
            byte[] decodedString;
            decodedString= Base64.decodeBase64(img);
            File f= new File(fileDir+"hello"+".png");
            FileOutputStream fout= new FileOutputStream(f);
            fout.write(decodedString);
            fout.close();
            Main mn= new Main();
            
            //path = mn.browse();
            System.out.println("path = " + path);
        BufferedImage image = ImageIO.read(new File(path));
        byte[] b = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        Mat mat = new Mat(image.getHeight(), image.getWidth(), CvType.CV_8UC3);
         mat.put(0,0, b);
         new showResult(mat, "Original Image");
      mn.init(mat,"C:/Users/saipr/Documents/NetBeansProjects/Final-Year-Project-Android-App/FinalYearProjectJSP/src/java/resources/haarcascades/haarcascade_frontalface_alt.xml");
            System.out.print(mn.getHeadCount());
            out.print(mn.getHeadCount());
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
