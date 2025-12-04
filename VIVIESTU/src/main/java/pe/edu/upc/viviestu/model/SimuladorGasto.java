package pe.edu.upc.viviestu.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "simulador_gasto")
public class SimuladorGasto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSimulador;

    private Double costoAlquiler;
    private Double costoTransporte;
    private Double costoTotalEstimado;

    private LocalDate fechaSimulacion;

    @ManyToOne
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "idZona")
    private Zona zona;

    public SimuladorGasto() {
    }

    public Integer getIdSimulador() {
        return idSimulador;
    }

    public void setIdSimulador(Integer idSimulador) {
        this.idSimulador = idSimulador;
    }

    public Double getCostoAlquiler() {
        return costoAlquiler;
    }

    public void setCostoAlquiler(Double costoAlquiler) {
        this.costoAlquiler = costoAlquiler;
    }

    public Double getCostoTransporte() {
        return costoTransporte;
    }

    public void setCostoTransporte(Double costoTransporte) {
        this.costoTransporte = costoTransporte;
    }

    public Double getCostoTotalEstimado() {
        return costoTotalEstimado;
    }

    public void setCostoTotalEstimado(Double costoTotalEstimado) {
        this.costoTotalEstimado = costoTotalEstimado;
    }

    public LocalDate getFechaSimulacion() {
        return fechaSimulacion;
    }

    public void setFechaSimulacion(LocalDate fechaSimulacion) {
        this.fechaSimulacion = fechaSimulacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }
}
