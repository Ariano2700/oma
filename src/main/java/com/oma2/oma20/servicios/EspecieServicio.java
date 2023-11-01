package com.oma2.oma20.servicios;

import com.oma2.oma20.modelos.Especie;

import java.util.List;

public interface EspecieServicio {
    public List<Especie> obtenerTodo();
    public Especie guardar(Especie especie);
    public Especie obtenerPorId(long id);
    public Especie obtenerPorCategoria(String categoria);
    public void eliminar(long id);
}
