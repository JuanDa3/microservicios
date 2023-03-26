package co.com.uniquindio.rest;

import co.com.uniquindio.entidades.Compra;
import co.com.uniquindio.servicios.CompraServicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/compras")
public class CompraRestController {

    public final CompraServicio compraServicio;

    public CompraRestController(CompraServicio compraServicio) {
        this.compraServicio = compraServicio;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?>crearCompra(Compra compra) throws Exception {
        String mensajeGuardar = compraServicio.crearCompra(compra);
        return new ResponseEntity<>(mensajeGuardar, HttpStatus.CREATED);
    }

}
