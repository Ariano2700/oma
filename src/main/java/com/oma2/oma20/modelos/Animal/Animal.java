package com.oma2.oma20.modelos.Animal;

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
public class Animal implements Serializable {
    private static final long serialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAnimal;
    private String nombreAnimal;
    private int edad;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;
    private String tipo;
    private String estado;
    private int idEspecie;
    private int idRecinto;
}
