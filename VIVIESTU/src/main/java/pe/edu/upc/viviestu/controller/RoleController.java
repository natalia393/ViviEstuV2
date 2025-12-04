package pe.edu.upc.viviestu.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.viviestu.dto.RoleDTO;
import pe.edu.upc.viviestu.model.Role;
import pe.edu.upc.viviestu.service.RoleService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/roles")
public class RoleController {
    @Autowired
    private RoleService service;

    // GET ALL
    @GetMapping("/listas")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<RoleDTO> listar() {
        return service.listAll().stream().map(entity -> {
            ModelMapper m = new ModelMapper();
            return m.map(entity, RoleDTO.class);
        }).collect(Collectors.toList());
    }

    // INSERT
    @PostMapping("/nuevo")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void insertar(@RequestBody RoleDTO dto) {
        ModelMapper m = new ModelMapper();
        Role r = m.map(dto, Role.class);
        service.insert(r);
    }

    // GET BY ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {

        Role r = service.listId(id);

        if (r == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un rol con el ID: " + id);
        }

        ModelMapper m = new ModelMapper();
        RoleDTO dto = m.map(r, RoleDTO.class);

        return ResponseEntity.ok(dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {

        Role r = service.listId(id);

        if (r == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un rol con el ID: " + id);
        }

        service.delete(id);

        return ResponseEntity.ok("Rol con ID " + id + " eliminado correctamente.");
    }

    // UPDATE
    @PutMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> modificar(@RequestBody RoleDTO dto) {

        ModelMapper m = new ModelMapper();
        Role r = m.map(dto, Role.class);

        Role existente = service.listId(r.getId());

        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un rol con ID: " + r.getId());
        }

        service.edit(r);

        return ResponseEntity.ok("Rol con ID " + r.getId() + " modificado correctamente.");
    }
}
