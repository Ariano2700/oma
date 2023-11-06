package com.oma2.oma20.controladores;

import com.oma2.oma20.modelos.Alimento;
import com.oma2.oma20.servicioImplementacion.AlimentoServicioImplementacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/alimento")
public class AlimentoControlador {

    @Autowired
    AlimentoServicioImplementacion implementacion;

    @GetMapping("/all")
    public List<Alimento> obtenerAlimentos(){return implementacion.obtenerTodo();}


    @PostMapping("/guardar/alimento")
    public ResponseEntity<Alimento> guardarAlimento(@RequestBody Alimento alimento ){
        Alimento nuevo_alimento = implementacion.guardar(alimento);
        return new ResponseEntity<>(nuevo_alimento, HttpStatus.CREATED);
    }

    @GetMapping("/alimento/{param}")
    public ResponseEntity<Alimento> obtenerPorIDoMarca(@PathVariable String param){
        Alimento obtenerAlimentoDatos;
        if(param.matches("\\d+")){
            long id = Long.parseLong(param);
            obtenerAlimentoDatos = implementacion.obtenerPorId(id);
        }else{
            obtenerAlimentoDatos = implementacion.obtenerPorMarca(param);
        }

        if(obtenerAlimentoDatos == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(obtenerAlimentoDatos);
    }
    @PutMapping("/actualizar/alimento/{param}")
    public ResponseEntity<Alimento> actualizarDatosAlimento(@PathVariable String param, @RequestBody Alimento alimento) {
        Alimento actualizarAlimento;

        if (param.matches("\\d+")) {
            long id = Long.parseLong(param);
            actualizarAlimento = implementacion.obtenerPorId(id);
        } else {
            actualizarAlimento = implementacion.obtenerPorMarca(param);
        }

        if (actualizarAlimento == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        actualizarAlimento.setMarca(alimento.getMarca());
        actualizarAlimento.setFecha_compra(alimento.getFecha_compra());
        actualizarAlimento.setPrecio_unitario(alimento.getPrecio_unitario());
        actualizarAlimento.setVolumen(alimento.getVolumen());
        actualizarAlimento.setStock(alimento.getStock());
        Alimento alimento_actualizado = implementacion.guardar(actualizarAlimento);
        return new ResponseEntity<>(alimento_actualizado, HttpStatus.CREATED);
    }

    @DeleteMapping("/eliminar/alimento/{id}")
    public ResponseEntity<HashMap<String, Boolean>> eliminarAlimento(@PathVariable long id){
        this.implementacion.eliminar(id);
        HashMap<String, Boolean> estadoAlimento =new HashMap<>();
        estadoAlimento.put("Alimento eliminado", true);
        return ResponseEntity.ok(estadoAlimento);
    }
}
