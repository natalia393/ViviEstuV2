package pe.edu.upc.viviestu.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upc.viviestu.model.Zona;

import java.util.List;

public interface ZonaRepository extends JpaRepository<Zona, Integer> {

    @Query(value = "SELECT \n" +
            "    z.id_zona,\n" +
            "    z.nombre AS nombre_zona,\n" +
            "    AVG(ca.puntuacion) AS promedio_calificacion,\n" +
            "    co.comentario,\n" +
            "    co.fecha\n" +
            " FROM zonas z\n" +
            " LEFT JOIN calificaciones ca ON ca.id_zona = z.id_zona\n" +
            " LEFT JOIN comentarios co ON co.id_zona = z.id_zona\n" +
            " GROUP BY z.id_zona, z.nombre, co.comentario, co.fecha\n" +
            " ORDER BY z.id_zona ASC, co.fecha ASC ", nativeQuery = true)
    List<Object[]> obtenerPromediosYComentarios();

    @Query(value = """
        SELECT DISTINCT
            z.id_zona,
            z.nombre,
            z.precio_promedio,
            z.seguridad
        FROM zonas z
        WHERE
            LOWER(z.seguridad) IN ('media','alta')
            AND EXISTS (
                SELECT 1
                FROM preferencias p
                WHERE p.id_usuario = :idUsuario
                  AND p.universidad IS NOT NULL
                  AND p.presupuesto IS NOT NULL
                  AND z.precio_promedio <= p.presupuesto
            )
        ORDER BY z.nombre ASC
        """, nativeQuery = true)
    List<Object[]> listarZonasQueCumplen(@Param("idUsuario") Long idUsuario);
}
