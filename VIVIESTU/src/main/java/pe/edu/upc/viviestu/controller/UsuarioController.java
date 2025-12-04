package pe.edu.upc.viviestu.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.viviestu.dto.RegistroPublicoDTO;
import pe.edu.upc.viviestu.dto.UsuarioDTO;
import pe.edu.upc.viviestu.model.Role;
import pe.edu.upc.viviestu.model.Usuario;
import pe.edu.upc.viviestu.service.RoleService;
import pe.edu.upc.viviestu.service.UsuarioService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService service;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // GET ALL
    @GetMapping("/listas")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USUARIO')")
    public List<UsuarioDTO> listar() {
        return service.listAll().stream().map(entity -> {
            ModelMapper m = new ModelMapper();
            return m.map(entity, UsuarioDTO.class);
        }).collect(Collectors.toList());
    }

    @PostMapping("/registro")
    public ResponseEntity<String> registrar(@RequestBody RegistroPublicoDTO dto) {

        // 1) Validar correo ya registrado
        if (service.existeCorreo(dto.getCorreo())) {
            return ResponseEntity.badRequest().body("El correo ya está registrado.");
        }

        // 2) Mapear DTO → Usuario
        ModelMapper m = new ModelMapper();
        Usuario u = m.map(dto, Usuario.class);

        u.setEstado(true);
        u.setFechaRegistro(LocalDate.now());

        // 3) Encriptar contraseña
        u.setPassword(passwordEncoder.encode(dto.getPassword()));

        // 4) Buscar rol "USUARIO" desde BD
        Role rolUsuario = roleService.buscarPorNombre("USUARIO");
        if (rolUsuario == null) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error: No existe el rol USUARIO en la base de datos.");
        }

        // 5) Asignar rol al usuario
        u.setRole(rolUsuario);

        // 6) Guardar usuario
        service.insert(u);

        return ResponseEntity.ok("Registro exitoso.");
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Usuario u = service.listId(id);
        if (u == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        UsuarioDTO dto = m.map(u, UsuarioDTO.class);
        return ResponseEntity.ok(dto);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Usuario u = service.listId(id);
        if (u == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        service.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }
    @PutMapping("/editar")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> modificar(@RequestBody UsuarioDTO dto) {
        ModelMapper m = new ModelMapper();
        Usuario u = m.map(dto, Usuario.class);
        Usuario existente = service.listId(u.getIdUsuario());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + u.getIdUsuario());
        }
        service.edit(u);
        return ResponseEntity.ok("Registro con ID " + u.getIdUsuario() + " modificado correctamente.");
    }

}
