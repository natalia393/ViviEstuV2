package pe.edu.upc.viviestu.dto;

import pe.edu.upc.viviestu.model.Usuario;
import pe.edu.upc.viviestu.model.Zona;

public class FavoritoDTO {
    private Integer idFavorito;
    private Usuario usuario;
    private Zona zona;

    public Integer getIdFavorito() {
        return idFavorito;
    }

    public void setIdFavorito(Integer idFavorito) {
        this.idFavorito = idFavorito;
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
