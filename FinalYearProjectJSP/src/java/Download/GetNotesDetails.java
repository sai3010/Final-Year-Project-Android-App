/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Download;

import static Upload.GetInfoFrom.fsusn;
import static Upload.GetInfoFrom.sem;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author saipr
 */
public class GetNotesDetails extends HttpServlet {

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
            
           String dir= context.getRealPath("/").replace("\\build", ""); 
           String usn=request.getParameter("usn");
           String sem=request.getParameter("sem");
            System.out.println("sem = " + sem);
            System.out.println("usn = " + usn);
            String branch="",sfsem="";
            String tempDir=dir+"Notes"+"\\";
            if(usn.contains("CS")){
                branch="cse";
            }
            else if(usn.contains("EC")){
                branch="ece";
            }
            else if(usn.contains("EE")){
                branch="eee";
            }
            else if(usn.contains("IS")){
                branch="ise";
            }
            else if(usn.contains("ME")){
                branch="mech";
            }
            else if(usn.contains("CV")){
                branch="civil";
            }
            else if(usn.contains("EI")){
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
            List<String> results = new ArrayList<String>();
            //List<String> url = new ArrayList<String>();
            String fileDir=tempDir+branch+"\\"+sfsem+"\\";
            System.out.println("fileDir := " + fileDir);
            File directory=new File(fileDir);
            File[] flist=directory.listFiles();
            
            for (File file : flist){
            if (file.isFile()){
                //System.out.println(file.getName());
                results.add(file.getName().toString());
                //url.add(fileDir+file.getName());
            }
                //System.out.println("url = " + url);
                System.out.println("file names="+results);
            }
            String fname=results.get(0);
            for(int i=1;i<results.size();i++)
            {
                fname=fname+","+results.get(i);
            }
//            System.out.println("lol"+fname[0]+"\n");
            out.print(fname);
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
