package com.oma2.oma20.servicios;

import com.oma2.oma20.modelos.TrabajadorRol;

import java.util.List;

public interface TrabajadorRolServicio {    public List<TrabajadorRol> obtenerTodo();
    public TrabajadorRol guardar(TrabajadorRol trabajador);
    public TrabajadorRol obtenerPorId(long id);
    public TrabajadorRol obtenerPorNombre(String nombre);
    public void eliminar(long id);
}
