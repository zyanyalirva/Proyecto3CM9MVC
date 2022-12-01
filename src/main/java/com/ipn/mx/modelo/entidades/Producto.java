package com.ipn.mx.modelo.entidades;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Producto implements Serializable{
    private Integer idProducto;
    private String nombreProducto;
    private String descripcionProducto;
    private Double precio;
    private Integer existencia;
}
