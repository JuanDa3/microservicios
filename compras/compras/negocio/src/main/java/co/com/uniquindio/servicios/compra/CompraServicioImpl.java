package co.com.uniquindio.servicios.compra;

import co.com.uniquindio.entidades.Compra;
import co.com.uniquindio.enums.EnumCompra;
import co.com.uniquindio.repositorios.CompraRepo;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public String crearCompra(Compra compra) throws Exception {
        if(compra != null){
            compraRepo.save(compra);
        }else {
            throw new  Exception("No se puede guardar como nulo");
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
        Optional<Compra>compraActual = compraRepo.findById(id);
        if(compraActual.orElseThrow(() -> new Exception("la compra no existe")).getEstado().equals(EnumCompra.CANCELADO)){
            throw new Exception("La compra ya se encuentra cancelada");
        }
        compraActual.get().setEstado(EnumCompra.CANCELADO);
        return compraActual;
    }

}
