package pe.edu.upc.viviestu.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comparaciones")
public class Comparacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idComparacion;

    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "comparacion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"comparacion"})
    private List<ComparacionDetalle> detalles = new ArrayList<>();

    public Comparacion() {
    }

    public Integer getIdComparacion() {
        return idComparacion;
    }

    public void setIdComparacion(Integer idComparacion) {
        this.idComparacion = idComparacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
