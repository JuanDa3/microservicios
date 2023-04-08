package co.com.uniquindio.servicios.compra;

import co.com.uniquindio.dto.CompraDTO;
import co.com.uniquindio.entidades.Compra;
import co.com.uniquindio.respuestas.CancelarCompraRespuesta;
import co.com.uniquindio.respuestas.CrearCompraRespuesta;
import co.com.uniquindio.respuestas.EstadoCompraRespuesta;

import java.util.List;

public interface CompraServicio {

    CancelarCompraRespuesta cancelarCompra(Integer id)throws Exception;
    EstadoCompraRespuesta estadoCompra(Integer id)throws Exception;
    CrearCompraRespuesta crearCompra(CompraDTO compra)throws Exception;
    List<Compra>historialCompras(Integer idUsuario)throws Exception;
    String detalleCompra(Integer id) throws Exception;










}
