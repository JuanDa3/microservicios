package co.com.uniquindio.dto;

import co.com.uniquindio.enums.EnumMedioPago;
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
