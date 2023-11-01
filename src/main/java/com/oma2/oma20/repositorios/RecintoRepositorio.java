package com.oma2.oma20.repositorios;

import com.oma2.oma20.modelos.Recinto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecintoRepositorio extends JpaRepository<Recinto, Long> {
    Recinto findByNombre(String nombre);
    List<Recinto> findByTipo(String tipo);
}
