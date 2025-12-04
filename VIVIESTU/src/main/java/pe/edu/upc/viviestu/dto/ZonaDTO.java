package pe.edu.upc.viviestu.dto;

public class ZonaDTO {
    private Integer idZona;
    private String nombreZona;
    private String distrito;
    private Double precioPromedio;
    private Integer nivelSeguridad;
    private String accesibilidadTransporte;
    private String serviciosCercanos;
    private String descripcion;
    private Double latitud;
    private Double longitud;
    private String imagenUrl;

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

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public Double getPrecioPromedio() {
        return precioPromedio;
    }

    public void setPrecioPromedio(Double precioPromedio) {
        this.precioPromedio = precioPromedio;
    }

    public Integer getNivelSeguridad() {
        return nivelSeguridad;
    }

    public void setNivelSeguridad(Integer nivelSeguridad) {
        this.nivelSeguridad = nivelSeguridad;
    }

    public String getAccesibilidadTransporte() {
        return accesibilidadTransporte;
    }

    public void setAccesibilidadTransporte(String accesibilidadTransporte) {
        this.accesibilidadTransporte = accesibilidadTransporte;
    }

    public String getServiciosCercanos() {
        return serviciosCercanos;
    }

    public void setServiciosCercanos(String serviciosCercanos) {
        this.serviciosCercanos = serviciosCercanos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }
}
