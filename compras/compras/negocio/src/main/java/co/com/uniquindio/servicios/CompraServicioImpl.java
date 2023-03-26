package co.com.uniquindio.servicios;

import co.com.uniquindio.entidades.Compra;
import co.com.uniquindio.repositorios.CompraRepo;
import org.springframework.stereotype.Repository;

@Repository
public class CompraServicioImpl implements CompraServicio {

    private final CompraRepo compraRepo;

    public CompraServicioImpl(CompraRepo compraRepo) {
        this.compraRepo = compraRepo;
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
    public String eliminarCompra(Integer id) throws Exception {
        compraRepo.deleteById(id);
        return "Compra eliminada exitosamente";
    }
}
