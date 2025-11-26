package pe.edu.upc.viviestu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.edu.upc.viviestu.model.Favorito;

public interface FavoritoRepository extends JpaRepository<Favorito, Integer> {
}
