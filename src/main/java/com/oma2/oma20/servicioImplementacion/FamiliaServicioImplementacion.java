package com.oma2.oma20.servicioImplementacion;

import com.oma2.oma20.modelos.Familia;
import com.oma2.oma20.repositorios.FamiliaRepositorio;
import com.oma2.oma20.servicios.FamiliaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FamiliaServicioImplementacion implements FamiliaServicio {
    @Autowired
    FamiliaRepositorio repositorio;

    @Override
    public List<Familia> obtenerTodo() {
        return repositorio.findAll();
    }

    @Override
    public Familia guardar(Familia familia) {
        return repositorio.save(familia);
    }

    @Override
    public Familia obtenerPorId(long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public Familia obtenerPorNombre(String nombre) {
        return repositorio.findByNombre(nombre);
    }

    @Override
    public void eliminar(long id) {
        repositorio.deleteById(id);
    }
}
