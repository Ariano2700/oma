package com.oma2.oma20.servicioImplementacion;

import com.oma2.oma20.modelos.Animal.Animal;
import com.oma2.oma20.modelos.Animal.Sexo;
import com.oma2.oma20.repositorios.AnimalRepositorio;
import com.oma2.oma20.servicios.AnimalServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalServicioImplementacion implements AnimalServicio {

    @Autowired
    AnimalRepositorio repositorio;

    @Override
    public List<Animal> obtenerTodo() {
        return repositorio.findAll();
    }

    @Override
    public Animal guardar(Animal animal) {
        return repositorio.save(animal);
    }

    @Override
    public Animal obtenerPorId(long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public List<Animal> obtenerPorSexo(Sexo sexo) {
        return repositorio.findBySexo(sexo);
    }

    @Override
    public List<Animal> obtenerPorTipo(String tipo) {
        return repositorio.findByTipo(tipo);
    }

    @Override
    public Animal obtenerPorNombre(String nombreAnimal) {
        return repositorio.findByNombreAnimal(nombreAnimal);
    }

    @Override
    public void eliminar(long id) {
        repositorio.deleteById(id);
    }
}
