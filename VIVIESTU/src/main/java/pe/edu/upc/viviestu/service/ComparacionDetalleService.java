package pe.edu.upc.viviestu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.viviestu.model.ComparacionDetalle;
import pe.edu.upc.viviestu.repository.ComparacionDetalleRepository;

import java.util.List;

@Service
public class ComparacionDetalleService {
    @Autowired
    private ComparacionDetalleRepository compDetalleRep;

    public List<ComparacionDetalle> listAll() {
        return compDetalleRep.findAll();
    }

    public void insert(ComparacionDetalle cd) {
        compDetalleRep.save(cd);
    }

    public ComparacionDetalle listId(Integer idCompDetalle) {
        return compDetalleRep.findById(idCompDetalle).orElse(null);
    }

    public void delete(Integer idCompDetalle) {
        compDetalleRep.deleteById(idCompDetalle);
    }

    public void edit(ComparacionDetalle cd) {
        compDetalleRep.save(cd);
    }
}
