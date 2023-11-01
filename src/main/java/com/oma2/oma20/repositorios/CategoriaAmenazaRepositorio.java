package com.oma2.oma20.repositorios;

import com.oma2.oma20.modelos.CategoriaAmenaza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaAmenazaRepositorio extends JpaRepository<CategoriaAmenaza, Long> {
}
