<%-- 
    Document   : perfil
    Created on : 13 dic. 2020, 20:39:17
    Author     : Iikt
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("idUsuario") == null)
        response.sendRedirect("login.jsp");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <nav class="navbar navbar-dark bg-dark">
                    <a class="navbar-brand" href="#">
                        <img src="assets/img/bootstrap-solid.svg" width="30" height="30" class="d-inline-block align-top" alt="" >
                        TRAAF Store
                    </a>
                </nav>

                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Categor&iacute;as
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="CategoriaServlet?accion=listaCategorias">Lista de categor&iacute;as</a>
                                <a class="dropdown-item" href="CategoriaServlet?accion=nuevo">Registrar categor&iacute;a</a>
                                <a class="dropdown-item" href="CategoriaServlet?accion=generarReporte">Generar reporte</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Productos
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="ProductosServlet?accion=listaProductos">Lista de productos</a>
                                <a class="dropdown-item" href="ProductosServlet?accion=nuevo">Registrar producto</a>
                            </div>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Productos
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <a class="dropdown-item" href="UsuariosServlet?accion=listaUsuarios">Lista de usuarios</a>
                                <a class="dropdown-item" href="UsuariosServlet?accion=nuevo">Registrar usuario</a>
                            </div>
                        </li>
                    </ul>
                    <div class="form-inline my-2 my-lg-0">
                        <a class="navbar-brand" href="UsuariosServlet?accion=verPerfil"><c:out value="${sessionScope.nombreUsuario}"/></a>
                        <a class="btn btn-danger" href="UsuariosServlet?accion=logout">Cerrar sesión</a>
                    </div>
                </div>
            </nav>

            <div class="card border-dark mb-3">
                <div class="card-header">
                    <h3>Datos de usuario</h3>
                </div>
                <div class="card-body text-dark">
                    <ul class="list-group">
                        <li class="list-group-item"><b>Nombre: </b> <c:out value="${usr.entidad.nombre}"/></li>
                        <li class="list-group-item"><b>Apellido paterno: </b> <c:out value="${usr.entidad.paterno}"/></li>
                        <li class="list-group-item"><b>Apellido materno </b> <c:out value="${usr.entidad.materno}"/></li>
                        <li class="list-group-item"><b>Correo electr&oacute;nico: </b> <c:out value="${usr.entidad.email}"/></li>
                        <li class="list-group-item"><b>Tipo de usuario: </b> <c:out value="${usr.entidad.tipoUsuario}"/></li>
                    </ul>
                </div>
            </div>

        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" ></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" ></script>
    </body>
