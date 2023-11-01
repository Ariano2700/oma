package com.oma2.oma20.controladores;

import com.oma2.oma20.modelos.Recinto;
import com.oma2.oma20.servicioImplementacion.RecintoServicioImplementacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/recinto")
@Validated
public class RecintoControlador {
    @Autowired
    RecintoServicioImplementacion servicioImplementacion;

    @GetMapping("/all")
    public List<Recinto> obtenerAnimales(){return servicioImplementacion.obtenerTodo();}

    @PostMapping("/guardar/recinto")
    public ResponseEntity<Recinto> guardarEspecie(@RequestBody Recinto recinto){
        Recinto nuevo_recinto = servicioImplementacion.guardar(recinto);
        return new ResponseEntity<>(nuevo_recinto, HttpStatus.CREATED);
    }
    @PutMapping("/actualizar/recinto/{id}")
    public ResponseEntity<Recinto> actualizarRecinto (@PathVariable long id, @RequestBody Recinto recinto){
        Recinto actualizarRecinto = servicioImplementacion.obtenerPorId(id);
        actualizarRecinto.setNombre(recinto.getNombre());
        actualizarRecinto.setTipo(recinto.getTipo());
        actualizarRecinto.setEstado(recinto.getEstado());
        actualizarRecinto.setUbicacion(recinto.getUbicacion());

        Recinto recinto_actualizado = servicioImplementacion.guardar(actualizarRecinto);
        return new ResponseEntity<>(recinto_actualizado, HttpStatus.CREATED);
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Recinto>> obtenerPorTipos(@PathVariable String tipo){
        List<Recinto> recinto = servicioImplementacion.obtenerPorTipo(tipo);
        return ResponseEntity.ok(recinto);
    }
    @GetMapping("/nombre/recinto/{nombre}")
    public ResponseEntity<Recinto> obtenerPorNombreAnimal(@PathVariable String nombre){
        Recinto recinto = servicioImplementacion.obtenerPorNombre(nombre);
        return ResponseEntity.ok(recinto);
    }
    @GetMapping("/porId/{id}")
    public ResponseEntity<Recinto> obtenerId(@PathVariable long id){
        Recinto recinto = servicioImplementacion.obtenerPorId(id);
        return ResponseEntity.ok(recinto);
    }
    @DeleteMapping("/eliminar/animal/{id}")
    public ResponseEntity<HashMap<String, Boolean>> eliminarEspecie (@PathVariable long id) {
        this.servicioImplementacion.eliminar(id);
        HashMap<String, Boolean> estadoEspecie = new HashMap<>();
        estadoEspecie.put("Especie eliminado", true);
        return ResponseEntity.ok(estadoEspecie);
    }
}
