package pe.edu.upc.viviestu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.viviestu.model.Comparacion;
import pe.edu.upc.viviestu.repository.ComparacionRepository;

import java.util.List;

@Service
public class ComparacionService {
    @Autowired
    private ComparacionRepository comparacionRep;

    public List<Comparacion> listAll() {
        return comparacionRep.findAll();
    }

    public void insert(Comparacion c) {
        comparacionRep.save(c);
    }

    public Comparacion listId(Integer idComparacion) {
        return comparacionRep.findById(idComparacion).orElse(null);
    }

    public void delete(Integer idComparacion) {
        comparacionRep.deleteById(idComparacion);
    }

    public void edit(Comparacion c) {
        comparacionRep.save(c);
    }
}
