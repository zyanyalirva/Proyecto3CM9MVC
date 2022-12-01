/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador.web;

import com.ipn.mx.modelo.dao.CategoriaDAO;
import com.ipn.mx.modelo.dao.GraficaDAO;
import com.ipn.mx.modelo.dto.CategoriaDTO;
import com.ipn.mx.modelo.dto.GraficaDTO;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author Iikt
 */
public class CategoriaServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");

        if (accion.equals("listaCategorias")) {
            listaCategorias(request, response);
        } else if (accion.equals("nuevo")) {
            agregarCategoria(request, response);
        } else if (accion.equals("eliminar")) {
            eliminarCategoria(request, response);
        } else if (accion.equals("actualizar")) {
            actualizarCategoria(request, response);
        } else if (accion.equals("guardar")) {
            almacenarCategoria(request, response);
        } else if (accion.equals("ver")) {
            mostrarCategoria(request, response);
        } else if (accion.equals("graficar")) {
            graficar(request, response);
        } else if (accion.equals("generarReporte")) {
            verPDF(request, response);
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

    private void listaCategorias(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        try {
            List lista = dao.readAll();
            request.setAttribute("listaCategorias", lista);
            RequestDispatcher vista = request.getRequestDispatcher("listCat.jsp");
            vista.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarCategoria(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher rd = request.getRequestDispatcher("categoriaForm.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarCategoria(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();
        dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));
        try {
            dao.delete(dto);
            listaCategorias(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarCategoria(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();
        dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));
        try {
            dto = dao.read(dto);
            request.setAttribute("dto", dto);

            RequestDispatcher rd = request.getRequestDispatcher("categoriaForm.jsp");

            rd.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void mostrarCategoria(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();
        dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));
        RequestDispatcher rd = request.getRequestDispatcher("ver.jsp");

        try {
            dto = dao.read(dto);
            request.setAttribute("cat", dto);
            rd.forward(request, response);
        } catch (SQLException | ServletException | IOException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void almacenarCategoria(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();

        dto.getEntidad().setNombreCategoria(request.getParameter("txtNombre"));
        dto.getEntidad().setDescripcionCategoria(request.getParameter("txtDescripcion"));

        try {
            if (request.getParameter("id") != null && request.getParameter("id") != "") {
                dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));
                dao.update(dto);
            } else {
                dao.create(dto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            response.sendRedirect("CategoriaServlet?accion=listaCategorias");
        } catch (IOException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void graficar(HttpServletRequest request, HttpServletResponse response) {
        JFreeChart grafica = ChartFactory.createPieChart3D("Productos por categoria",
                getGraficaProductos(), true, true, Locale.getDefault());
        String archivo = getServletConfig().getServletContext().getRealPath("/grafica.png");
        try {
            ChartUtils.saveChartAsPNG(new File(archivo), grafica, 500, 500);
            RequestDispatcher rd = request.getRequestDispatcher("listaCategorias.jsp");
            rd.forward(request, response);
        } catch (IOException | ServletException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private PieDataset getGraficaProductos() {
        DefaultPieDataset pie3d = new DefaultPieDataset();
        GraficaDAO gdao = new GraficaDAO();
        try {
            List datos = gdao.grafica();
            for (int i = 0; i < datos.size(); i++) {
                GraficaDTO dto = (GraficaDTO) datos.get(i);
                pie3d.setValue(dto.getNombre(), dto.getCantidad());
            }

        } catch (SQLException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pie3d;
    }

    private void verPDF(HttpServletRequest request, HttpServletResponse response) {
        ServletOutputStream sos = null;
        try {
            CategoriaDAO dao = new CategoriaDAO();
            sos = response.getOutputStream();
            File reporte = new File(getServletConfig().getServletContext().getRealPath("/assets/reports/ProductosPorCategoria.jasper"));
            byte[] bytes = JasperRunManager.runReportToPdf(reporte.getPath(), null, dao.getConnection());
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            sos.write(bytes, 0, bytes.length);
            sos.flush();
        } catch (IOException | JRException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                sos.close();
            } catch (IOException ex) {
                Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
