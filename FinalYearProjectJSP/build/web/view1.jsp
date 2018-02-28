<%-- 
    Document   : view1
    Created on : Feb 27, 2018, 7:15:37 PM
    Author     : spoorthi s
--%>

<%@page import="db.DBQuery"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Bree+Serif" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/home.css"/>
    <title>ADMIN</title>
  </head>
  
    <body>
        <% 
            String val[]= new String[9];
            
            DBQuery db= new DBQuery();
            String usn= request.getParameter("usn");
            usn.toUpperCase();
            String ch= usn.charAt(0)+"";
                    if(ch.matches("[0-9]"))
                    {
                        val=  db.view_stud_data(usn);
                       
                    }
          String value= val[0]+" "+val[1];
         value=value.toUpperCase();
          if(val[0] == null)
                {
                    RequestDispatcher rd= request.getRequestDispatcher("home.jsp");
                    rd.forward(request, response);   
                }
                else
                { 
        %>
        
    <h2 class="head" align="center"><%=value%></h2>
        
        
        <div class="container">
        <table class="table table-bordered">
  <thead>
   
                 <tr><th scope="row">Firstname</th>
                     <td><%=val[0]%></td>  
                 </tr>
                  
                 <tr><th scope="row">Lastname</th>
                      <td><%=val[1]%></td>  
                 </tr>                        
                 <tr><th scope="row">USN</th>
                      <td><%=val[2]%></td>  
                 </tr>
                <tr><th scope="row">Sem</th>
                    <td><%=val[3]%></td>  
                </tr>
                <tr><th scope="row">Email</th>
                     <td><%=val[4]%></td>  
                </tr>
                <tr>  <th scope="row">Address</th>
                    <td><%=val[5]%></td>  
                </tr>
                <tr><th scope="row">Phone</th>
                    <td><%=val[6]%></td>  
                </tr>
                <tr><th scope="row">Gender</th>
                    <td><%=val[7]%></td>  
                </tr>
                <tr><th scope="row">Branch</th>
                    <td><%=val[8]%></td>  
                </tr>
    
  </thead>
  <tbody>
   
        </table>  
                <%
                    }
                    %>
        </div>
                
   

 </body>
</html>
