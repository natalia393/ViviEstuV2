package pe.edu.upc.viviestu.dto;

import pe.edu.upc.viviestu.model.Comparacion;
import pe.edu.upc.viviestu.model.Zona;

import java.util.List;

public class ComparacionDetalleDTO {
    private Integer idCompDetalle;
    private Comparacion comparacion;
    private Zona zona;
    private List<ComparacionDetalleDTO> detalles;

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
