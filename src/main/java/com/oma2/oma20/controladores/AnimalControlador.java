package com.oma2.oma20.controladores;

import com.oma2.oma20.modelos.Animal.Animal;
import com.oma2.oma20.modelos.Animal.Sexo;
import com.oma2.oma20.servicioImplementacion.AnimalServicioImplementacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/apo/animal")
@Validated
public class AnimalControlador {
    @Autowired
    AnimalServicioImplementacion servicioImplementacion;

    @GetMapping("/all")
    public List<Animal> obtenerAnimales(){return servicioImplementacion.obtenerTodo();}

    @PostMapping("/guardar/animal")
    public ResponseEntity<Animal> guardarEspecie(@RequestBody Animal animal){
        Animal nuevo_animal = servicioImplementacion.guardar(animal);
        return new ResponseEntity<>(nuevo_animal, HttpStatus.CREATED);
    }
    @PutMapping("/actualizar/animal/{id}")
    public ResponseEntity<Animal> actualizarAnimal(@PathVariable long id, @RequestBody Animal animal){
        Animal actualizarAnimal = servicioImplementacion.obtenerPorId(id);
        actualizarAnimal.setNombreAnimal(animal.getNombreAnimal());
        actualizarAnimal.setEdad(animal.getEdad());
        actualizarAnimal.setSexo(animal.getSexo());
        actualizarAnimal.setTipo(animal.getTipo());
        actualizarAnimal.setIdEspecie(animal.getIdEspecie());
        actualizarAnimal.setIdRecinto(animal.getIdRecinto());

        Animal animal_actualizado = servicioImplementacion.guardar(actualizarAnimal);
        return new ResponseEntity<>(animal_actualizado, HttpStatus.CREATED);
    }
    @GetMapping("/sexo/{sexo}")
    public ResponseEntity<List<Animal>> obtenerPorSexo(@PathVariable Sexo sexo){
        List<Animal> animal = servicioImplementacion.obtenerPorSexo(sexo);
        return ResponseEntity.ok(animal);
    }
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Animal>> obtenerPorTipos(@PathVariable String tipo){
        List<Animal> animal = servicioImplementacion.obtenerPorTipo(tipo);
        return ResponseEntity.ok(animal);
    }
    @GetMapping("/nombre/animal/{nombreAnimal}")
    public ResponseEntity<Animal> obtenerPorNombreAnimal(@PathVariable String nombreAnimal){
        Animal animal = servicioImplementacion.obtenerPorNombre(nombreAnimal);
        return ResponseEntity.ok(animal);
    }
    @GetMapping("/porId/{id}")
    public ResponseEntity<Animal> obtenerId(@PathVariable long id){
        Animal animal = servicioImplementacion.obtenerPorId(id);
        return ResponseEntity.ok(animal);
    }
    @DeleteMapping("/eliminar/animal/{id}")
    public ResponseEntity<HashMap<String, Boolean>> eliminarEspecie (@PathVariable long id) {
        this.servicioImplementacion.eliminar(id);
        HashMap<String, Boolean> estadoEspecie = new HashMap<>();
        estadoEspecie.put("Especie eliminado", true);
        return ResponseEntity.ok(estadoEspecie);
    }
}
