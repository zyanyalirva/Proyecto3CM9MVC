<%-- 
    Document   : usuarioForm
    Created on : 13 dic. 2020, 22:25:29
    Author     : Iikt
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuario Form</title>
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
            <div>
                <div class="card-header bg-dark">
                    <h3 class="text-white">Datos del usuario</h3>
                </div>
                <div class="card-body">
                    <form action="UsuariosServlet?accion=guardar" method="post" name="frmUsuarioForm" id="frmUsuarioForm">
                        <input type="hidden" name="id" id="id" value="<c:out value="${dto.entidad.idUsuario}"/>"/>
                        <div class="form-group row">
                            <div class="col-sm-6">
                                <label class="col-sm-2 col-form-label">Nombre(s)</label>
                            </div>
                            <div class="col-sm-6">
                                <input type="text" name="txtNombre" id="txtNombre" maxlength="50" required="true" placeholder="Nombre (s)"
                                       class="form-control" value="<c:out value="${dto.entidad.nombre}"/>"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-6">
                                <label class="col-sm-4 col-form-label">Apellido paterno</label>
                            </div>
                            <div class="col-sm-6">
                                <input type="text" name="txtPaterno" id="txtPaterno" maxlength="50" required="true" placeholder="Apellido paterno"
                                       class="form-control" value="<c:out value="${dto.entidad.paterno}"/>"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-6">
                                <label class="col-sm-4 col-form-label">Apellido materno</label>
                            </div>
                            <div class="col-sm-6">
                                <input type="text" name="txtMaterno" id="txtMaterno" maxlength="50" required="true" placeholder="Apellido materno"
                                       class="form-control" value="<c:out value="${dto.entidad.materno}"/>"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-6">
                                <label class="col-sm-4 col-form-label">Correo electr&oacute;nico</label>
                            </div>
                            <div class="col-sm-6">
                                <input type="text" name="txtEmail" id="txtEmail" maxlength="50" required="true" placeholder="Correo electrónico"
                                       class="form-control" value="<c:out value="${dto.entidad.email}"/>"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-6">
                                <label class="col-sm-4 col-form-label">Nombre Usuario</label>
                            </div>
                            <div class="col-sm-6">
                                <input type="text" name="txtUsername" id="txtUsername" maxlength="50" required="true" placeholder="Nombre de usuario"
                                       class="form-control" value="<c:out value="${dto.entidad.nombreUsuario}"/>"/>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-6">
                                <label class="col-sm-4 col-form-label">Contraseña</label>
                            </div>
                            <div class="col-sm-6">
                                <input type="password" name="txtPassword" id="txtPassword" maxlength="50" required="true" placeholder="Contraseña"
                                       class="form-control" value="<c:out value="${dto.entidad.claveUsuario}"/>"/>
                            </div>
                        </div>        
                        <a class="btn btn-secondary" href="UsuariosServlet?accion=listaUsuarios">
                            Regresar
                        </a>
                        <button type="submit" class="btn btn-success">Aceptar</button>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
