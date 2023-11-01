package com.oma2.oma20.servicios;

import com.oma2.oma20.modelos.CategoriaAmenaza;

import java.util.List;

public interface CategoriaAmenazaServicio {
    public List<CategoriaAmenaza> obtenerTodo();
    public CategoriaAmenaza guardar(CategoriaAmenaza categoriaAmenaza);
    public CategoriaAmenaza obtenerPorId(long id);
    public void eliminar(long id);
}
