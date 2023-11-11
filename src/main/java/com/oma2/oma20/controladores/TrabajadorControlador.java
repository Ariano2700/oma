package com.oma2.oma20.controladores;

import com.oma2.oma20.modelos.Trabajador;
import com.oma2.oma20.servicioImplementacion.TrabajadorServicioImplementacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/trabajador")
@Validated
public class TrabajadorControlador {

    @Autowired
    TrabajadorServicioImplementacion servicioImplementacion;

    @GetMapping("/all")
    public List<Trabajador> obtenerTrabajadores(){return servicioImplementacion.obtenerTodo();}


    @PostMapping("/guardar/trabajador")
    public ResponseEntity<Trabajador> guardarAlimento(@RequestBody Trabajador trabajador ){
        Trabajador nuevo_trabajador = servicioImplementacion.guardar(trabajador);
        return new ResponseEntity<>(nuevo_trabajador, HttpStatus.CREATED);
    }

    @GetMapping("/obtener/{param}")
    public ResponseEntity<Trabajador> obtenerPorEmailODni(@PathVariable String param){
        Trabajador obtener;
        if(param.matches("\\d+")){
            int dni = Integer.parseInt(param);
            obtener = servicioImplementacion.obtenerPorDni(dni);
        }else{
            obtener = servicioImplementacion.obtenerPorEmail(param);
        }

        if(obtener == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(obtener    );
    }
    @GetMapping("/roles/{rol}")
    public ResponseEntity<List<Trabajador>> obtenerPorRoles(@PathVariable int idRol){
        List<Trabajador> roles = servicioImplementacion.obtenerPorIdRol(idRol);
        return ResponseEntity.ok(roles);
    }

    @PutMapping("/actualizar/trabajador/{dni}")
    public ResponseEntity<Trabajador> actualizarDatosAlimento(@PathVariable int dni, @RequestBody Trabajador trabajador) {
        Trabajador actualizarTrabajador = servicioImplementacion.obtenerPorDni(dni);

        actualizarTrabajador.setNombre(trabajador.getNombre());
        actualizarTrabajador.setApellido(trabajador.getApellido());
        actualizarTrabajador.setDni(trabajador.getDni());
        actualizarTrabajador.setTelefono(trabajador.getTelefono());
        actualizarTrabajador.setDireccion(trabajador.getDireccion());
        actualizarTrabajador.setEmail(trabajador.getEmail());
        actualizarTrabajador.setPassword(trabajador.getPassword());
        actualizarTrabajador.setUsername(trabajador.getUsername());
        //actualizarTrabajador.setIdRol(trabajador.getIdRol());

        Trabajador trabajador_actualizado = servicioImplementacion.guardar(actualizarTrabajador);
        return new ResponseEntity<>(trabajador_actualizado, HttpStatus.CREATED);
    }
    @PatchMapping("/actualizar/foto/perfil/{dni}")
    //public ResponseEntity<Trabajador> actualizarFotoPerfil (@PathVariable int dni, @RequestBody Trabajador trabajador){
    public ResponseEntity<Trabajador> actualizarFotoPerfil (@PathVariable int dni, @RequestParam("fotoPerfil")MultipartFile fotoPerfil){
        try {
            Trabajador trabajadorExistente = servicioImplementacion.obtenerPorDni(dni);
            if (!fotoPerfil.isEmpty()){
                trabajadorExistente.setFotoPerfil(fotoPerfil.getBytes());
            }
            Trabajador trabajador_actualizado = servicioImplementacion.guardar(trabajadorExistente);
            return new ResponseEntity<>(trabajador_actualizado, HttpStatus.CREATED);
        }catch (IOException e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/verificar/email")
    public ResponseEntity<Map<String, Boolean>> verificarExistencia (@RequestParam String email){
        boolean existeCorreo = servicioImplementacion.existeCorreo(email);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Existe", existeCorreo);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/eliminar/trabajador/{id}")
    public ResponseEntity<HashMap<String, Boolean>> eliminarAlimento(@PathVariable long id){
        this.servicioImplementacion.eliminar(id);
        HashMap<String, Boolean> estadoTrabajador =new HashMap<>();
        estadoTrabajador.put("Trabajador eliminado", true);
        return ResponseEntity.ok(estadoTrabajador);
    }
}
