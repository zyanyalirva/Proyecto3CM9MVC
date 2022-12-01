/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.CategoriaDTO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
// import javax.naming.Context;
// import javax.naming.InitialContext;
// import javax.naming.NamingException;
// import javax.sql.DataSource;

/**
 *
 * @author Iikt
 */
public class CategoriaDAO {

    private static final String SQL_INSERT = "{ call spInsertarCategoria(?, ?) }";
    private static final String SQL_UPDATE = "{ call spActualizarCategoria(?, ?, ?) }";
    private static final String SQL_DELETE = "{ call spBorrarCategoria(?) }";
    private static final String SQL_SELECT = "{ call spVerCategoria(?) }";
    private static final String SQL_SELECT_ALL = "{ call spMostrarCategorias() }";


    private Connection con;

    // public Connection getConnection() {
    // Context ic;
    // Context ec;
    // String resourceDataSource = "jdbc/3cm9";
    // try {
    // ic = new InitialContext();
    // ec = (Context) ic.lookup("java:comp/env");
    // DataSource ds = (DataSource) ec.lookup(resourceDataSource);
    // con = ds.getConnection();
    // } catch (NamingException | SQLException ex) {
    // Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
    // }
    // return con;
    // }

    // public Connection getConnection() {
    //     String user = "postgres";
    //     String pwd = "n0m3l0s3";
    //     String url = "jdbc:postgresql://localhost:5432/db3cm9";
    //     String driver = "org.postgresql.Driver";
    //     try {
    //         Class.forName(driver);
    //         con = DriverManager.getConnection(url, user, pwd);
    //     } catch (ClassNotFoundException | SQLException ex) {
    //         Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
    //     }
    //     return con;
    // }

    public Connection getConnection() {
        String user = "uqynzbofzainfs";
        String pwd = "5cda7ba65ce743927702243a03891967dd3436f1d7286f95ab77decf16ba039a";
        String url = "jdbc:postgresql://ec2-52-44-235-121.compute-1.amazonaws.com:5432/d1kjq1s95jhf07";
        String driver = "org.postgresql.Driver";
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(url, user, pwd);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }

    public void create(CategoriaDTO dto) throws SQLException {
        this.getConnection();
        CallableStatement cs = null;
        try {
            cs = con.prepareCall(SQL_INSERT);
            cs.setString(1, dto.getEntidad().getNombreCategoria());
            cs.setString(2, dto.getEntidad().getDescripcionCategoria());
            cs.executeUpdate();
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void update(CategoriaDTO dto) throws SQLException {
        this.getConnection();
        CallableStatement cs = null;
        try {
            cs = con.prepareCall(SQL_UPDATE);
            cs.setString(1, dto.getEntidad().getNombreCategoria());
            cs.setString(2, dto.getEntidad().getDescripcionCategoria());
            cs.setInt(3, dto.getEntidad().getIdCategoria());
            cs.executeUpdate();
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public void delete(CategoriaDTO dto) throws SQLException {
        this.getConnection();
        CallableStatement cs = null;
        try {
            cs = con.prepareCall(SQL_DELETE);
            cs.setInt(1, dto.getEntidad().getIdCategoria());
            cs.executeUpdate();
        } finally {
            if (cs != null) {
                cs.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public CategoriaDTO read(CategoriaDTO dto) throws SQLException {
        this.getConnection();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = con.prepareCall(SQL_SELECT);
            cs.setInt(1, dto.getEntidad().getIdCategoria());
            rs = cs.executeQuery();
            List results = getResults(rs);

            if (results.size() > 0)
                return (CategoriaDTO) results.get(0);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (cs != null) {
                cs.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    public List readAll() throws SQLException {
        this.getConnection();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = con.prepareCall(SQL_SELECT_ALL);
            rs = cs.executeQuery();
            List results = getResults(rs);

            if (results.size() > 0)
                return results;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (cs != null) {
                cs.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

    private List getResults(ResultSet rs) throws SQLException {
        List results = new ArrayList();
        while (rs.next()) {
            CategoriaDTO dto = new CategoriaDTO();
            dto.getEntidad().setIdCategoria(rs.getInt("idCategoria"));
            dto.getEntidad().setNombreCategoria(rs.getString("nombreCategoria"));
            dto.getEntidad().setDescripcionCategoria(rs.getString("descripcionCategoria"));
            results.add(dto);
        }
        return results;
    }

}
