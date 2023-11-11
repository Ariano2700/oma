package com.oma2.oma20.modelos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Trabajador implements Serializable {

    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTrabajador;
    private String nombre;
    private String apellido;
    private int dni;
    private int telefono;
    private String direccion;
    private String biografia;
    private String email;
    private String password;
    private String username;
    @Lob
    private byte[] fotoPerfil;
    @Lob
    private  byte[] fotoPortada;
    private int idRol;
}
