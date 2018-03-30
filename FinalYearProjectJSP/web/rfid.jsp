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
        <title>RFID</title>
    </head>
    <body>
        <%
            String msg="";
            String usn="";
            if(session.getAttribute("id")!= null){
                usn= session.getAttribute("id").toString();
            }
            if(session.getAttribute("msg")!= null){
                msg= session.getAttribute("msg").toString();
            }
        %>
        <font color="Blue"><h3><%=msg%> <%=usn%></h3></font>
        <form method="post" action="./Rfid_Read">
            <input type="text" name="rfidno" autofocus="autofocus" id="usntxt">
        </form>
        
        <%
            session.setAttribute("id", " ");
            session.setAttribute("msg", " ");
        %>
    </body>
</html>
