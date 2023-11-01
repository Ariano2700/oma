package com.oma2.oma20.repositorios;

import com.oma2.oma20.modelos.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrabajadorRepositorio extends JpaRepository<Trabajador, Long> {
    Trabajador findByDni(int dni);
    List<Trabajador> findByIdRol (int idRol);
    Trabajador findByEmail (String email);

}
