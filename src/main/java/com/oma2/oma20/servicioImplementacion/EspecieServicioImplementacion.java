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
    public Especie obtenerPorCategoria(String categoria) {
        return repositorio.findByCategoria(categoria);
    }

    @Override
    public void eliminar(long id) {
        repositorio.deleteById(id);
    }
}
