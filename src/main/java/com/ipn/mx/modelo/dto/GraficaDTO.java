/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Iikt
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraficaDTO implements Serializable {

    private Integer cantidad;
    private String nombre;

}
