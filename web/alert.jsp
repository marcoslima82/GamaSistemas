<%-- 
    Document   : alerta
    Created on : 19/06/2018, 21:44:08
    Author     : marco
--%>
<%
    String msg = (String) request.getSession().getAttribute("msg");
    String link = (String) request.getSession().getAttribute("link");

    if (msg == null){
        response.sendRedirect("index.html");
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><%=msg%></h1>
        <a href="<%=link%>">Back</a>
    </body>
</html>
