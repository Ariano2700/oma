package com.oma2.oma20.servicioImplementacion;

import com.oma2.oma20.modelos.ImagenesAnimal;
import com.oma2.oma20.repositorios.ImagenesAnimalRepositorio;
import com.oma2.oma20.servicios.ImagenesAnimalServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImagenesAnimalServicioImplementacion implements ImagenesAnimalServicio {

    @Autowired
    ImagenesAnimalRepositorio repositorio;

    @Override
    public List<ImagenesAnimal> obtenerTodo() {
        return repositorio.findAll();
    }

    @Override
    public ImagenesAnimal guardar(ImagenesAnimal imagenesAnimal) {
        return repositorio.save(imagenesAnimal);
    }

    @Override
    public ImagenesAnimal obtenerPorId(long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public List<ImagenesAnimal> obtenerPorIdAnimal(int idAnimal) {
        return repositorio.findByIdAnimal(idAnimal);
    }

    @Override
    public void eliminar(long id) {
        repositorio.deleteById(id);
    }
}
