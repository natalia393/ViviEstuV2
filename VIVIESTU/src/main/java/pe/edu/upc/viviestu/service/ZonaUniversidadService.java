package pe.edu.upc.viviestu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.viviestu.model.ZonaUniversidad;
import pe.edu.upc.viviestu.repository.ZonaUniversidadRepository;

import java.util.List;

@Service
public class ZonaUniversidadService {
    @Autowired
    private ZonaUniversidadRepository zonaUnivRep;

    public List<ZonaUniversidad> listAll() {
        return zonaUnivRep.findAll();
    }

    public void insert(ZonaUniversidad zu) {
        zonaUnivRep.save(zu);
    }

    public ZonaUniversidad listId(Integer idZonaUniversidad) {
        return zonaUnivRep.findById(idZonaUniversidad).orElse(null);
    }

    public void delete(Integer idZonaUniversidad) {
        zonaUnivRep.deleteById(idZonaUniversidad);
    }

    public void edit(ZonaUniversidad zu) {
        zonaUnivRep.save(zu);
    }
}
