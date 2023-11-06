package com.oma2.oma20.servicios;

import com.oma2.oma20.modelos.Especie;

import java.util.List;

public interface EspecieServicio {
    public List<Especie> obtenerTodo();
    public Especie guardar(Especie especie);
    public Especie obtenerPorId(long id);
    public List<Especie> obtenerPorClase(String clase);
    public List<Especie> obtenerPorEspecie(String especie);
    public List<Especie> obtenerPorFamilia(String familia);
    public List<Especie> obtenerPorNombreCientifico(String nombreCientifico);
    public List<Especie> obtenerPorGenero(String Genero);
    public List<Especie> obtenerIdCategoriaAmenaza(int idCategoriaAmenaza);
    public List<Especie> obtenerPorIdAlimento(int idAlimento);
    public void eliminar(long id);
}
