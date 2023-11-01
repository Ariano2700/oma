package com.oma2.oma20.servicioImplementacion;

import com.oma2.oma20.modelos.Recinto;
import com.oma2.oma20.repositorios.RecintoRepositorio;
import com.oma2.oma20.servicios.RecintoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecintoServicioImplementacion implements RecintoServicio {
    @Autowired
    RecintoRepositorio repositorio;

    @Override
    public List<Recinto> obtenerTodo() {
        return repositorio.findAll();
    }

    @Override
    public Recinto guardar(Recinto recinto) {
        return repositorio.save(recinto);
    }

    @Override
    public Recinto obtenerPorId(long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public List<Recinto> obtenerPorTipo(String tipo) {
        return repositorio.findByTipo(tipo);
    }

    @Override
    public Recinto obtenerPorNombre(String nombre) {
        return repositorio.findByNombre(nombre);
    }

    @Override
    public void eliminar(long id) {
        repositorio.deleteById(id);
    }
}
