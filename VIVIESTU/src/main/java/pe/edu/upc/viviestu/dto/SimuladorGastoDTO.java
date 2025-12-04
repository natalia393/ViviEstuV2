package pe.edu.upc.viviestu.dto;

import pe.edu.upc.viviestu.model.Usuario;
import pe.edu.upc.viviestu.model.Zona;

import java.time.LocalDate;

public class SimuladorGastoDTO {
    private Integer idSimulador;
    private Double costoAlquiler;
    private Double costoTransporte;
    private Double costoTotalEstimado;
    private LocalDate fechaSimulacion;
    private Usuario usuario;
    private Zona zona;

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
