package co.com.uniquindio.respuestas;

import co.com.uniquindio.enums.EnumCompra;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.ToString;

@Setter
@AllArgsConstructor
@ToString
@Builder
public class EstadoCompraRespuesta {
    @JsonProperty("estado_compra")
    private EnumCompra estado;
}
