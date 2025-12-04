package pe.edu.upc.viviestu.dto;

import pe.edu.upc.viviestu.model.Usuario;
import pe.edu.upc.viviestu.model.Zona;

import java.time.LocalDate;

public class ValoracionDTO {
    private Integer idValoracion;
    private Integer puntajeSeguridad;
    private Integer puntajeAcceso;
    private Integer puntajeServicios;
    private String comentario;
    private LocalDate fecha;
    private Usuario usuario;
    private Zona zona;

    public Integer getIdValoracion() {
        return idValoracion;
    }

    public void setIdValoracion(Integer idValoracion) {
        this.idValoracion = idValoracion;
    }

    public Integer getPuntajeSeguridad() {
        return puntajeSeguridad;
    }

    public void setPuntajeSeguridad(Integer puntajeSeguridad) {
        this.puntajeSeguridad = puntajeSeguridad;
    }

    public Integer getPuntajeAcceso() {
        return puntajeAcceso;
    }

    public void setPuntajeAcceso(Integer puntajeAcceso) {
        this.puntajeAcceso = puntajeAcceso;
    }

    public Integer getPuntajeServicios() {
        return puntajeServicios;
    }

    public void setPuntajeServicios(Integer puntajeServicios) {
        this.puntajeServicios = puntajeServicios;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }
}
