package com.oma2.oma20.controladores;

import com.oma2.oma20.modelos.Especie;
import com.oma2.oma20.servicioImplementacion.EspecieServicioImplementacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/especie")
@Validated
public class EspecieControlador {

    @Autowired
    EspecieServicioImplementacion servicioImplementacion;

    @GetMapping("/all")
    public List<Especie> obtenerEspecies(){return servicioImplementacion.obtenerTodo();}

    @PostMapping("/guardar/especie")
    public ResponseEntity<Especie> guardarEspecie(@RequestBody Especie especie){
        Especie nueva_especie = servicioImplementacion.guardar(especie);
        return new ResponseEntity<>(nueva_especie, HttpStatus.CREATED);
    }
    @GetMapping("/categoria/{categoria}")
    public ResponseEntity<Especie> obtenerPorCategoria(@PathVariable String categoria){
        Especie especie = servicioImplementacion.obtenerPorCategoria(categoria);
        return ResponseEntity.ok(especie);
    }
    @GetMapping("/porId/{id}")
    public ResponseEntity<Especie> obtenerId(@PathVariable long id){
        Especie especie = servicioImplementacion.obtenerPorId(id);
        return ResponseEntity.ok(especie);
    }
    @DeleteMapping("/eliminar/especie/{id}")
    public ResponseEntity<HashMap<String, Boolean>> eliminarEspecie (@PathVariable long id) {
        this.servicioImplementacion.eliminar(id);
        HashMap<String, Boolean> estadoEspecie = new HashMap<>();
        estadoEspecie.put("Especie eliminado", true);
        return ResponseEntity.ok(estadoEspecie);
    }
}
