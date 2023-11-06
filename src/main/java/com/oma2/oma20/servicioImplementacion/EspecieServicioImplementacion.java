package com.oma2.oma20.servicioImplementacion;

import com.oma2.oma20.modelos.Especie;
import com.oma2.oma20.repositorios.EspecieRepositorio;
import com.oma2.oma20.servicios.EspecieServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EspecieServicioImplementacion implements EspecieServicio {
    @Autowired
    EspecieRepositorio repositorio;

    @Override
    public List<Especie> obtenerTodo() {
        return repositorio.findAll();
    }

    @Override
    public Especie guardar(Especie especie) {
        return repositorio.save(especie);
    }

    @Override
    public Especie obtenerPorId(long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public List<Especie> obtenerPorClase(String clase) {
        return repositorio.findByClase(clase);
    }

    @Override
    public List<Especie> obtenerPorEspecie(String especie) {
        return repositorio.findByEspecie(especie);
    }

    @Override
    public List<Especie> obtenerPorFamilia(String familia) {
        return repositorio.findByFamilia(familia);
    }

    @Override
    public List<Especie> obtenerPorNombreCientifico(String nombreCientifico) {
        return repositorio.findByNombreCientifico(nombreCientifico);
    }

    @Override
    public List<Especie> obtenerPorGenero(String genero) {
        return repositorio.findByGenero(genero);
    }

    @Override
    public List<Especie> obtenerIdCategoriaAmenaza(int idCategoriaAmenaza) {
        return repositorio.findByIdCategoriaAmenaza(idCategoriaAmenaza);
    }

    @Override
    public List<Especie> obtenerPorIdAlimento(int idAlimento) {
        return repositorio.findByIdAlimento(idAlimento);
    }

    @Override
    public void eliminar(long id) {
        repositorio.deleteById(id);
    }
}
