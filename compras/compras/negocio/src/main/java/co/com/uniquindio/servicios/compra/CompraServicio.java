package co.com.uniquindio.servicios.compra;

import co.com.uniquindio.dto.CompraDTO;
import co.com.uniquindio.entidades.Compra;

import java.util.List;

public interface CompraServicio {

    String cancelarCompra(Integer id)throws Exception;
    String estadoCompra(Integer id)throws Exception;
    String crearCompra(CompraDTO compra)throws Exception;
    List<Compra>historialCompras(Integer idUsuario)throws Exception;
    String detalleCompra(Integer id) throws Exception;










}
