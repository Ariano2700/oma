package com.oma2.oma20.servicioImplementacion;

import com.oma2.oma20.modelos.Alimento;
import com.oma2.oma20.repositorios.AlimentoRepositorio;
import com.oma2.oma20.servicios.AlimentoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlimentoServicioImplementacion implements AlimentoServicio {

    @Autowired
    AlimentoRepositorio alimentoRepositorio;
    @Override
    public List<Alimento> obtenerTodo() {
        return alimentoRepositorio.findAll();
    }

    @Override
    public Alimento guardar(Alimento alimento) {
        return alimentoRepositorio.save(alimento );
    }

    @Override
    public Alimento obtenerPorId(long id) {
        return alimentoRepositorio.findById(id).orElse(null);
    }

    @Override
    public Alimento obtenerPorMarca(String marca) {
        return alimentoRepositorio.findByMarca(marca);
    }

    @Override
    public void eliminar(long id) {
alimentoRepositorio.deleteById(id);
    }
}
