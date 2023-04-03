package co.com.uniquindio.servicios.producto;

import co.com.uniquindio.entidades.Producto;
import co.com.uniquindio.repositorios.ProductoRepo;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductoServicioImpl implements ProductoServicio{

    public final ProductoRepo productoRepo;

    public ProductoServicioImpl(ProductoRepo productoRepo) {
        this.productoRepo = productoRepo;
    }

    @Override
    public List<Producto> listaProductos() throws Exception {
        return productoRepo.findAll();
    }
}
