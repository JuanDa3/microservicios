package co.com.uniquindio.entidades;

import co.com.uniquindio.enums.EnumCompra;
import co.com.uniquindio.enums.EnumMedioPago;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private double totalCompra;

    @Column(nullable = false)
    private EnumMedioPago medioPago;

    @Column(nullable = false)
    private EnumCompra estado;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private String numeroFactura;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "productos_compra",
            joinColumns = @JoinColumn(name = "FK_PRODUCTO", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "FK_COMPRA", nullable = false)
    )
    private List<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto producto) {
        productos.add(producto);
        producto.getCompras().add(this);
    }

    public void removerProducto(Producto producto) {
        productos.remove(producto);
        producto.getCompras().remove(this);
    }
}
