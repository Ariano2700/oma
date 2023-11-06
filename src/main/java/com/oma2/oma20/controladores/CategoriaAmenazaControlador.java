package com.oma2.oma20.controladores;

import com.oma2.oma20.modelos.CategoriaAmenaza;
import com.oma2.oma20.servicioImplementacion.CategoriaAmenazaServicioImplementacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/categoriaamenaza")
@Validated
public class CategoriaAmenazaControlador {
    @Autowired
    CategoriaAmenazaServicioImplementacion servicioImplementacion;

    @GetMapping("/all")
    public List<CategoriaAmenaza> obtenerTodo() {return servicioImplementacion.obtenerTodo(); }

    @PostMapping("/guardar/categoriaamenaza")
    public ResponseEntity<CategoriaAmenaza> guardar(@RequestBody CategoriaAmenaza categoriaAmenaza){
        CategoriaAmenaza nueva_categoriaAmenaza = servicioImplementacion.guardar(categoriaAmenaza);
        return new ResponseEntity<>(nueva_categoriaAmenaza, HttpStatus.CREATED);
    }

    @PutMapping("/actualizar/categoriaamenaza/{id}")
    public ResponseEntity<CategoriaAmenaza> actualizarCategoriaAmenaza(@PathVariable long id, @RequestBody CategoriaAmenaza categoriaAmenaza){
        CategoriaAmenaza actualizarCategoriaAmenaza = servicioImplementacion.obtenerPorId(id);
        actualizarCategoriaAmenaza.setMinagri(categoriaAmenaza.getMinagri());
        actualizarCategoriaAmenaza.setCites(categoriaAmenaza.getCites()); // Corrección aquí
        actualizarCategoriaAmenaza.setUicn(categoriaAmenaza.getUicn());

        CategoriaAmenaza categoriaAmenaza_actualizada = servicioImplementacion.guardar(actualizarCategoriaAmenaza);
        return new ResponseEntity<>(categoriaAmenaza_actualizada, HttpStatus.CREATED);
    }
    @GetMapping("/porId/{id}")
    public ResponseEntity<CategoriaAmenaza> obtenerId(@PathVariable long id){
        CategoriaAmenaza amenaza = servicioImplementacion.obtenerPorId(id);
        return ResponseEntity.ok(amenaza);
    }
    @DeleteMapping("/eliminar/categoriaamenaza/{id}")
    public ResponseEntity<HashMap<String, Boolean>> eliminarCategoriaA(@PathVariable long id){
        this.servicioImplementacion.eliminar(id);
        HashMap<String,Boolean> estadoCategoriaAmenaza = new HashMap<>();
        estadoCategoriaAmenaza.put("Categoria de amenaza eliminada", true);
        return ResponseEntity.ok(estadoCategoriaAmenaza);
    }
}
