/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador.web;

import com.ipn.mx.modelo.dto.UsuarioDTO;
import com.ipn.mx.modelo.dao.UsuarioDAO;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Iikt
 */
public class UsuariosServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");

        if (accion.equals("login")) {
            iniciarSesion(request, response);
        } else if (accion.equals("logout")) {
            cerrarSesion(request, response);
        } else if (accion.equals("verPerfil")) {
            verPerfil(request, response);
        } else if (accion.equals("nuevo")) {
            registrarUsuario(request, response);
        } else if (accion.equals("guardar")) {
            almacenarUsuario(request, response);
        } else if (accion.equals("listaUsuarios")) {
            listaUsuarios(request, response);
        }
    }

    private void iniciarSesion(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDTO dto = new UsuarioDTO();
        UsuarioDAO dao = new UsuarioDAO();

        dto.getEntidad().setNombreUsuario(request.getParameter("username"));
        dto.getEntidad().setClaveUsuario(request.getParameter("pass"));

        RequestDispatcher rd = request.getRequestDispatcher("perfil.jsp");
        try {
            dto = dao.validate(dto);
            if (dto != null) {
                HttpSession session = request.getSession();
                session.setAttribute("idUsuario", dto.getEntidad().getIdUsuario());
                session.setAttribute("nombreUsuario", dto.getEntidad().getNombreUsuario());
                try {
                    request.setAttribute("usr", dto);
                    rd.forward(request, response);
                } catch (IOException | ServletException ex) {
                    Logger.getLogger(UsuariosServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void registrarUsuario(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher rd = request.getRequestDispatcher("usuarioForm.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void cerrarSesion(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        try {
            response.sendRedirect("login.jsp");
        } catch (IOException ex) {
            Logger.getLogger(UsuariosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void verPerfil(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDTO dto = new UsuarioDTO();
        UsuarioDAO dao = new UsuarioDAO();
        if (request.getParameter("idUsuario") != null && request.getParameter("idUsuario") != "") {
            dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("idUsuario")));
        } else {
            HttpSession session = request.getSession();
            dto.getEntidad().setIdUsuario((Integer) session.getAttribute("idUsuario"));
        }
        RequestDispatcher rd = request.getRequestDispatcher("perfil.jsp");
        try {
            dto = dao.read(dto);
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        request.setAttribute("usr", dto);

        try {
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(UsuariosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void almacenarUsuario(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();

        dto.getEntidad().setNombre(request.getParameter("txtNombre"));
        dto.getEntidad().setPaterno(request.getParameter("txtPaterno"));
        dto.getEntidad().setMaterno(request.getParameter("txtMaterno"));
        dto.getEntidad().setEmail(request.getParameter("txtEmail"));
        dto.getEntidad().setNombreUsuario(request.getParameter("txtUsername"));

        try {
            if (request.getParameter("id") != null && request.getParameter("id") != "") {
                dto.getEntidad().setIdUsuario(Integer.parseInt(request.getParameter("id")));
                dao.update(dto);
            } else {
                dto.getEntidad().setClaveUsuario(request.getParameter("txtPassword"));
                dto.getEntidad().setTipoUsuario("Cliente");
                dao.create(dto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuariosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            response.sendRedirect("UsuariosServlet?accion=listaUsuarios");
        } catch (IOException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void listaUsuarios(HttpServletRequest request, HttpServletResponse response) {
        UsuarioDAO dao = new UsuarioDAO();
        try {
            List lista = dao.readAll();
            request.setAttribute("listaUsuarios", lista);
            RequestDispatcher vista = request.getRequestDispatcher("listUsers.jsp");
            vista.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
