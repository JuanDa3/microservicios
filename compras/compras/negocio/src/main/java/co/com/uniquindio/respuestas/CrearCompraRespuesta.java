package co.com.uniquindio.respuestas;

import co.com.uniquindio.dto.ProductoDTO;
import co.com.uniquindio.enums.EnumCompra;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Setter
@AllArgsConstructor
@ToString
@Builder
public class CrearCompraRespuesta implements Serializable {
    @JsonProperty("total_compra")
    private BigDecimal totalCompra;
    @JsonProperty("estado_compra")
    private EnumCompra estado;
    @JsonProperty("fecha_compra")
    private Date fecha;
    @JsonProperty("numero_factura")
    private String numeroFactura;
    @JsonProperty("correo_usuario")
    private String correoUsuario;
    @JsonProperty("productos_compra")
    private List<ProductoDTO> productos;
}
