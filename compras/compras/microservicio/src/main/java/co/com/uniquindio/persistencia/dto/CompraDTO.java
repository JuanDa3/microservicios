package co.com.uniquindio.persistencia.dto;

import co.com.uniquindio.persistencia.enums.EnumMedioPago;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompraDTO {

    private EnumMedioPago medioPago;

    private UsuarioDTO usuario;

    private List<ProductoDTO>productos;
}
