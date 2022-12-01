<%-- 
    Document   : error
    Created on : 20 nov. 2020, 10:47:12
    Author     : Iikt
--%>

<%@page isErrorPage="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>P&aacute;gina de error :(</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <h1>Houston, we have a problem.</h1>

            <%
                String de = request.getParameter("de");
            %>

            <p>Ocurrió un error en <%=de%></p>
            <br />
            <div class="alert alert-danger" role="alert">
                <p>El error es: <%=exception%></p>
            </div>

        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" ></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" ></script>
    </body>
</html>
