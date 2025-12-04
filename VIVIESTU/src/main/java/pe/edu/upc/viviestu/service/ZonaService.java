package pe.edu.upc.viviestu.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.viviestu.model.Zona;
import pe.edu.upc.viviestu.repository.ZonaRepository;

import java.util.List;

@Service
public class ZonaService {
    @Autowired
    private ZonaRepository zonaRep;

    public List<Zona> listAll() {
        return zonaRep.findAll();
    }

    public void insert(Zona z) {
        zonaRep.save(z);
    }

    public Zona listId(Integer idZona) {
        return zonaRep.findById(idZona).orElse(null);
    }

    public void delete(Integer idZona) {
        zonaRep.deleteById(idZona);
    }

    public void edit(Zona z) {
        zonaRep.save(z);
    }



}
