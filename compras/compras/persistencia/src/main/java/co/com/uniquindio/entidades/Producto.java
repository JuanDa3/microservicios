package co.com.uniquindio.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String referencia;
    private String nombre;
    private double precio;
    private int stock;

    @JoinTable(
            name = "productos_compra",
            joinColumns = @JoinColumn(name = "FK_PRODUCTO", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "FK_COMPRA", nullable = false)
    )
    @ManyToMany
    private List<Compra> compras;


}
