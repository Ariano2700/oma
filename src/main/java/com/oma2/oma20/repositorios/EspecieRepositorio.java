package com.oma2.oma20.repositorios;

import com.oma2.oma20.modelos.Especie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EspecieRepositorio extends JpaRepository<Especie, Long> {
    List<Especie> findByClase(String clase);
    List<Especie> findByEspecie(String especie);
    List<Especie> findByFamilia(String familia);
    List<Especie> findByNombreCientifico (String nombreCientifico);
    List<Especie> findByGenero(String genero);
    List<Especie> findByIdCategoriaAmenaza(int idCategoriaAmenaza);
    List<Especie> findByIdAlimento(int idAlimento);}
