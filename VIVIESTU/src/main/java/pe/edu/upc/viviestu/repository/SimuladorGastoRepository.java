package pe.edu.upc.viviestu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.viviestu.model.SimuladorGasto;

@Repository
public interface SimuladorGastoRepository extends JpaRepository<SimuladorGasto, Integer> {
}
