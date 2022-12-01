/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.GraficaDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
public class GraficaDAO {

    private static final String SQL_GRAFICAR = "SELECT categoria.nombrecategoria as categoria, count(*) as productos FROM producto "
            + "INNER JOIN categoria ON producto.idcategoria = categoria.idcategoria group by categoria.idcategoria";
    
    private Connection con;
    
    //  public Connection getConnection() {
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
    
    /*public void obtenerConexion(){    //COnexion con postgres a través de heroku
        String user = "qybwlbsaxguimj";   //Usuario de la BD
        String pass = "5ea914dcec5f3ba065cf70627384327819e42ad041d5e15dcbd42eb658858ffd";   //Contra del usuario
        String url = "jdbc:postgresql://ec2-52-44-139-108.compute-1.amazonaws.com:5432/d6ame4eub7sihh?sslmode=require"; //Url de la base de datos
        try {
            con = DriverManager.getConnection(url, user, pass);
            System.out.println("Logre la conexion");
        } catch (SQLException ex) {
            Logger.getLogger(CategoriaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    public List grafica() throws SQLException {
        this.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List lista = new ArrayList();
        try {
            ps = con.prepareStatement(SQL_GRAFICAR);
            rs = ps.executeQuery();
            while (rs.next()) {
                GraficaDTO dto = new GraficaDTO();
                dto.setNombre(rs.getString("categoria"));
                dto.setCantidad(rs.getInt("productos"));
                lista.add(dto);
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return lista;
    }

}
