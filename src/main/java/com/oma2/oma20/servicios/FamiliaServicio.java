package com.oma2.oma20.servicios;

import com.oma2.oma20.modelos.Familia;

import java.util.List;

public interface FamiliaServicio {
    public List<Familia> obtenerTodo();
    public Familia guardar(Familia familia);
    public Familia obtenerPorId(long id);
    public Familia obtenerPorNombre(String nombre);
    public void eliminar(long id);
}
