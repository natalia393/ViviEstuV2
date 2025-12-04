package pe.edu.upc.viviestu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.viviestu.model.Reporte;
import pe.edu.upc.viviestu.repository.ReporteRepository;

import java.util.List;

@Service
public class ReporteService {
    @Autowired
    private ReporteRepository reporteRep;

    public List<Reporte> listAll() {
        return reporteRep.findAll();
    }

    public void insert(Reporte r) {
        reporteRep.save(r);
    }

    public Reporte listId(Integer idReporte) {
        return reporteRep.findById(idReporte).orElse(null);
    }

    public void delete(Integer idReporte) {
        reporteRep.deleteById(idReporte);
    }

    public void edit(Reporte r) {
        reporteRep.save(r);
    }
}
