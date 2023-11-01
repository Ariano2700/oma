package com.oma2.oma20.servicios;

import com.oma2.oma20.modelos.Animal.Animal;
import com.oma2.oma20.modelos.Animal.Sexo;

import java.util.List;

public interface AnimalServicio {
    public List<Animal> obtenerTodo();
    public Animal guardar(Animal animal);
    public Animal obtenerPorId(long id);
    public List<Animal> obtenerPorSexo(Sexo sexo);
    public List<Animal> obtenerPorTipo(String tipo);
    public Animal obtenerPorNombre (String nombreAnimal);

    public void eliminar(long id);
}
