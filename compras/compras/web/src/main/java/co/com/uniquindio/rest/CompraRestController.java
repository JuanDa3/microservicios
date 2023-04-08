package co.com.uniquindio.rest;

import co.com.uniquindio.dto.CompraDTO;
import co.com.uniquindio.respuestas.CancelarCompraRespuesta;
import co.com.uniquindio.respuestas.CrearCompraRespuesta;
import co.com.uniquindio.respuestas.EstadoCompraRespuesta;
import co.com.uniquindio.servicios.compra.CompraServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/compras")
public class CompraRestController {

    public final CompraServicio compraServicio;

    public CompraRestController(CompraServicio compraServicio) {
        this.compraServicio = compraServicio;
    }

    @PostMapping
    public ResponseEntity<?>crearCompra(@RequestBody CompraDTO compra) {
        Map<String, Object>respuesta = new HashMap<>();
        CrearCompraRespuesta respuestaTransaccion;
        try{
            respuestaTransaccion = compraServicio.crearCompra(compra);
            respuesta.put("transaccion exitosa, resumen", respuestaTransaccion);
            System.out.println(respuestaTransaccion);
        }catch (Exception e){
            respuesta.put("mensaje", e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    @DeleteMapping("/cancelarCompra/{idCompra}")
    public ResponseEntity<?>cancelarCompra(@PathVariable int idCompra) {
        Map<String, Object>respuesta = new HashMap<>();
        try {
            CancelarCompraRespuesta respuestaTransaccion = compraServicio.cancelarCompra(idCompra);
            respuesta.put("respuesta", respuestaTransaccion);
        } catch (Exception e) {
            respuesta.put("error",e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/estado/{idCompra}")
    public ResponseEntity<?>obtenerEstadoCompra(@PathVariable int idCompra){
        Map<String, Object>respuesta = new HashMap<>();
        try {
            EstadoCompraRespuesta estadoCompraRespuesta = compraServicio.estadoCompra(idCompra);
            respuesta.put("Estado de la compra", estadoCompraRespuesta);
        } catch (Exception e) {
            respuesta.put("error", e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
    //https://dominio.com/compras/historial/
    //https://dominio.com/compras/detalle/idCompra

}
