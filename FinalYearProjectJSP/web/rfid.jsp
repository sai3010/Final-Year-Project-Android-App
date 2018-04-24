<%-- 
    Document   : rfid
    Created on : Mar 29, 2018, 5:13:20 PM
    Author     : saipr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Bree+Serif" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/home.css"/>
    <link rel="stylesheet" type="text/css" href="css/rfid.css"/>
    <title>ADMIN</title>
    </head>
    <body>
         <!--navbar -->
 
  <div id="mySidenav" class="sidenav">
                      <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
                      <a href="home.jsp">Home</a>
                      <a href="index.jsp">Logout</a>
                    </div>
     
     <span class="navtrigger" style="font-size:30px;cursor:pointer; color:black ;" onclick="openNav()">&#9776</span>
     <h1>Attendance Updation</h1>
        <%
            String msg="";
            String usn="";
            String name="";
            if(session.getAttribute("id")!= null){
                usn= session.getAttribute("id").toString();
                
            }
            if(session.getAttribute("name")!=null){
                name=session.getAttribute("name").toString().toUpperCase();
                
            }
            if(session.getAttribute("msg")!= null){
                msg= session.getAttribute("msg").toString();
            }
        %>
       
           <div class="in">
              
        <form method="post" action="./Rfid_Read">
            <div class="col-md-6 offset-md-3">
            <input type="text" class="form-control" name="rfidno" autofocus="autofocus" id="usntxt">
            </div>
        </form>
                <font><h3><%=msg%> <%=name%> <%=usn%></h3></font>
        </div>
 
        <%
            session.setAttribute("id", " ");
            session.setAttribute("msg", " ");
            session.setAttribute("name", " ");
        %>
          <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="js/home.js"></script>
    </body>
</html>
