package com.oma2.oma20.servicios;

import com.oma2.oma20.modelos.Alimento;

import java.util.List;

public interface AlimentoServicio {
    public List<Alimento> obtenerTodo();
    public Alimento guardar(Alimento alimento);
    public Alimento obtenerPorId(long id);
    public Alimento obtenerPorMarca(String marca);
    public void eliminar(long id);
}
