package co.com.uniquindio.respuestas;

import co.com.uniquindio.dto.ProductoDTO;
import co.com.uniquindio.entidades.Producto;
import co.com.uniquindio.enums.EnumCompra;
import co.com.uniquindio.enums.EnumMedioPago;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Setter
@AllArgsConstructor
@ToString
@Builder
public class HistorialCompraRespuesta {
    @JsonProperty("total_compra")
    private BigDecimal totalCompra;

    @JsonProperty("medio_pago")
    private EnumMedioPago medioPago;

    @JsonProperty("estado_compra")
    private EnumCompra estado;

    @JsonProperty("fecha_compra")
    private LocalDate fecha;

    @JsonProperty("numero_factura")
    private String numeroFactura;

    @JsonProperty("correo_usuario")
    private String correoUsuario;

    @JsonProperty("productos_compra")
    private List<Producto> productos;

}
