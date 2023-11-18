package com.oma2.oma20.servicios;

import com.oma2.oma20.modelos.ImagenesAnimal;

import java.util.List;

public interface ImagenesAnimalServicio {
    public List<ImagenesAnimal> obtenerTodo();
    public ImagenesAnimal guardar(ImagenesAnimal imagenesAnimal);
    public ImagenesAnimal obtenerPorId (long id);
    public List<ImagenesAnimal> obtenerPorIdAnimal(int idAnimal);
    public void eliminar(long id);
}
