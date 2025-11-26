package pe.edu.upc.viviestu.model;

import jakarta.persistence.*;

@Entity
@Table(name = "zonas")
public class Zona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idZona;

    @Column(name = "nombreZona", nullable = false)
    private String nombreZona;
    @Column(name = "precioPromedio", nullable = false)
    private Double precioPromedio;
    @Column(name = "seguridad", nullable = false)
    private String seguridad; // ej "alta", "media", "baja"
    @Column(name = "transporteDisponible", nullable = false)
    private String transporteDisponible; // ej "bus, carro, caminar"
    @Column(name = "recomendado", nullable = false)
    private Boolean recomendado; // ej "true" = Recomendado, "false" = Recomendar

    public Zona() {
    }

    public Integer getIdZona() {
        return idZona;
    }

    public void setIdZona(Integer idZona) {
        this.idZona = idZona;
    }

    public String getNombreZona() {
        return nombreZona;
    }

    public void setNombreZona(String nombreZona) {
        this.nombreZona = nombreZona;
    }

    public Double getPrecioPromedio() {
        return precioPromedio;
    }

    public void setPrecioPromedio(Double precioPromedio) {
        this.precioPromedio = precioPromedio;
    }

    public String getSeguridad() {
        return seguridad;
    }

    public void setSeguridad(String seguridad) {
        this.seguridad = seguridad;
    }

    public String getTransporteDisponible() {
        return transporteDisponible;
    }

    public void setTransporteDisponible(String transporteDisponible) {
        this.transporteDisponible = transporteDisponible;
    }

    public Boolean getRecomendado() {
        return recomendado;
    }

    public void setRecomendado(Boolean recomendado) {
        this.recomendado = recomendado;
    }
}
