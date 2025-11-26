package pe.edu.upc.viviestu.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.viviestu.dto.UsuarioDTO;
import pe.edu.upc.viviestu.model.Usuario;
import pe.edu.upc.viviestu.service.UsuarioService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping("/todos")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USUARIO')")
    public List<UsuarioDTO> listar(){
        return service.listAll().stream().map(a->{
            ModelMapper m=new ModelMapper();
            return m.map(a,UsuarioDTO.class);
        }).collect(Collectors.toList());
    }
    @PostMapping("/nuevo")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void insertar(@RequestBody UsuarioDTO dto) {
        ModelMapper m=new ModelMapper();
        Usuario u=m.map(dto, Usuario.class);
        service.insert(u);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USUARIO')")
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
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USUARIO')")
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
