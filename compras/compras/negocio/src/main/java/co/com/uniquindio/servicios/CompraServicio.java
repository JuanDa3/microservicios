package co.com.uniquindio.servicios;

import co.com.uniquindio.entidades.Compra;

public interface CompraServicio {

    String crearCompra(Compra compra)throws Exception;

    String eliminarCompra(Integer id) throws Exception;
}
