package pe.edu.upc.viviestu.dto;

import pe.edu.upc.viviestu.model.Usuario;

import java.time.LocalDate;

public class ComparacionDTO {
    private Integer idComparacion;
    private LocalDate fecha;
    private Usuario usuario;

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
