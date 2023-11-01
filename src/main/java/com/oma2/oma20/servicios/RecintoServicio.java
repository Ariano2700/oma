package com.oma2.oma20.servicios;


import com.oma2.oma20.modelos.Recinto;

import java.util.List;

public interface RecintoServicio {
    public List<Recinto> obtenerTodo();
    public Recinto guardar(Recinto recinto);
    public Recinto obtenerPorId(long id);
    public List<Recinto> obtenerPorTipo(String tipo);
    public Recinto obtenerPorNombre(String nombre);

    public void eliminar(long id);
}
