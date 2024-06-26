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
    public List<Especie> obtenerEspecies() {
        return servicioImplementacion.obtenerTodo();
    }

    @PostMapping("/guardar/especie")
    public ResponseEntity<Especie> guardarEspecie(@RequestBody Especie especie) {
        Especie nueva_especie = servicioImplementacion.guardar(especie);
        return new ResponseEntity<>(nueva_especie, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/especie/{id}")
    public ResponseEntity<Especie> actualizarEspecie(@PathVariable long id, @RequestBody Especie especie) {
        Especie actualizarEspecie = servicioImplementacion.obtenerPorId(id);
        actualizarEspecie.setClase(especie.getClase());
        actualizarEspecie.setNombreComun(especie.getNombreComun());
        actualizarEspecie.setNombreCientifico(especie.getNombreCientifico());
        actualizarEspecie.setEspecie(especie.getEspecie());
        actualizarEspecie.setOrden(especie.getOrden());
        actualizarEspecie.setIdCategoriaAmenaza(especie.getIdCategoriaAmenaza());
        actualizarEspecie.setIdAlimento(especie.getIdAlimento());

        Especie especie_actualizada = servicioImplementacion.guardar(actualizarEspecie);
        return new ResponseEntity<>(especie_actualizada, HttpStatus.CREATED);
    }

    @GetMapping("/clase/{clase}")
    public ResponseEntity<List<Especie>> obtenerPorClase(@PathVariable String clase) {
        List<Especie> especie = servicioImplementacion.obtenerPorClase(clase);
        return ResponseEntity.ok(especie);
    }

    //////// CAMBIOS
    @GetMapping("/especie/{especie}")
    public ResponseEntity<List<Especie>> obtenerPorEspecie(@PathVariable String especie) {
        List<Especie> especies = servicioImplementacion.obtenerPorEspecie(especie);
        return ResponseEntity.ok(especies);
    }

    @GetMapping("/familia/{familia}")
    public ResponseEntity<List<Especie>> obtenerPorFamilia(@PathVariable String familia) {
        List<Especie> especies = servicioImplementacion.obtenerPorFamilia(familia);
        return ResponseEntity.ok(especies);
    }

    @GetMapping("/nombreCientifico/{nombreCientifico}")
    public ResponseEntity<List<Especie>> obtenerPorNombreCientifico(@PathVariable String nombreCientifico) {
        List<Especie> especies = servicioImplementacion.obtenerPorNombreCientifico(nombreCientifico);
        return ResponseEntity.ok(especies);
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<Especie>> obtenerPorGenero(@PathVariable String genero) {
        List<Especie> especies = servicioImplementacion.obtenerPorGenero(genero);
        return ResponseEntity.ok(especies);
    }

    @GetMapping("/categoriaAmenaza/{idCategoriaAmenaza}")
    public ResponseEntity<List<Especie>> obtenerIdCategoriaAmenaza(@PathVariable int idCategoriaAmenaza) {
        List<Especie> especies = servicioImplementacion.obtenerIdCategoriaAmenaza(idCategoriaAmenaza);
        return ResponseEntity.ok(especies);
    }

    @GetMapping("/alimento/{idAlimento}")
    public ResponseEntity<List<Especie>> obtenerPorIdAlimento(@PathVariable int idAlimento) {
        List<Especie> especies = servicioImplementacion.obtenerPorIdAlimento(idAlimento);
        return ResponseEntity.ok(especies);
    }

    //////// CAMBIOS
    @GetMapping("/porId/{id}")
    public ResponseEntity<Especie> obtenerId(@PathVariable long id) {
        Especie especie = servicioImplementacion.obtenerPorId(id);
        return ResponseEntity.ok(especie);
    }

    @DeleteMapping("/eliminar/especie/{id}")
    public ResponseEntity<HashMap<String, Boolean>> eliminarEspecie(@PathVariable long id) {
        this.servicioImplementacion.eliminar(id);
        HashMap<String, Boolean> estadoEspecie = new HashMap<>();
        estadoEspecie.put("Especie eliminado", true);
        return ResponseEntity.ok(estadoEspecie);
    }
}
