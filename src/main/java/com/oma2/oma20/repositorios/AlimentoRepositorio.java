package com.oma2.oma20.repositorios;

import com.oma2.oma20.modelos.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlimentoRepositorio extends JpaRepository<Alimento, Long> {
    Alimento findByMarca(String marca);
}
