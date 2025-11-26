package pe.edu.upc.viviestu.dto;

public class ZonaDTO {
    private Integer idZona;
    private String nombreZona;
    private Double precioPromedio;
    private String seguridad; // ej "alta", "media", "baja"
    private String transporteDisponible; // ej "bus, carro, caminar"
    private Boolean recomendado; // ej "true" = Recomendado, "false" = Recomendar

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
