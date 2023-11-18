package com.oma2.oma20.controladores;

import com.oma2.oma20.modelos.ImagenesAnimal;
import com.oma2.oma20.servicioImplementacion.ImagenesAnimalServicioImplementacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/img/animal")
@Validated
public class ImagenesAnimalControlador {
    @Autowired
    ImagenesAnimalServicioImplementacion servicioImplementacion;

    @GetMapping("/all")
    public List<ImagenesAnimal> obtenerImagenes(){return servicioImplementacion.obtenerTodo();}

    @PostMapping("/guardar/imagen")
    public ResponseEntity<ImagenesAnimal> guardarImg(@RequestBody ImagenesAnimal imagenesAnimal){
        ImagenesAnimal nueva_imagen = servicioImplementacion.guardar(imagenesAnimal);
        return new ResponseEntity<>(nueva_imagen, HttpStatus.CREATED);
    }
    @GetMapping("/porId/animal/{idAnimal}")
    public ResponseEntity<List<ImagenesAnimal>> obtenerPorIdAnimal (@PathVariable int idAnimal){
        List<ImagenesAnimal> porIdAnimal = servicioImplementacion.obtenerPorIdAnimal(idAnimal);
        return ResponseEntity.ok(porIdAnimal);
    }

    @PatchMapping("/actualizar/imagenes/{id}")
    public ResponseEntity<ImagenesAnimal> actualizarImg(@PathVariable long id, @RequestParam("fotoAnimal")MultipartFile imagenAnimal){
        try{
            ImagenesAnimal imgExiste = servicioImplementacion.obtenerPorId(id);
            if (!imagenAnimal.isEmpty()){
                imgExiste.setImgAnimal(imagenAnimal.getBytes());
            }
            ImagenesAnimal imgActualizada = servicioImplementacion.guardar(imgExiste);
            return new ResponseEntity<>(imgActualizada, HttpStatus.CREATED);
        }catch (IOException e)
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/eliminar/img/{id}")
    public ResponseEntity<HashMap<String, Boolean>> eliminarImg(@PathVariable long id){
        this.servicioImplementacion.eliminar(id);
        HashMap<String, Boolean> estadoImg = new HashMap<>();
        estadoImg.put("Img animal eliminada", true);
        return ResponseEntity.ok(estadoImg);
    }
}
