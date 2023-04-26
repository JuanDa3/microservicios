package com.co.uniquindio.web.rest;

import com.co.uniquindio.negocio.respuestas.*;
import com.co.uniquindio.negocio.servicios.compra.CompraServicio;
import com.co.uniquindio.persistencia.dto.CompraDTO;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/compras")
public class CompraRestController {

    public final CompraServicio compraServicio;

    public CompraRestController(CompraServicio compraServicio) {
        this.compraServicio = compraServicio;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiResponse(
            responseCode = "201",
            description = "El recurso ha sido creado exitosamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CompraRestController.class)
            )
    )
    public ResponseEntity<?>crearCompra(@RequestBody CompraDTO compra) {
        Map<String, Object>respuesta = new HashMap<>();
        CrearCompraRespuesta respuestaTransaccion;
        try{
            respuestaTransaccion = compraServicio.crearCompra(compra);
            respuesta.put("resumen", respuestaTransaccion);
            System.out.println(respuestaTransaccion);
        }catch (Exception e){
            respuesta.put("mensaje", e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    @DeleteMapping("/cancelarCompra/{idCompra}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?>cancelarCompra(@PathVariable int idCompra) {
        Map<String, Object>respuesta = new HashMap<>();
        try {
            CancelarCompraRespuesta respuestaTransaccion = compraServicio.cancelarCompra(idCompra);
            respuesta.put("respuesta", respuestaTransaccion);
        } catch (Exception e) {
            respuesta.put("error",e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(respuesta, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/estado/{idCompra}")
    @ResponseStatus(HttpStatus.OK)
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

    @GetMapping("/historial/{correo}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?>obtenerHistorialCompra(@PathVariable String correo){
        Map<String, Object>respuesta = new HashMap<>();
        try {
            List<HistorialCompraRespuesta> historialCompras = compraServicio.historialCompras(correo);
            respuesta.put("historial de compras", historialCompras);
        } catch (Exception e) {
            respuesta.put("error", e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/detalle/{numero-factura}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?>obtenerDetalleCompra(@PathVariable(name = "numero-factura") String numeroFactura){
        Map<String, Object>respuesta = new HashMap<>();
        try {
            DetalleCompraRespuesta detalleCompraRespuesta = compraServicio.detalleCompra(numeroFactura);
            respuesta.put("detalle compra", detalleCompraRespuesta);
        } catch (Exception e) {
            respuesta.put("error", e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
