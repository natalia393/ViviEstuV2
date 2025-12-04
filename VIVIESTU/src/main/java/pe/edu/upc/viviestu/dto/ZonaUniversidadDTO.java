package pe.edu.upc.viviestu.dto;

import pe.edu.upc.viviestu.model.Zona;

public class ZonaUniversidadDTO {
    private Integer idZonaUniversidad;
    private String universidad;
    private Integer distanciaMinutos;
    private Zona zona;

    public Integer getIdZonaUniversidad() {
        return idZonaUniversidad;
    }

    public void setIdZonaUniversidad(Integer idZonaUniversidad) {
        this.idZonaUniversidad = idZonaUniversidad;
    }

    public String getUniversidad() {
        return universidad;
    }

    public void setUniversidad(String universidad) {
        this.universidad = universidad;
    }

    public Integer getDistanciaMinutos() {
        return distanciaMinutos;
    }

    public void setDistanciaMinutos(Integer distanciaMinutos) {
        this.distanciaMinutos = distanciaMinutos;
    }

    public Zona getZona() {
        return zona;
    }

    public void setZona(Zona zona) {
        this.zona = zona;
    }
}
