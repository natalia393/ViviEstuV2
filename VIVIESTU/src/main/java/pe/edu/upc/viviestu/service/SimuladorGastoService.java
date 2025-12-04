package pe.edu.upc.viviestu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.viviestu.model.SimuladorGasto;
import pe.edu.upc.viviestu.repository.SimuladorGastoRepository;

import java.util.List;

@Service
public class SimuladorGastoService {
    @Autowired
    private SimuladorGastoRepository simuladorRep;

    public List<SimuladorGasto> listAll() {
        return simuladorRep.findAll();
    }

    public void insert(SimuladorGasto s) {
        simuladorRep.save(s);
    }

    public SimuladorGasto listId(Integer idSimulador) {
        return simuladorRep.findById(idSimulador).orElse(null);
    }

    public void delete(Integer idSimulador) {
        simuladorRep.deleteById(idSimulador);
    }

    public void edit(SimuladorGasto s) {
        simuladorRep.save(s);
    }
}
