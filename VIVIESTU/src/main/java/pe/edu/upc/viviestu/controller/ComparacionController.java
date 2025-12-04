package pe.edu.upc.viviestu.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.viviestu.dto.ComparacionDTO;
import pe.edu.upc.viviestu.model.Comparacion;
import pe.edu.upc.viviestu.model.Usuario;
import pe.edu.upc.viviestu.service.ComparacionService;
import pe.edu.upc.viviestu.service.UsuarioService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/comparaciones")
public class ComparacionController {
    @Autowired
    private ComparacionService service;

    @Autowired
    private UsuarioService usuarioService;

    // LISTAR TODO
    @GetMapping("/lista")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public List<ComparacionDTO> listar() {
        return service.listAll().stream().map(entity -> {
            ModelMapper m = new ModelMapper();
            return m.map(entity, ComparacionDTO.class);
        }).collect(Collectors.toList());
    }

    // NUEVO
    @PostMapping("/nuevo")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public ResponseEntity<Comparacion> insertar(@RequestBody ComparacionDTO dto) {

        Usuario u = usuarioService.listId(dto.getUsuario().getIdUsuario());
        if (u == null) {
            return ResponseEntity.badRequest().build();
        }

        ModelMapper m = new ModelMapper();
        Comparacion c = m.map(dto, Comparacion.class);
        c.setFecha(LocalDate.now());

        service.insert(c);

        return ResponseEntity.ok(c); // 👈 devolver JSON real con idComparacion
    }

    // LISTAR POR ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {

        Comparacion c = service.listId(id);

        if (c == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una comparación con el ID: " + id);
        }

        ModelMapper m = new ModelMapper();
        ComparacionDTO dto = m.map(c, ComparacionDTO.class);

        return ResponseEntity.ok(dto);
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {

        Comparacion c = service.listId(id);

        if (c == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una comparación con el ID: " + id);
        }

        service.delete(id);

        return ResponseEntity.ok("Comparación eliminada correctamente.");
    }

    // EDITAR
    @PutMapping("/editar")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public ResponseEntity<String> modificar(@RequestBody ComparacionDTO dto) {

        ModelMapper m = new ModelMapper();
        Comparacion c = m.map(dto, Comparacion.class);

        Comparacion existente = service.listId(c.getIdComparacion());

        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe una comparación con ese ID.");
        }

        // Mantener fecha original o actualizarla; tú decides
        c.setFecha(existente.getFecha());

        service.edit(c);

        return ResponseEntity.ok("Comparación modificada correctamente.");
    }
}
