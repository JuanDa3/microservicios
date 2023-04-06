package co.com.uniquindio.servicios.compra;

import co.com.uniquindio.dto.CompraDTO;
import co.com.uniquindio.dto.ProductoDTO;
import co.com.uniquindio.entidades.Compra;
import co.com.uniquindio.entidades.Producto;
import co.com.uniquindio.entidades.Usuario;
import co.com.uniquindio.enums.EnumCompra;
import co.com.uniquindio.repositorios.CompraRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompraServicioImpl implements CompraServicio {

    private final CompraRepo compraRepo;

    public CompraServicioImpl(CompraRepo compraRepo) {
        this.compraRepo = compraRepo;
    }

    @Override
    public String cancelarCompra(Integer id) throws Exception {
        System.out.println(cambiarEstadoCompra(id));
        cambiarEstadoCompra(id);
        return null;
    }

    @Override
    public String estadoCompra(Integer id) throws Exception {
        return null;
    }

    @Override
    public String crearCompra(CompraDTO compra) throws Exception {
        if (compra != null) {

            LocalDate fechaActual = LocalDate.now();
            String numeroFactura = String.valueOf(UUID.randomUUID()).replace("-","");
            Usuario usuario = new Usuario();
            usuario.setId(compra.getUsuario().getId());
            usuario.setCorreo(compra.getUsuario().getCorreo());
            List<Producto>listaProductos = new ArrayList<>();

            for(ProductoDTO producto: compra.getProductos()){
                Producto productoNuevo = new Producto();
                productoNuevo.setId(producto.getId());
                productoNuevo.setNombre(producto.getNombre());
                productoNuevo.setReferencia(producto.getReferencia());
                productoNuevo.setPrecio(producto.getPrecio());

                listaProductos.add(productoNuevo);
            }

            Compra guardarCompra = Compra.builder()
                    .totalCompra(compra.getTotalCompra())
                    .medioPago(compra.getMedioPago())
                    .estado(EnumCompra.EN_PROCESO)
                    .fecha(fechaActual)
                    .numeroFactura(numeroFactura)
                    .productos(listaProductos)
                    .usuario(usuario)
                    .build();

            compraRepo.save(guardarCompra);

        } else {
            throw new Exception("No se puede guardar como nulo");
        }
        return "compra guardada exitosamente";
    }

    @Override
    public List<Compra> historialCompras(Integer idUsuario) throws Exception {
        return null;
    }

    @Override
    public String detalleCompra(Integer id) throws Exception {
        return null;
    }

    private Optional<Compra> cambiarEstadoCompra(Integer id) throws Exception {
        Optional<Compra> compraActual = compraRepo.findById(id);
        if (compraActual.orElseThrow(() -> new Exception("la compra no existe")).getEstado().equals(EnumCompra.CANCELADO)) {
            throw new Exception("La compra ya se encuentra cancelada");
        }
        compraActual.get().setEstado(EnumCompra.CANCELADO);
        return compraActual;
    }

}
