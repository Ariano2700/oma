package com.oma2.oma20.controladores;

import com.oma2.oma20.modelos.Trabajador;
import com.oma2.oma20.servicioImplementacion.TrabajadorServicioImplementacion;
import com.oma2.oma20.utils.ValidacionesPost;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/trabajador")
@Validated
public class TrabajadorControlador {

    @Autowired
    TrabajadorServicioImplementacion servicioImplementacion;
    @Autowired
    ValidacionesPost validacionesPost;


    @GetMapping("/all")
    public List<Trabajador> obtenerTrabajadores(){return servicioImplementacion.obtenerTodo();}


    @PostMapping("/guardar/trabajador")
    public ResponseEntity<Trabajador> guardarAlimento(@RequestBody Trabajador trabajador ){
         if (!validacionesPost.isValidPassword(trabajador.getPassword()) || !validacionesPost.isValidEmail(trabajador.getEmail()) || !validacionesPost.isValidDNI(trabajador.getDni())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

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

    //EDITABLE POR EL ADMINISTRADOR GENERAL
    @PutMapping("/actualizar/trabajador/{dni}")
    public ResponseEntity<Trabajador> actualizarDatosTrabajador(@PathVariable int dni, @RequestBody Trabajador trabajador) {
        Trabajador actualizarTrabajador = servicioImplementacion.obtenerPorDni(dni);

        actualizarTrabajador.setNombre(trabajador.getNombre());
        actualizarTrabajador.setApellido(trabajador.getApellido());
        actualizarTrabajador.setDni(trabajador.getDni());
        actualizarTrabajador.setTelefono(trabajador.getTelefono());
        actualizarTrabajador.setDireccion(trabajador.getDireccion());
        actualizarTrabajador.setBiografia(trabajador.getBiografia());
        actualizarTrabajador.setEmail(trabajador.getEmail());
        actualizarTrabajador.setPassword(trabajador.getPassword());
        actualizarTrabajador.setUsername(trabajador.getUsername());
        //actualizarTrabajador.setIdRol(trabajador.getIdRol());

        Trabajador trabajador_actualizado = servicioImplementacion.guardar(actualizarTrabajador);
        return new ResponseEntity<>(trabajador_actualizado, HttpStatus.CREATED);
    }
    //EDITABLE POR EL TRABAJADOR
    @PatchMapping("/editar/perfil/trabajador/{dni}")
    public ResponseEntity<Trabajador> editarPerfilTrabajador(@PathVariable int dni, @RequestBody Trabajador trabajador) {
        Trabajador actualizarTrabajador = servicioImplementacion.obtenerPorDni(dni);

        actualizarTrabajador.setNombre(trabajador.getNombre());
        actualizarTrabajador.setApellido(trabajador.getApellido());
        actualizarTrabajador.setTelefono(trabajador.getTelefono());
        actualizarTrabajador.setDireccion(trabajador.getDireccion());
        actualizarTrabajador.setBiografia(trabajador.getBiografia());
        actualizarTrabajador.setEmail(trabajador.getEmail());
        actualizarTrabajador.setUsername(trabajador.getUsername());

        Trabajador trabajador_actualizado = servicioImplementacion.guardar(actualizarTrabajador);
        return new ResponseEntity<>(trabajador_actualizado, HttpStatus.CREATED);
    }
    //CAMBIAR CONTRASEÑA
    @PatchMapping("/cambiar/contraseña/{dni}")
    public ResponseEntity<Trabajador> cambiarContraseña(@PathVariable int dni, @RequestBody Trabajador trabajador){
        Trabajador cambiartContraseña = servicioImplementacion.obtenerPorDni(dni);
        cambiartContraseña.setPassword(trabajador.getPassword());
        Trabajador contraseña_actualizada = servicioImplementacion.guardar(cambiartContraseña);
        return new ResponseEntity<>(contraseña_actualizada, HttpStatus.CREATED);
    }
    //FOTO DE PERFIL
    @PatchMapping("/actualizar/foto/perfil/{dni}")
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
    //FOTO DE PORTADA
    @PatchMapping("/actualizar/foto/portada/{dni}")
    public ResponseEntity<Trabajador> actualizarFotoPortada (@PathVariable int dni, @RequestParam("fotoPortada")MultipartFile fotoPortada){
        try {
            Trabajador trabajadorExistente = servicioImplementacion.obtenerPorDni(dni);
            if (!fotoPortada.isEmpty()){
                trabajadorExistente.setFotoPortada(fotoPortada.getBytes());
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
