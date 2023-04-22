package co.com.uniquindio.negocio.servicios.producto;

import co.com.uniquindio.persistencia.entidades.Producto;

import java.util.List;

public interface ProductoServicio {

    List<Producto>listaProductos()throws Exception;
}
