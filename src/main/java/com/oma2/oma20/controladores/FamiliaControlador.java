package com.oma2.oma20.controladores;

import com.oma2.oma20.modelos.Familia;
import com.oma2.oma20.servicioImplementacion.FamiliaServicioImplementacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/familia")
@Validated
public class FamiliaControlador {
    @Autowired
    FamiliaServicioImplementacion servicioImplementacion;

    @GetMapping("/all")
    public List<Familia> obtenerRoles(){return servicioImplementacion.obtenerTodo();}

    @PostMapping("/guardar/familia")
    public ResponseEntity<Familia> guardarRol (@RequestBody Familia familia){
        Familia nuevo_familia = servicioImplementacion.guardar(familia);
        return new ResponseEntity<>(nuevo_familia, HttpStatus.CREATED);
    }
    @GetMapping("/familia/{param}")
    public ResponseEntity<Familia> obtenerPorEmailODni(@PathVariable String param){
        Familia obtener;
        if(param.matches("\\d+")){
            int id = Integer.parseInt(param);
            obtener = servicioImplementacion.obtenerPorId(id);
        }else{
            obtener = servicioImplementacion.obtenerPorNombre(param);
        }

        if(obtener == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(obtener);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<Familia> actualizarDatosRol(@PathVariable long id, @RequestBody Familia familia){
        Familia actualizarFamilia = servicioImplementacion.obtenerPorId(id);
        actualizarFamilia.setNombre(familia.getNombre());

        Familia familia_actualizado = servicioImplementacion.guardar(actualizarFamilia);
        return new ResponseEntity<>(familia_actualizado, HttpStatus.CREATED);
    }
    @DeleteMapping("/eliminar/rol/{id}")
    public ResponseEntity<HashMap<String, Boolean>> eliminarRol (@PathVariable long id){
        this.servicioImplementacion.eliminar(id);
        HashMap<String, Boolean> estadoRol = new HashMap<>();
        estadoRol.put("Familia eliminado", true);
        return ResponseEntity.ok(estadoRol);
    }

}
