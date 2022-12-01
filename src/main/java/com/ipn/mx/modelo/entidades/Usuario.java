/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.entidades;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 *
 * @author Iikt
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    private Integer idUsuario;
    private String nombre;
    private String paterno;
    private String materno;
    private String email;
    private String nombreUsuario;
    private String claveUsuario;
    private String tipoUsuario;

}
