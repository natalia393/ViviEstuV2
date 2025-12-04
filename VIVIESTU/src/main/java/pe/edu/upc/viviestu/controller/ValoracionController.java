package pe.edu.upc.viviestu.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.viviestu.dto.ValoracionDTO;
import pe.edu.upc.viviestu.model.Usuario;
import pe.edu.upc.viviestu.model.Valoracion;
import pe.edu.upc.viviestu.model.Zona;
import pe.edu.upc.viviestu.service.UsuarioService;
import pe.edu.upc.viviestu.service.ValoracionService;
import pe.edu.upc.viviestu.service.ZonaService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/valoraciones")
public class ValoracionController {
    @Autowired
    private ValoracionService service;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ZonaService zonaService;

    // LISTAR TODO
    @GetMapping("/lista")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public List<ValoracionDTO> listar() {
        return service.listAll().stream().map(entity -> {
            ModelMapper m = new ModelMapper();
            return m.map(entity, ValoracionDTO.class);
        }).collect(Collectors.toList());
    }

    // NUEVO
    @PostMapping("/nuevo")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public ResponseEntity<String> insertar(@RequestBody ValoracionDTO dto) {

        // Validar usuario
        Usuario u = usuarioService.listId(dto.getUsuario().getIdUsuario());
        if (u == null) {
            return ResponseEntity.badRequest().body("El usuario no existe.");
        }

        // Validar zona
        Zona z = zonaService.listId(dto.getZona().getIdZona());
        if (z == null) {
            return ResponseEntity.badRequest().body("La zona no existe.");
        }

        ModelMapper m = new ModelMapper();
        Valoracion v = m.map(dto, Valoracion.class);

        // Registrar fecha actual
        v.setFecha(LocalDate.now());

        service.insert(v);

        return ResponseEntity.ok("Valoración registrada correctamente.");
    }

    // LISTAR POR ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {

        Valoracion v = service.listId(id);

        if (v == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una valoración con el ID: " + id);
        }

        ModelMapper m = new ModelMapper();
        ValoracionDTO dto = m.map(v, ValoracionDTO.class);

        return ResponseEntity.ok(dto);
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {

        Valoracion v = service.listId(id);

        if (v == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una valoración con el ID: " + id);
        }

        service.delete(id);

        return ResponseEntity.ok("Valoración eliminada correctamente.");
    }

    // EDITAR
    @PutMapping("/editar")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public ResponseEntity<String> modificar(@RequestBody ValoracionDTO dto) {

        ModelMapper m = new ModelMapper();
        Valoracion v = m.map(dto, Valoracion.class);

        Valoracion existente = service.listId(v.getIdValoracion());

        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una valoración con ese ID.");
        }

        // Mantenemos la fecha original
        v.setFecha(existente.getFecha());

        service.edit(v);

        return ResponseEntity.ok("Valoración actualizada correctamente.");
    }
}
