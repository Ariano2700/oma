package com.oma2.oma20.repositorios;

import com.oma2.oma20.modelos.Especie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecieRepositorio extends JpaRepository<Especie, Long> {
    Especie findByCategoria(String categoria);
}
