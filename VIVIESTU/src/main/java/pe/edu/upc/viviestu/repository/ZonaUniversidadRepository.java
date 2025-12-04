package pe.edu.upc.viviestu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.viviestu.model.ZonaUniversidad;

@Repository
public interface ZonaUniversidadRepository extends JpaRepository<ZonaUniversidad, Integer> {
}
