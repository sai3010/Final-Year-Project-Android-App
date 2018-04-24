<%-- 
    Document   : index
    Created on : Feb 16, 2018, 9:59:01 AM
    Author     : saipr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
            <!-- Required meta tags -->
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

      <!-- Bootstrap CSS -->
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
      <link href="https://fonts.googleapis.com/css?family=Bree+Serif" rel="stylesheet">
       <link rel="stylesheet" type="text/css" href="css/index.css"/>
       <link href="https://fonts.googleapis.com/css?family=Rammetto+One" rel="stylesheet"> 
          <title>ADMIN</title>
    </head>
    <body>
        <%
            String msg="";
            if(session.getAttribute("msg")!= null){
                msg= session.getAttribute("msg").toString();
            }
            %>
        <div class="heading">
         <div class="container">
             <form  action="./Adminlogin" method="post" >
                <div class="title"><p align="center" style="color:#004d40 ;font-family:  'Bree Serif', serif;  font-size: 50px; " >Campus Bridge</p></div>
                <img src="img/login.png" class=" image-fluid col-md-6 offset-md-3" alt="no image">
                <div class="row">
                    <input type="text" class="form-control col-md-8 offset-md-2" name="uname" placeholder="Username" id="uname" required="Field Required">          
                </div>
                <br>
                <div class="row">
                    <input type="password" class="form-control col-md-8 offset-md-2" name="pass" placeholder="Password" required="Field Required">
                </div>
                <br>
                <div class="row">
                   <button type="submit" class="btn btn-primary btn-lg btn-block btn-block col-md-8 offset-md-2 " id="button"  value="Submit">Login</button>
                </div> 
                <div class="row">
                    <font><h3><%=msg%></h3></font>
                </div>
            </form>
          </div>
        </div>
         <%
            session.setAttribute("msg", " ");
        %>
         <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="js/index.js"></script>        
                
  </body>
</html>
