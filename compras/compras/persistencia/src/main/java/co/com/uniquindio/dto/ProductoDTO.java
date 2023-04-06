package co.com.uniquindio.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductoDTO {

    private Integer id;
    private String referencia;
    private String nombre;
    private double precio;
    private int cantidadCompra;
}
