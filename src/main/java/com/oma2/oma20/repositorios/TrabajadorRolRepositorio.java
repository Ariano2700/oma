package com.oma2.oma20.repositorios;

import com.oma2.oma20.modelos.TrabajadorRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrabajadorRolRepositorio extends JpaRepository<TrabajadorRol, Long> {
    TrabajadorRol findByNombre(String nombre);
}
