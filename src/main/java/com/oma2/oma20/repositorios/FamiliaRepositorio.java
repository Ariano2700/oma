package com.oma2.oma20.repositorios;

import com.oma2.oma20.modelos.Familia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamiliaRepositorio extends JpaRepository<Familia, Long> {
    Familia findByNombre (String nombre);
}
