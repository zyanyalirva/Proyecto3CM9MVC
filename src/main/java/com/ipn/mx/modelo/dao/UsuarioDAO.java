/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.UsuarioDTO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Iikt
 */
public class UsuarioDAO {

    private static final String SQL_INSERT = "{ call spInsertarUsuario(?, ?, ?, ?, ?, ?, ?) }";
    private static final String SQL_UPDATE = "{ call spActualizarUsuario(?, ?, ?, ?, ?, ?, ?) }";
    private static final String SQL_DELETE = "{ call spBorrarUsuario(?) }";
    private static final String SQL_SELECT = "{ call spVerUsuario(?) }";
    private static final String SQL_SELECT_ALL = "{ call spMostrarUsuarios() }";
    private static final String SQL_VALIDATE = "{ call spValidarUsuario(?, ?) }";

    private Connection con;

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

    public void create(UsuarioDTO dto) throws SQLException {
        this.getConnection();
        CallableStatement cs = null;
        try {
            cs = con.prepareCall(SQL_INSERT);
            cs.setString(1, dto.getEntidad().getNombre());
            cs.setString(2, dto.getEntidad().getPaterno());
            cs.setString(3, dto.getEntidad().getMaterno());
            cs.setString(4, dto.getEntidad().getEmail());
            cs.setString(5, dto.getEntidad().getNombreUsuario());
            cs.setString(6, dto.getEntidad().getClaveUsuario());
            cs.setString(7, dto.getEntidad().getTipoUsuario());
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

    public void update(UsuarioDTO dto) throws SQLException {
        this.getConnection();
        CallableStatement cs = null;
        try {
            cs = con.prepareCall(SQL_UPDATE);
            cs.setInt(1, dto.getEntidad().getIdUsuario());
            cs.setString(2, dto.getEntidad().getNombre());
            cs.setString(3, dto.getEntidad().getPaterno());
            cs.setString(4, dto.getEntidad().getMaterno());
            cs.setString(5, dto.getEntidad().getEmail());
            cs.setString(6, dto.getEntidad().getNombreUsuario());
            cs.setString(7, dto.getEntidad().getTipoUsuario());
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

    public void delete(UsuarioDTO dto) throws SQLException {
        this.getConnection();
        CallableStatement cs = null;
        try {
            cs = con.prepareCall(SQL_DELETE);
            cs.setInt(1, dto.getEntidad().getIdUsuario());
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

    public UsuarioDTO read(UsuarioDTO dto) throws SQLException {
        this.getConnection();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = con.prepareCall(SQL_SELECT);
            cs.setInt(1, dto.getEntidad().getIdUsuario());
            rs = cs.executeQuery();
            List results = getResults(rs);

            if (results.size() > 0) {
                return (UsuarioDTO) results.get(0);
            }
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

    public UsuarioDTO validate(UsuarioDTO dto) throws SQLException {
        this.getConnection();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = con.prepareCall(SQL_VALIDATE);
            cs.setString(1, dto.getEntidad().getNombreUsuario());
            cs.setString(2, dto.getEntidad().getClaveUsuario());
            rs = cs.executeQuery();
            List results = getResults(rs);

            if (results.size() == 1) {
                return (UsuarioDTO) results.get(0);
            }
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
            UsuarioDTO dto = new UsuarioDTO();
            dto.getEntidad().setIdUsuario(rs.getInt("idUsuario"));
            dto.getEntidad().setNombre(rs.getString("nombre"));
            dto.getEntidad().setPaterno(rs.getString("paterno"));
            dto.getEntidad().setMaterno(rs.getString("materno"));
            dto.getEntidad().setNombreUsuario(rs.getString("nombreUsuario"));
            dto.getEntidad().setEmail(rs.getString("email"));
            dto.getEntidad().setTipoUsuario(rs.getString("tipoUsuario"));
            results.add(dto);
        }
        return results;
    }
}
