package pe.edu.upc.viviestu.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.viviestu.dto.ComparacionDetalleDTO;
import pe.edu.upc.viviestu.model.Comparacion;
import pe.edu.upc.viviestu.model.ComparacionDetalle;
import pe.edu.upc.viviestu.model.Zona;
import pe.edu.upc.viviestu.service.ComparacionDetalleService;
import pe.edu.upc.viviestu.service.ComparacionService;
import pe.edu.upc.viviestu.service.ZonaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comparacion-detalle")
public class ComparacionDetalleController {
    @Autowired
    private ComparacionDetalleService service;

    @Autowired
    private ComparacionService comparacionService;

    @Autowired
    private ZonaService zonaService;

    // LISTAR TODO
    @GetMapping("/lista")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public List<ComparacionDetalleDTO> listar() {
        return service.listAll().stream().map(entity -> {
            ModelMapper m = new ModelMapper();
            return m.map(entity, ComparacionDetalleDTO.class);
        }).collect(Collectors.toList());
    }

    // NUEVO
    @PostMapping("/nuevo")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public ResponseEntity<String> insertar(@RequestBody ComparacionDetalleDTO dto) {

        // Validar comparación existente
        Comparacion c = comparacionService.listId(dto.getComparacion().getIdComparacion());
        if (c == null) {
            return ResponseEntity.badRequest().body("La comparación no existe.");
        }

        // Validar zona existente
        Zona z = zonaService.listId(dto.getZona().getIdZona());
        if (z == null) {
            return ResponseEntity.badRequest().body("La zona no existe.");
        }

        ModelMapper m = new ModelMapper();
        ComparacionDetalle cd = m.map(dto, ComparacionDetalle.class);

        service.insert(cd);

        return ResponseEntity.ok("Zona agregada a la comparación.");
    }

    // LISTAR POR ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {

        ComparacionDetalle cd = service.listId(id);

        if (cd == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un detalle con el ID: " + id);
        }

        ModelMapper m = new ModelMapper();
        ComparacionDetalleDTO dto = m.map(cd, ComparacionDetalleDTO.class);

        return ResponseEntity.ok(dto);
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {

        ComparacionDetalle cd = service.listId(id);

        if (cd == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un detalle con el ID: " + id);
        }

        service.delete(id);

        return ResponseEntity.ok("Detalle eliminado correctamente.");
    }

    // EDITAR
    @PutMapping("/editar")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public ResponseEntity<String> modificar(@RequestBody ComparacionDetalleDTO dto) {

        ModelMapper m = new ModelMapper();
        ComparacionDetalle cd = m.map(dto, ComparacionDetalle.class);

        ComparacionDetalle existente = service.listId(cd.getIdCompDetalle());

        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un detalle con ese ID.");
        }

        // Normalmente no se edita, pero lo dejamos por uniformidad
        service.edit(cd);

        return ResponseEntity.ok("Detalle actualizado correctamente.");
    }
}
