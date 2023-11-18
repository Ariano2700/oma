package com.oma2.oma20.repositorios;

import com.oma2.oma20.modelos.ImagenesAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagenesAnimalRepositorio extends JpaRepository<ImagenesAnimal, Long> {
    List<ImagenesAnimal> findByIdAnimal(int idAnimal);
}
