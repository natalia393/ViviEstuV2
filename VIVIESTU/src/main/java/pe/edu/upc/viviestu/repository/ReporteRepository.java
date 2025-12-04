package pe.edu.upc.viviestu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.viviestu.model.Reporte;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Integer> {
}
