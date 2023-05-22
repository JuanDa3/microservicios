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

        }catch (Exception e){
            respuesta.put("mensaje", e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

    @DeleteMapping("/{idCompra}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponse(
            responseCode = "204",
            description = "El recurso ha sido eliminado exitosamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CompraRestController.class)
            )
    )
    public ResponseEntity<?>cancelarCompra(@PathVariable int idCompra) {
        Map<String, Object>respuesta = new HashMap<>();
        try {
            CancelarCompraRespuesta respuestaTransaccion = compraServicio.cancelarCompra(idCompra);
            respuesta.put("respuesta", respuestaTransaccion);
            return new ResponseEntity<>(respuesta, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            respuesta.put("error",e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{idCompra}")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CompraRestController.class)
            )
    )
    public ResponseEntity<?>obtenerEstadoCompra(@PathVariable int idCompra){
        Map<String, Object>respuesta = new HashMap<>();
        try {
            EstadoCompraRespuesta estadoCompraRespuesta = compraServicio.estadoCompra(idCompra);
            respuesta.put("estadoCompra", estadoCompraRespuesta);
        } catch (Exception e) {
            respuesta.put("error", e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
            responseCode = "200",
            description = "El recurso ha sido creado exitosamente",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CompraRestController.class)
            )
    )
    public ResponseEntity<?>obtenerHistorialCompra(@RequestParam("email") String email){
        Map<String, Object>respuesta = new HashMap<>();
        try {
            List<HistorialCompraRespuesta> historialCompras = compraServicio.historialCompras(email);
            respuesta.put("historialCompras", historialCompras);
        } catch (Exception e) {
            respuesta.put("error", e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @GetMapping("/facturas")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(
            responseCode = "200",
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = CompraRestController.class)
            )
    )
    public ResponseEntity<?>obtenerDetalleCompra(@RequestParam("numeroFactura") String numeroFactura){
        Map<String, Object>respuesta = new HashMap<>();
        try {
            DetalleCompraRespuesta detalleCompraRespuesta = compraServicio.detalleCompra(numeroFactura);
            respuesta.put("detalleCompra", detalleCompraRespuesta);
        } catch (Exception e) {
            respuesta.put("error", e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }
}
