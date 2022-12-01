package com.ipn.mx.modelo.dto;

import java.io.Serializable;

import com.ipn.mx.modelo.entidades.Usuario;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioDTO implements Serializable {

    private Usuario entidad;

    public UsuarioDTO() {
        this.entidad = new Usuario();
    }
    
    @Override
    public String toString() {
        return "Id: " + entidad.getIdUsuario()
                + "\nNombre: " + entidad.getNombre()
                + "\nPaterno: " + entidad.getPaterno()
                + "\nMaterno: " + entidad.getMaterno()
                + "\nNombre usuario: " + entidad.getNombreUsuario()
                + "\nClave usuario: " + entidad.getClaveUsuario()
                + "\nTipo usuario: " + entidad.getTipoUsuario();
    }
}
