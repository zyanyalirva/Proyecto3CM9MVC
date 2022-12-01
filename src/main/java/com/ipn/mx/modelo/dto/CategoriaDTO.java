package com.ipn.mx.modelo.dto;

import com.ipn.mx.modelo.entidades.Categoria;
import java.io.Serializable;

public class CategoriaDTO implements Serializable{
    private Categoria entidad;

    public CategoriaDTO() {
        this.entidad = new Categoria();
    }

    public CategoriaDTO(Categoria entidad){
        this.entidad = entidad;
    }

    public Categoria getEntidad() {
        return entidad;
    }

    public void setEntidad(Categoria entidad) {
        this.entidad = entidad;
    }

    @Override
    public String toString() {
        return "Id: " + entidad.getIdCategoria()
                + "\nNombre: " + entidad.getNombreCategoria()
                + "\nDesc: "+ entidad.getDescripcionCategoria();
    }

    public static void main(String[] args) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.getEntidad().setIdCategoria(1);
        dto.getEntidad().setNombreCategoria("Nombre 2");
        dto.getEntidad().setDescripcionCategoria("Desc 1");
        
        System.out.println(dto);
    }
    
}
