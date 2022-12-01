<%-- 
    Document   : login
    Created on : 13 dic. 2020, 19:13:47
    Author     : Iikt
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="icon" type="image/png" href="assets/img/icons/favicon.ico"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/fontawesome.min.css" />
        <link rel="stylesheet" type="text/css" href="fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/hamburgers/1.1.3/hamburgers.min.css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animsition/4.0.2/css/animsition.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/css/select2.min.css" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-daterangepicker/3.0.5/daterangepicker.css" />
        <link rel="stylesheet" type="text/css" href="assets/css/util.css">
        <link rel="stylesheet" type="text/css" href="assets/css/main.css">
    </head>
    <body>
        <div class="limiter">
            <div class="container-login100">
                <div class="wrap-login100 p-l-55 p-r-55 p-t-65 p-b-50">
                    <form class="login100-form validate-form" action="UsuariosServlet?accion=login" method="post">
                        <span class="login100-form-title p-b-33">
                            Iniciar Sesión
                        </span>

                        <div class="wrap-input100 validate-input" data-validate = "El nombre de usuario es requerido.">
                            <input class="input100" type="text" name="username" placeholder="Nombre de usuario">
                            <span class="focus-input100-1"></span>
                            <span class="focus-input100-2"></span>
                        </div>

                        <div class="wrap-input100 rs1 validate-input" data-validate="La contraseña es requerida.">
                            <input class="input100" type="password" name="pass" placeholder="Contraseña">
                            <span class="focus-input100-1"></span>
                            <span class="focus-input100-2"></span>
                        </div>

                        <div class="container-login100-form-btn m-t-20">
                            <button class="login100-form-btn">
                                Iniciar sesión
                            </button>
                        </div>
                        <!--
                        <div class="text-center p-t-45 p-b-4">
                            <span class="txt1">
                                Olvidó
                            </span>

                            <a href="#" class="txt2 hov1">
                                Usuario / Contraseña?
                            </a>
                        </div>
                        -->
                        <br>
                        <div class="text-center">
                            <span class="txt1">
                                ¿No tienes una cuenta?
                            </span>

                            <a href="UsuariosServlet?accion=nuevo" class="txt2 hov1">
                                Registrarse
                            </a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/animsition/4.0.2/js/animsition.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" ></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.13/js/select2.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-daterangepicker/3.0.5/daterangepicker.min.js" ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/countdown/2.6.0/countdown.min.js" ></script>

        <script src="assets/js/main.js"></script>
    </body>
</html>
