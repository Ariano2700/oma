package com.oma2.oma20.repositorios;

import com.oma2.oma20.modelos.Animal.Animal;
import com.oma2.oma20.modelos.Animal.Sexo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepositorio extends JpaRepository<Animal, Long> {
    List<Animal> findBySexo (Sexo sexo);
    List<Animal> findByTipo(String tipo);
    Animal findByNombreAnimal(String nombreAnimal);

}
