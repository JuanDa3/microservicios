package co.com.uniquindio.entidades;

import co.com.uniquindio.enums.EnumCompra;
import co.com.uniquindio.enums.EnumMedioPago;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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
    private Date fecha;

    @Column(nullable = false)
    private String numeroFactura;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToMany(mappedBy = "compras")
    private List<Producto> productos;
}
