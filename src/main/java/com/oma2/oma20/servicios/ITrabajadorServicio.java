package com.oma2.oma20.servicios;

import com.oma2.oma20.modelos.Trabajador;

import java.util.List;

public interface ITrabajadorServicio {
    public List<Trabajador> obtenerTodo();
    public Trabajador guardar(Trabajador trabajador);
    public Trabajador obtenerPorId(long id);
    public Trabajador obtenerPorDni(int dni);
    public List<Trabajador> obtenerPorIdRol (int idRol);
    public boolean existeCorreo (String correo);
    public Trabajador obtenerPorEmail (String correo);
    public void eliminar(long id);
}
