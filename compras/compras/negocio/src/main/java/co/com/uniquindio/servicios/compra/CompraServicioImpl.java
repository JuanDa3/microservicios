package co.com.uniquindio.servicios.compra;

import co.com.uniquindio.dto.CompraDTO;
import co.com.uniquindio.dto.ProductoDTO;
import co.com.uniquindio.dto.UsuarioDTO;
import co.com.uniquindio.entidades.Compra;
import co.com.uniquindio.entidades.Producto;
import co.com.uniquindio.entidades.Usuario;
import co.com.uniquindio.enums.EnumCompra;
import co.com.uniquindio.repositorios.CompraRepo;
import co.com.uniquindio.repositorios.ProductoRepo;
import co.com.uniquindio.repositorios.UsuarioRepo;
import co.com.uniquindio.respuestas.CrearCompraRespuesta;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompraServicioImpl implements CompraServicio {

    private final CompraRepo compraRepo;
    private final UsuarioRepo usuarioRepo;

    private final ProductoRepo productoRepo;

    public CompraServicioImpl(CompraRepo compraRepo, UsuarioRepo usuarioRepo, ProductoRepo productoRepo) {
        this.compraRepo = compraRepo;
        this.usuarioRepo = usuarioRepo;
        this.productoRepo = productoRepo;
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
    public CrearCompraRespuesta crearCompra(CompraDTO compra) throws Exception {

        CrearCompraRespuesta crearCompraRespuesta;

        if (compra != null) {
            LocalDate fechaActual = LocalDate.now();
            String numeroFactura = String.valueOf(UUID.randomUUID()).replace("-", "");
            Usuario usuario = verificarUsuario(compra.getUsuario());
            List<Producto> listaProductos = verificarProductos(compra.getProductos());

            Compra guardarCompra = Compra.builder()
                    .totalCompra(calcularTotalCompra(compra.getProductos()))
                    .medioPago(compra.getMedioPago())
                    .estado(EnumCompra.EN_PROCESO)
                    .fecha(fechaActual)
                    .numeroFactura(numeroFactura)
                    .productos(listaProductos)
                    .usuario(usuario)
                    .build();

            compraRepo.save(guardarCompra);

            crearCompraRespuesta =  CrearCompraRespuesta.builder()
                    .correoUsuario(usuario.getCorreo())
                    .numeroFactura(guardarCompra.getNumeroFactura())
                    .totalCompra(guardarCompra.getTotalCompra())
                    .estado(guardarCompra.getEstado())
                    .fecha(guardarCompra.getFecha())
                    .productos(compra.getProductos())
                    .build();

            actualizarStock(compra.getProductos());

        } else {
            throw new Exception("No se puede guardar como nulo");
        }
        return crearCompraRespuesta;
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

    private Usuario verificarUsuario(UsuarioDTO usuarioDTO) throws Exception {
        Optional<Usuario> usuarioRegistrado = usuarioRepo.findById(usuarioDTO.getId());
        if (usuarioRegistrado.isPresent()) {
            Usuario usuario = new Usuario();
            usuario.setId(usuarioDTO.getId());
            usuario.setCorreo(usuarioDTO.getCorreo());
            return usuario;
        } else {
            throw new Exception("El usuario no se encuentra registrado");
        }
    }

    private List<Producto> verificarProductos(List<ProductoDTO> productos) throws Exception {

        List<Producto> listaProductos = new ArrayList<>();

        for (ProductoDTO productoDTO : productos) {
            Optional<Producto> productoDisponible = productoRepo.findById(productoDTO.getId());

            if (productoDisponible.orElseThrow(() -> new Exception("Producto no existe"))
                    .getStock() > productoDTO.getCantidadCompra()) {
                Producto producto = new Producto();
                producto.setId(productoDTO.getId());
                producto.setNombre(productoDTO.getNombre());
                producto.setReferencia(productoDTO.getReferencia());
                producto.setPrecio(productoDTO.getPrecio());

                listaProductos.add(producto);
            }else{
                throw new Exception("No se encuentra en inventario la cantidad de productos que desea comprar");
            }

        }
        return listaProductos;
    }

    private void actualizarStock(List<ProductoDTO> productoDTOS){
        for(ProductoDTO productoDTO: productoDTOS){
            Optional<Producto> producto = productoRepo.findById(productoDTO.getId());

            int stockActual = producto.get().getStock();
            int compraProducto = productoDTO.getCantidadCompra();
            producto.get().setStock(stockActual - compraProducto);

            Producto productoActualizar = new Producto(
                    producto.get().getId(),
                    producto.get().getReferencia(),
                    producto.get().getNombre(),
                    producto.get().getPrecio(),
                    producto.get().getStock(),
                    producto.get().getCompras()
                    );
            productoRepo.save(productoActualizar);
        }
    }

    private BigDecimal calcularTotalCompra(List<ProductoDTO> productoDTOS){
        BigDecimal totalCompra = new BigDecimal(0);
        for(ProductoDTO productoDTO: productoDTOS){
            Optional<Producto> producto = productoRepo.findById(productoDTO.getId());

            BigDecimal precio = producto.get().getPrecio();
            BigDecimal cantidadCompra = BigDecimal.valueOf(productoDTO.getCantidadCompra());

            totalCompra = totalCompra.add(precio.multiply(cantidadCompra));

        }

        return totalCompra;
    }

}
