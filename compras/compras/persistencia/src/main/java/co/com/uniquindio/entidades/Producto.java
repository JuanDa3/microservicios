package co.com.uniquindio.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Producto {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String referencia;
    private String nombre;
    private BigDecimal precio;
    @JsonIgnore
    private int stock;

    @JsonIgnore
    @ToString.Exclude
    @ManyToMany(mappedBy = "productos")
    private List<Compra> compras = new ArrayList<>();

}
