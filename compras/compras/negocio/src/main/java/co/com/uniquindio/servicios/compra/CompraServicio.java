package co.com.uniquindio.servicios.compra;

import co.com.uniquindio.dto.CompraDTO;
import co.com.uniquindio.respuestas.CancelarCompraRespuesta;
import co.com.uniquindio.respuestas.CrearCompraRespuesta;
import co.com.uniquindio.respuestas.EstadoCompraRespuesta;
import co.com.uniquindio.respuestas.HistorialCompraRespuesta;

import java.util.List;

public interface CompraServicio {

    CancelarCompraRespuesta cancelarCompra(Integer id)throws Exception;
    EstadoCompraRespuesta estadoCompra(Integer id)throws Exception;
    CrearCompraRespuesta crearCompra(CompraDTO compra)throws Exception;
    List<HistorialCompraRespuesta>historialCompras(String correo)throws Exception;
    String detalleCompra(Integer id) throws Exception;
}
