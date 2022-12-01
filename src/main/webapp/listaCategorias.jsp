<%-- 
    Document   : listaCategorias
    Created on : 20 nov. 2020, 11:03:01
    Author     : Iikt
--%>
<%@page import="com.ipn.mx.modelo.dto.CategoriaDTO"%>
<%@page import="com.ipn.mx.modelo.dao.CategoriaDAO"%>
<%@page import="java.util.List"%>

<%@page errorPage="error.jsp?de=listaCategorias.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lista de categor&iacute;as</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <table class="table table-bordered table-hover table-dark">
                <thead>
                    <tr>
                        <th scope="col">Clave categor&iacute;a</th>
                        <th scope="col">Nombre categor&iacute;a</th>
                        <th scope="col">Descripci&oacute;n categor&iacute;a</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        CategoriaDAO dao = new CategoriaDAO();
                        List lista = dao.readAll();
                        if (lista != null) {
                            for (int i = 0; i < lista.size(); i++) {
                                CategoriaDTO dto = (CategoriaDTO) lista.get(i);
                    %>
                    <tr>
                        <th scope="row"><%=dto.getEntidad().getIdCategoria()%></th>
                        <td><%=dto.getEntidad().getNombreCategoria()%></td>
                        <td><%=dto.getEntidad().getDescripcionCategoria()%></td>
                        <td><a href="eliminarCategoria.jsp?id=<%=dto.getEntidad().getIdCategoria()%>">Eliminar</a> </td>
                    </tr>
                    <%
                            }
                        }
                    %>
                </tbody>
            </table>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" ></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" ></script>
    </body>
</html>
