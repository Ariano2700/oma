package com.oma2.oma20.servicioImplementacion;

import com.oma2.oma20.modelos.CategoriaAmenaza;
import com.oma2.oma20.repositorios.CategoriaAmenazaRepositorio;
import com.oma2.oma20.servicios.CategoriaAmenazaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategoriaAmenazaServicioImplementacion implements CategoriaAmenazaServicio{
    @Autowired
    CategoriaAmenazaRepositorio repositorio;

    @Override
    public List<CategoriaAmenaza> obtenerTodo() {
        return repositorio.findAll();
    }

    @Override
    public CategoriaAmenaza guardar(CategoriaAmenaza categoriaAmenaza) {
        return repositorio.save(categoriaAmenaza);
    }

    @Override
    public CategoriaAmenaza obtenerPorId(long id) {
        return  repositorio.findById(id).orElse(null);
    }

    @Override
    public void eliminar(long id) {
        repositorio.deleteById(id);
    }
}
