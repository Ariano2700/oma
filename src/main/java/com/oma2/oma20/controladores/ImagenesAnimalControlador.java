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
    public ResponseEntity<ImagenesAnimal> guardarImg(@RequestParam("imgAnimal") MultipartFile imgAnimal, @RequestParam("idAnimal") int idAnimal) {
        try {
            // Verifica si la imagen no es nula y tiene contenido
            if (imgAnimal != null && !imgAnimal.isEmpty()) {
                // Aquí puedes realizar la lógica para guardar la imagen en tu servicio
                byte[] imgBytes = imgAnimal.getBytes();
                ImagenesAnimal nueva_imagen = servicioImplementacion.guardar(new ImagenesAnimal(0,idAnimal, imgBytes));
                // Devuelve la nueva imagen creada
                return new ResponseEntity<>(nueva_imagen, HttpStatus.CREATED);
            } else {
                // Manejar el caso en el que no se haya proporcionado una imagen
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {
            // Manejar errores de IO, como problemas al leer los bytes de la imagen
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/porId/animal/{idAnimal}")
    public ResponseEntity<List<ImagenesAnimal>> obtenerPorIdAnimal (@PathVariable int idAnimal){
        List<ImagenesAnimal> porIdAnimal = servicioImplementacion.obtenerPorIdAnimal(idAnimal);
        return ResponseEntity.ok(porIdAnimal);
    }
    @GetMapping("/porId/{id}")
    public ResponseEntity<ImagenesAnimal> obtenerPorId (@PathVariable long id){
        ImagenesAnimal porId = servicioImplementacion.obtenerPorId(id);
        return ResponseEntity.ok(porId);
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
