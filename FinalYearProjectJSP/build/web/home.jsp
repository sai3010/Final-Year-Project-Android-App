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
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="css/home.css"/>
    <title>ADMIN</title>
  </head>
  <body>
  
 <!--navbar -->
 
  <div id="mySidenav" class="sidenav">
                      <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
                      <a href="#mp">Functionality</a>
                      <a href="#placement">Placements</a>
                      <a href="#">Clients</a>
                      <a href="#">Contact</a>
                    </div>
     
     <span class="navtrigger" style="font-size:30px;cursor:pointer; color:black ;" onclick="openNav()">&#9776</span>
     
 <!--landing page-->    
 <section class="landing">
   <div class="inner">
      
       <img src="img/landing.png" align="left">
      
     <div class="content" align="left">
         <h1 class="head">Hello,Admin!</h1>
         <h2 class="head1">let's get going ... </h2>
                </div>
         </div>
 </section>
 
 <!--Main Page-->
 <section id="mp">
 <div class="container">
     <h1 class="head" align="center">FUNCTIONALITY</h1>
       
  <div class="row">
    <div class="col md-6 sm-6 lg-6">
         <div class="float-left">
             <h2 class="head2" align="center">Student</h2>
             <div  id="studcontainer"> 
             <img src="img/student.png" class="image" >
                <div class="middle">
                   <div class="btn-group">
                       <div class="row">

                           
<!--button-->
<button type="button" class="button"  data-toggle="modal" data-target="#addstudent">
  ADD
</button>

                             
                               
                               
<button class="button" data-toggle="modal" data-target="#updatestudent"">UPDATE</button>
 
                                    

<button class="button" data-toggle="modal" data-target="#viewstudent">VIEW</button>

                                    


<button class="button" data-toggle="modal" data-target="#deletestudent">DELETE</button>

                         
                    </div>
                 </div>
             </div>
    </div>
    </div>
    </div>
      
      
    <div class="col md-6 sm-6 lg-6">
        <div class ="float-right">
            <h2 class="head2" align="center">Faculty</h2>
           <div  id="faccontainer">  
              <img src="img/fac.png" class="image1" >
               <div class="middle1">
                   <div class="btn-group" align="center">
                       <div class="row">
                      <button type="button" class="button"  data-toggle="modal" data-target="#addfaculty">ADD</button><br>
                      <button button type="button" class="button"  data-toggle="modal" data-target="#updatefaculty">UPDATE</button>
                      <button button type="button" class="button"  data-toggle="modal" data-target="#viewfaculty">VIEW</button>
                      <button button type="button" class="button"  data-toggle="modal" data-target="#deletefaculty">DELETE</button>
                       </div>
                    </div>
                 </div>
                </div>
            </div>
    </div>
      </div>
  </div>
 </section>
 

<!-- Update Student Modal -->
<div class="modal fade" id="updatestudent" tabindex="-1" role="dialog" aria-labelledby="updatestudenttitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="updatestudenttitle">Update Student</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
                 <form  action="./Adminlogin" method="post">
                        <div class="form-row">
                          <div class="col">
                               <label for="firstname"></label>
                            <input type="text" class="form-control" placeholder="First name">
                          </div>
                          <div class="col">
                               <label for="lastname"></label>
                            <input type="text" class="form-control" placeholder="Last name">
                          </div>
                        </div>
                        
                        <div class="form-row">
                            <div class="form-group col-md-8">
                                <label for=""></label>
                                <input type="text" class="form-control" id="usn" placeholder="USN">
                            </div>
                            
                              <div class="form-group col-md-4">
                                        <label for="date"></label>
                                        <input type="date" class="form-control"  placeholder="DOB">
                               </div>
                        </div>
                       
                        <div class="form-row align-items-center">
                                <div class="col-auto my-1">
                                  <select class="custom-select mr-sm-2" id="inlineFormCustomSelect">
                                    <option selected>Branch</option>
                                    <option value="1">CSE</option>
                                    <option value="2">ISE</option>
                                    <option value="3">ECE</option>
                                  </select>
                                </div>
                          
                   <div class="form-check">
                       <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios1" value="option1" checked>
                            <label class="form-check-label" for="exampleRadios1">
                            MALE
                            </label>
                   </div>
<div class="form-check">
  <input class="form-check-input" type="radio" name="exampleRadios" id="exampleRadios2" value="option2">
  <label class="form-check-label" for="exampleRadios2">
    FEMALE
  </label>
</div>
                    
                         </div>     
                    
 <div class="form-row">
    <div class="form-group col-md-6">
      <label for="email"></label>
      <input type="email" class="form-control" id="inputEmail4" placeholder="Email">
    </div>
    <div class="form-group col-md-6">
      <label for="password"></label>
      <input type="password" class="form-control" id="inputPassword4" placeholder="Password">
    </div>
  </div>
  
  
    <div class="form-row">
    <div class="form-group col-md-8">
      
      <input type="phone" class="form-control" id="phone" placeholder="Phone">
    </div>
    <div class="form-group col-md-3">
      
        <input type="number" min="1" max="8" class="form-control" id="sem" placeholder="Sem">
    </div>
  </div>
                    
                    
  <div class="form-group">
        <input type="text" class="form-control" id="inputAddress" placeholder="Addess Line 1">
  </div>
  
  
  <div class="form-group">
         <input type="text" class="form-control" id="inputAddress2" placeholder="Address Line 2">
  </div>
                
               </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save Changes</button>
      </div>
    </div>
  </div>
</div> 

<!--View Student Modal -->
<div class="modal fade" id="viewstudent" tabindex="-1" role="dialog" aria-labelledby="viewstudenttitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="viewstudenttitle">View Student</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
          <div class="col md-6  ">
         <input type="text" class="form-control" id="usn" placeholder="Enter USN">
          </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>  
 
 
 <!-- Delete Student Modal -->
<div class="modal fade" id="deletestudent" tabindex="-1" role="dialog" aria-labelledby="deletestudenttitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="deletestudenttitle">Delete Student</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
          <div class="col md-6  ">
         <input type="text" class="form-control " id="usn" placeholder=" Enter USN">
         </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Submit</button>
      </div>
    </div>
  </div>
</div>
 
 
 
 
 <!-- Add Student Modal -->
<div class="modal fade" id="addstudent" tabindex="-1" role="dialog" aria-labelledby="addstudenttitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" align ="center" id="addstudenttitle" >Add Student</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
                <form>
                        <div class="form-row">
                          <div class="col">
                               <label for="firstname"></label>
                            <input type="text" class="form-control" placeholder="First name">
                          </div>
                          <div class="col">
                               <label for="lastname"></label>
                            <input type="text" class="form-control" placeholder="Last name">
                          </div>
                        </div>
                        
                        <div class="form-row">
                            <div class="form-group col-md-8">
                                <label for=""></label>
                                <input type="text" class="form-control" id="usn" placeholder="USN">
                            </div>
                            
                              <div class="form-group col-md-4">
                                        <label for="date"></label>
                                        <input type="date" class="form-control"  placeholder="DOB">
                               </div>
                        </div>
                       
                        <div class="form-row align-items-center">
                                <div class="col-auto my-1">
                                  <select class="custom-select mr-sm-2" id="inlineFormCustomSelect">
                                    <option selected>Branch</option>
                                    <option value="1">CSE</option>
                                    <option value="2">ISE</option>
                                    <option value="3">ECE</option>
                                  </select>
                                </div>
                          
                <div class="form-check">
                       <input class="form-check-input" type="radio" name="exampleRadios" id="add_student_male" value="option1" checked>
                            <label class="form-check-label" for="exampleRadios1">
                            MALE
                            </label>
                   </div>
<div class="form-check">
  <input class="form-check-input" type="radio" name="exampleRadios" id="add_student_female" value="option2">
  <label class="form-check-label" for="exampleRadios2">
    FEMALE
  </label>
</div>
                    
                         </div>     
                    
 <div class="form-row">
    <div class="form-group col-md-6">
      <label for="email"></label>
      <input type="email" class="form-control" id="inputEmail4" placeholder="Email">
    </div>
    <div class="form-group col-md-6">
      <label for="password"></label>
      <input type="password" class="form-control" id="inputPassword4" placeholder="Password">
    </div>
  </div>
  
  
    <div class="form-row">
    <div class="form-group col-md-8">
      
      <input type="phone" class="form-control" id="phone" placeholder="Phone">
    </div>
    <div class="form-group col-md-3">
      
        <input type="number" min="1" max="8" class="form-control" id="sem" placeholder="Sem">
    </div>
  </div>
                    
                    
  <div class="form-group">
        <input type="text" class="form-control" id="inputAddress" placeholder="Addess Line 1">
  </div>
  
  
  <div class="form-group">
         <input type="text" class="form-control" id="inputAddress2" placeholder="Address Line 2">
  </div>
                
               </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Submit</button>
      </div>
    </div>
  </div>
</div> 
 

<!--View Faculty Modal -->
<div class="modal fade" id="viewfaculty" tabindex="-1" role="dialog" aria-labelledby="viewfacultytitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="viewfacultytitle">View Faculty</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
          <div class="col md-6  ">
         <input type="text" class="form-control" id="usn" placeholder=" Enter USN">
          </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Submit</button>
      </div>
    </div>
  </div>
</div>  
 
 <!-- Delete Faculty Modal -->
<div class="modal fade" id="deletefaculty" tabindex="-1" role="dialog" aria-labelledby="deletefacultytitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="deletefacultytitle">Delete Faculty</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
          <div class="col md-6  ">
         <input type="text" class="form-control " id="usn" placeholder=" Enter USN">
         </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Submit</button>
      </div>
    </div>
  </div>
</div>
 
 
 <!-- Add Faculty Modal -->
<div class="modal fade" id="addfaculty" tabindex="-1" role="dialog" aria-labelledby="addfacultytitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" align ="center" id="addfacultytitle" >Add Faculty</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
          <form action="./Functionality" method="post">
                        <div class="form-row">
                          <div class="col">
                               <label for="firstname"></label>
                               <input type="text" class="form-control" name="addfacfirstname" placeholder="First name">
                          </div>
                          <div class="col">
                               <label for="lastname"></label>
                               <input type="text" class="form-control" name="addfaclastname" placeholder="Last name">
                          </div>
                        </div>
                        
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for=""></label>
                                <input type="text" class="form-control" id="usn" name="addfacusn" placeholder="USN">
                            </div>
                            
                              <div class="form-group col-md-5">
                                        <label for="date"></label>
                                        <input type="date" class="form-control" name="addfacdob" placeholder="DOB">
                               </div>
                        </div>
                       
                        <div class="form-row align-items-center">
                                <div class="col-auto my-1">
                                    <select class="custom-select mr-sm-2" id="inlineFormCustomSelect" name="addfacbranch">
                                    <option selected>Branch</option>
                                    <option value="CSE">CSE</option>
                                    <option value="ISE">ISE</option>
                                    <option value="ECE">ECE</option>
                                    <option value="CIV">CIV</option>
                                    <option value="EEE">EEE</option>
                                    <option value="MECH">MECH</option>
                                    <option value="EEE">EEE</option>
                                  </select>
                                </div>
                          
                                         <div class="form-check">
                       <input class="form-check-input" type="radio" name="addfacgender"  id="add_faculty_male" value="MALE" checked>
                            <label class="form-check-label" for="exampleRadios1">
                            MALE
                            </label>
                   </div>
<div class="form-check">
  <input class="form-check-input" type="radio" name="addfacgender" id="add_faculty_female" value="FEMALE">
  <label class="form-check-label" for="exampleRadios2">
    FEMALE
  </label>
</div>
                    
                         </div>     
                    
 <div class="form-row">
    <div class="form-group col-md-6">
      <label for="email"></label>
      <input type="email" class="form-control" id="inputEmail4" name="addfacemail" placeholder="Email">
    </div>
    <div class="form-group col-md-6">
      <label for="password"></label>
      <input type="password" class="form-control" id="inputPassword4" name="addfacpassword" placeholder="Password">
    </div>
  </div>
  
  
    <div class="form-row">
         <div class="form-group col-md-8">
      
        <input type="text"  class="form-control" id="sem" name="addfacqual" placeholder="Qualification">
    </div>
    <div class="form-group col-md-4">
      
      <input type="phone" class="form-control" id="phone" name="addfacphone" placeholder="Phone">
    </div>
   
  </div>
                    
                    
  <div class="form-group">
        <input type="text" class="form-control" id="inputAddress" name="addfacadd1" placeholder="Address">
  </div>
  
  
  
               <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="submit" class="btn btn-primary btn-md" value="Submit">Submit</button>
      </div>
                
 </form>
      </div>
     
    </div>
  </div>
</div> 
 
 
  <!-- Update Faculty Modal -->
<div class="modal fade" id="updatefaculty" tabindex="-1" role="dialog" aria-labelledby="updatefacultytitle" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" align ="center" id="updatefacultytitle" >Update Faculty</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
                <form>
                        <div class="form-row">
                          <div class="col">
                               <label for="firstname"></label>
                            <input type="text" class="form-control" placeholder="First name">
                          </div>
                          <div class="col">
                               <label for="lastname"></label>
                            <input type="text" class="form-control" placeholder="Last name">
                          </div>
                        </div>
                        
                        <div class="form-row">
                            <div class="form-group col-md-8">
                                <label for=""></label>
                                <input type="text" class="form-control" id="usn" placeholder="USN">
                            </div>
                            
                              <div class="form-group col-md-4">
                                        <label for="date"></label>
                                        <input type="date" class="form-control"  placeholder="DOB">
                               </div>
                        </div>
                       
                        <div class="form-row align-items-center">
                                <div class="col-auto my-1">
                                  <select class="custom-select mr-sm-2" id="inlineFormCustomSelect">
                                    <option selected>Branch</option>
                                    <option value="1">CSE</option>
                                    <option value="2">ISE</option>
                                    <option value="3">ECE</option>
                                  </select>
                                </div>
                          
                                            <div class="form-check">
                       <input class="form-check-input" type="radio" name="exampleRadios" id="update_faculty_male" value="option1" checked>
                            <label class="form-check-label" for="exampleRadios1">
                            MALE
                            </label>
                   </div>
<div class="form-check">
  <input class="form-check-input" type="radio" name="exampleRadios" id="update_faculty_female" value="option2">
  <label class="form-check-label" for="exampleRadios2">
    FEMALE
  </label>
</div>
                    
                         </div>     
                    
 <div class="form-row">
    <div class="form-group col-md-6">
      <label for="email"></label>
      <input type="email" class="form-control" id="inputEmail4" placeholder="Email">
    </div>
    <div class="form-group col-md-6">
      <label for="password"></label>
      <input type="password" class="form-control" id="inputPassword4" placeholder="Password">
    </div>
  </div>
  
  
    <div class="form-row">
         <div class="form-group col-md-8">
      
        <input type="text"  class="form-control" id="sem" placeholder="Qualificatin">
    </div>
    <div class="form-group col-md-4">
      
      <input type="phone" class="form-control" id="phone" placeholder="Phone">
    </div>
   
  </div>
                    
                    
  <div class="form-group">
        <input type="text" class="form-control" id="inputAddress" placeholder="Addess Line 1">
  </div>
  
  
  <div class="form-group">
         <input type="text" class="form-control" id="inputAddress2" placeholder="Address Line 2">
  </div>
                
               </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save Changes</button>
      </div>
    </div>
  </div>
</div> 

 <!--Placement-->

 <section id="placement">
    <h1 class="head" align="center">PLACEMENTS</h1>
    <div class="container">      
        <form action="./Placementadmin" method="post">  
            <div class="form-row">
                <div class=" col-md-6 offset-md-3 ">
                    <label for="cname"></label>    
                    <input type="text" class="form-control" id="Cname" name="cname" placeholder="Company Name">
                </div> 
                <div class="col-md-6 offset-md-3 ">
                    <label for="Ccode"></label>
                    <input type="text" class="form-control" id="Ccode" name="ccode" placeholder="Company Code">
                </div> 
                <div class=" col-md-6 offset-md-3 ">
                    <label for="Ctier"></label>
                    <input type="text" class="form-control" id="Ctier" name="ctier" placeholder="Company Tier">
                </div> 
                <div class=" col-md-6 offset-md-3 ">
                    <label for="Cbranch"></label>
                    <input type="text" class="form-control" id="Cbranch" name="cbranch" placeholder="Branches">
                </div> 
               
                <div class="col-md-6 offset-md-3 " style="padding-top: 20px;">                 
                    <button type="submit" class="btn btn-primary btn-lg">Submit</button>
                </div>
                
                <!---image-->
                <div class="img col-md-3 ">
                    <img src="img/placement.png" alt="placement">
                </div>
            </div>
        </form>        
    </div>
 </section>
 
 <!--back to top button-->
 <button onclick="topFunction()" id="myBtn" title="Go to top"><i class="material-icons">keyboard_arrow_up</i></button>
  
  
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <script src="js/home.js"></script>
  </body>
</html>
