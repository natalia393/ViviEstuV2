package pe.edu.upc.viviestu.model;

import jakarta.persistence.*;

@Entity
@Table(name = "zona_universidad")
public class ZonaUniversidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idZonaUniversidad;

    @Column(nullable = false)
    private String universidad;

    private Integer distanciaMinutos;

    @ManyToOne
    @JoinColumn(name = "idZona")
    private Zona zona;

    public ZonaUniversidad() {
    }

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
