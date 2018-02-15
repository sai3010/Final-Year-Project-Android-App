<%-- 
    Document   : index
    Created on : Feb 5, 2018, 4:31:50 PM
    Author     : saipr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=Bree+Serif" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="index.css"/>
    <title>ADMIN</title>
  </head>
  <body>
  
 <!--navbar -->
 
  <div id="mySidenav" class="sidenav">
                      <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
                      <a href="#">About</a>
                      <a href="#">Services</a>
                      <a href="#">Clients</a>
                      <a href="#">Contact</a>
                    </div>
     
     <span class="navtrigger" style="font-size:30px;cursor:pointer" onclick="openNav()">&#9776</span>
     
 <!--landing page-->    
 <section class="landing">
   <div class="inner">
     <div class="content" align="center">
         <h1 class="head">HELLO,SO and SO</h1>
         <h2 class="head1">some text</h2>
                </div>
         </div>
 </section>
 
 <!--Main Page-->
 <div class="container">
     <h1 class="head" align="center">FUNCTIONALITY</h1>
       
  <div class="row">
    <div class="col md-6 sm-6 lg-6">
         <div class="float-left">
             <h2 class="head1" align="center">Student</h2>
             <div  id="studcontainer"> 
             <img src="img/student.png" class="image" >
                <div class="middle">
                   <div class="btn-group">
                      <button class="button">ADD</button>
                      <button class="button">UPDATE</button>
                      <button class="button">VIEW</button>
                      <button class="button">DELETE</button>
                    </div>
                 </div>
             </div>
    </div>
    </div>
 
      
    <div class="col md-6 sm-6 lg-6">
        <div class ="float-right">
            <h2 class="head1" align="center">Faculty</h2>
           <div  id="faccontainer">  
              <img src="img/fac.png" class="image1" >
               <div class="middle1">
                   <div class="btn-group">
                      <button class="button">ADD</button>
                      <button class="button">UPDATE</button>
                      <button class="button">View</button>
                      <button class="button">DELETE</button>
                    </div>
                 </div>
                </div>
            </div>
    </div>
      </div>
  </div>

 
 
 
 
 
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="index.js"></script>
  </body>
</html>
