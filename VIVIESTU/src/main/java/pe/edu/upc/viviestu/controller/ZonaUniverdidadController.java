package pe.edu.upc.viviestu.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.viviestu.dto.ZonaUniversidadDTO;
import pe.edu.upc.viviestu.model.ZonaUniversidad;
import pe.edu.upc.viviestu.service.ZonaUniversidadService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/zona-universidad")
public class ZonaUniverdidadController {
    @Autowired
    private ZonaUniversidadService service;

    // LISTAR TODO (ADMIN - USUARIO)
    @GetMapping("/lista")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public List<ZonaUniversidadDTO> listar() {
        return service.listAll().stream().map(entity -> {
            ModelMapper m = new ModelMapper();
            return m.map(entity, ZonaUniversidadDTO.class);
        }).collect(Collectors.toList());
    }

    // INSERTAR (solo ADMIN)
    @PostMapping("/nuevo")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void insertar(@RequestBody ZonaUniversidadDTO dto) {
        ModelMapper m = new ModelMapper();
        ZonaUniversidad zu = m.map(dto, ZonaUniversidad.class);
        service.insert(zu);
    }

    // LISTAR POR ID (ADMIN y USUARIO)
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {

        ZonaUniversidad zu = service.listId(id);

        if (zu == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }

        ModelMapper m = new ModelMapper();
        ZonaUniversidadDTO dto = m.map(zu, ZonaUniversidadDTO.class);

        return ResponseEntity.ok(dto);
    }

    // ELIMINAR (solo ADMIN)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {

        ZonaUniversidad zu = service.listId(id);

        if (zu == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }

        service.delete(id);

        return ResponseEntity.ok("ZonaUniversidad con ID " + id + " eliminada correctamente.");
    }

    // MODIFICAR (solo ADMIN)
    @PutMapping("/editar")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> modificar(@RequestBody ZonaUniversidadDTO dto) {

        ModelMapper m = new ModelMapper();
        ZonaUniversidad zu = m.map(dto, ZonaUniversidad.class);

        ZonaUniversidad existente = service.listId(zu.getIdZonaUniversidad());

        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con ID: " + zu.getIdZonaUniversidad());
        }

        service.edit(zu);

        return ResponseEntity.ok("ZonaUniversidad con ID " + zu.getIdZonaUniversidad()
                + " modificado correctamente.");
    }
}
