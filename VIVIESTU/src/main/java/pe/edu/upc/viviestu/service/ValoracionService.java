package pe.edu.upc.viviestu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.viviestu.model.Valoracion;
import pe.edu.upc.viviestu.repository.ValoracionRepository;

import java.util.List;

@Service
public class ValoracionService {
    @Autowired
    private ValoracionRepository valoracionRep;

    public List<Valoracion> listAll() {
        return valoracionRep.findAll();
    }

    public void insert(Valoracion v) {
        valoracionRep.save(v);
    }

    public Valoracion listId(Integer idValoracion) {
        return valoracionRep.findById(idValoracion).orElse(null);
    }

    public void delete(Integer idValoracion) {
        valoracionRep.deleteById(idValoracion);
    }

    public void edit(Valoracion v) {
        valoracionRep.save(v);
    }
}
