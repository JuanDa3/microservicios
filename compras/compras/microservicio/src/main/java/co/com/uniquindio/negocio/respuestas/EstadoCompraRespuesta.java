package co.com.uniquindio.negocio.respuestas;

import co.com.uniquindio.persistencia.enums.EnumCompra;
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
