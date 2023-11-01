package com.oma2.oma20.servicioImplementacion;

import com.oma2.oma20.modelos.TrabajadorRol;
import com.oma2.oma20.repositorios.TrabajadorRolRepositorio;
import com.oma2.oma20.servicios.TrabajadorRolServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrabajadorRolServicioImplementacion implements TrabajadorRolServicio {

    @Autowired
    TrabajadorRolRepositorio repositorio;
    @Override
    public List<TrabajadorRol> obtenerTodo() {
        return repositorio.findAll();
    }

    @Override
    public TrabajadorRol guardar(TrabajadorRol trabajador) {
        return repositorio.save(trabajador);
    }

    @Override
    public TrabajadorRol obtenerPorId(long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public TrabajadorRol obtenerPorNombre(String nombre) {
        return repositorio.findByNombre(nombre);
    }

    @Override
    public void eliminar(long id) {
        repositorio.deleteById(id);
    }
}
