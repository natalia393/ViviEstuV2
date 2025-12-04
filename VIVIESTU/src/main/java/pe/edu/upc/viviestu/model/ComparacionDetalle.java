package pe.edu.upc.viviestu.model;

import jakarta.persistence.*;

@Entity
@Table(name = "comparacion_detalle")
public class ComparacionDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCompDetalle;

    @ManyToOne
    @JoinColumn(name = "idComparacion")
    private Comparacion comparacion;

    @ManyToOne
    @JoinColumn(name = "idZona")
    private Zona zona;

    public ComparacionDetalle() {
    }

    public Integer getIdCompDetalle() {
        return idCompDetalle;
    }

    public void setIdCompDetalle(Integer idCompDetalle) {
        this.idCompDetalle = idCompDetalle;
    }

    public Comparacion getComparacion() {
        return comparacion;
    }

    public void setComparacion(Comparacion comparacion) {
        this.comparacion = comparacion;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }
}
