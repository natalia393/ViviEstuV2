package pe.edu.upc.viviestu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.viviestu.model.Favorito;
import pe.edu.upc.viviestu.repository.FavoritoRepository;

import java.util.List;

@Service
public class FavoritoService {
    @Autowired
    private FavoritoRepository favoritoRep;

    public List<Favorito> listAll(){
        return favoritoRep.findAll();
    }
    public void insert(Favorito f) {
        favoritoRep.save(f);
    }
    public Favorito listId(int idFavorito) {
        return favoritoRep.findById(idFavorito).orElse(null);
    }
    public void delete(int idFavorito) {
        favoritoRep.deleteById(idFavorito);
    }
    public void edit(Favorito f) {
        favoritoRep.save(f);
    }
}
