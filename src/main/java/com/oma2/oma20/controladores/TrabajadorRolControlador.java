package com.oma2.oma20.controladores;

import com.oma2.oma20.modelos.TrabajadorRol;
import com.oma2.oma20.servicioImplementacion.TrabajadorRolServicioImplementacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/rol")
@Validated
public class TrabajadorRolControlador {

    @Autowired
    TrabajadorRolServicioImplementacion servicioImplementacion;

    @GetMapping("/all")
    public List<TrabajadorRol> obtenerRoles(){return servicioImplementacion.obtenerTodo();}

    @PostMapping("/guardar/rol")
    public ResponseEntity<TrabajadorRol> guardarRol (@RequestBody TrabajadorRol rol){
        TrabajadorRol nuevo_rol = servicioImplementacion.guardar(rol);
        return new ResponseEntity<>(nuevo_rol, HttpStatus.CREATED);
    }
    @GetMapping("/rol/{nombre}")
    public ResponseEntity<TrabajadorRol> obtenerRol (@PathVariable String nombre){
        TrabajadorRol rol = servicioImplementacion.obtenerPorNombre(nombre);
        return ResponseEntity.ok(rol);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<TrabajadorRol> actualizarDatosRol(@PathVariable long id, @RequestBody TrabajadorRol trabajadorRol){
        TrabajadorRol actualizarRol = servicioImplementacion.obtenerPorId(id);
        actualizarRol.setNombre(trabajadorRol.getNombre());
        actualizarRol.setDescripcion(trabajadorRol.getDescripcion());

        TrabajadorRol rol_actualizado = servicioImplementacion.guardar(actualizarRol);
        return new ResponseEntity<>(rol_actualizado, HttpStatus.CREATED);
    }
    @DeleteMapping("/eliminar/rol/{id}")
    public ResponseEntity<HashMap<String, Boolean>> eliminarRol (@PathVariable long id){
        this.servicioImplementacion.eliminar(id);
        HashMap<String, Boolean> estadoRol = new HashMap<>();
        estadoRol.put("Rol eliminado", true);
        return ResponseEntity.ok(estadoRol);
    }






}
