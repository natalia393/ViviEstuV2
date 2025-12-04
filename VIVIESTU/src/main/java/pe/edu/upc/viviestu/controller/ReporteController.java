package pe.edu.upc.viviestu.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.viviestu.dto.ReporteDTO;
import pe.edu.upc.viviestu.model.Reporte;
import pe.edu.upc.viviestu.model.Usuario;
import pe.edu.upc.viviestu.service.ReporteService;
import pe.edu.upc.viviestu.service.UsuarioService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reportes")
public class ReporteController {
    @Autowired
    private ReporteService service;

    @Autowired
    private UsuarioService usuarioService;

    // LISTAR TODO
    @GetMapping("/lista")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public List<ReporteDTO> listar() {
        return service.listAll().stream().map(entity -> {
            ModelMapper m = new ModelMapper();
            return m.map(entity, ReporteDTO.class);
        }).collect(Collectors.toList());
    }

    // NUEVO
    @PostMapping("/nuevo")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public ResponseEntity<String> insertar(@RequestBody ReporteDTO dto) {

        // Validar usuario
        Usuario u = usuarioService.listId(dto.getUsuario().getIdUsuario());
        if (u == null) {
            return ResponseEntity.badRequest().body("El usuario no existe.");
        }

        ModelMapper m = new ModelMapper();
        Reporte r = m.map(dto, Reporte.class);

        // Registrar fecha actual
        r.setFechaGenerado(LocalDate.now());

        service.insert(r);

        return ResponseEntity.ok("Reporte generado correctamente.");
    }

    // LISTAR POR ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {

        Reporte r = service.listId(id);

        if (r == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un reporte con el ID: " + id);
        }

        ModelMapper m = new ModelMapper();
        ReporteDTO dto = m.map(r, ReporteDTO.class);

        return ResponseEntity.ok(dto);
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {

        Reporte r = service.listId(id);

        if (r == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un reporte con el ID: " + id);
        }

        service.delete(id);

        return ResponseEntity.ok("Reporte eliminado correctamente.");
    }

    // EDITAR
    @PutMapping("/editar")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public ResponseEntity<String> modificar(@RequestBody ReporteDTO dto) {

        ModelMapper m = new ModelMapper();
        Reporte r = m.map(dto, Reporte.class);

        Reporte existente = service.listId(r.getIdReporte());

        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un reporte con ese ID.");
        }

        // Mantener fecha generada original
        r.setFechaGenerado(existente.getFechaGenerado());

        service.edit(r);

        return ResponseEntity.ok("Reporte actualizado correctamente.");
    }
}
