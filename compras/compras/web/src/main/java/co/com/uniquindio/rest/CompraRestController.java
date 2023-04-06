package co.com.uniquindio.rest;

import co.com.uniquindio.dto.CompraDTO;
import co.com.uniquindio.servicios.compra.CompraServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/compras")
public class CompraRestController {

    public final CompraServicio compraServicio;

    public CompraRestController(CompraServicio compraServicio) {
        this.compraServicio = compraServicio;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?>crearCompra(@RequestBody CompraDTO compra) throws Exception {
        String mensajeGuardar = compraServicio.crearCompra(compra);
        return new ResponseEntity<>(mensajeGuardar, HttpStatus.CREATED);
    }

}
