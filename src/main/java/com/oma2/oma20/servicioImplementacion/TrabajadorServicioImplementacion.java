package com.oma2.oma20.servicioImplementacion;

import com.oma2.oma20.modelos.Trabajador;
import com.oma2.oma20.repositorios.TrabajadorRepositorio;
import com.oma2.oma20.servicios.ITrabajadorServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrabajadorServicioImplementacion implements ITrabajadorServicio {

    @Autowired
    TrabajadorRepositorio repositorio;
    @Override
    public List<Trabajador> obtenerTodo() {
        return repositorio.findAll();
    }

    @Override
    public Trabajador guardar(Trabajador trabajador) {
        return repositorio.save(trabajador);
    }

    @Override
    public Trabajador obtenerPorId(long id) {
        return repositorio.findById(id).orElse(null);
    }

    @Override
    public Trabajador obtenerPorDni(int dni) {
        return repositorio.findByDni(dni);
    }

    @Override
    public List<Trabajador> obtenerPorIdRol(int idRol) {
        return repositorio.findByIdRol(idRol);
    }

    @Override
    public boolean existeCorreo(String correo) {
        Trabajador trabajador = repositorio.findByEmail(correo);
        return trabajador != null;
    }

    @Override
    public Trabajador obtenerPorEmail(String correo) {
        return repositorio.findByEmail(correo);
    }

    @Override
    public void eliminar(long id) {
        repositorio.deleteById(id);
    }
}
