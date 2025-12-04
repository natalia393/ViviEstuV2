package pe.edu.upc.viviestu.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.viviestu.dto.FavoritoDTO;
import pe.edu.upc.viviestu.dto.ZonaDTO;
import pe.edu.upc.viviestu.model.Favorito;
import pe.edu.upc.viviestu.model.Usuario;
import pe.edu.upc.viviestu.model.Zona;
import pe.edu.upc.viviestu.service.FavoritoService;
import pe.edu.upc.viviestu.service.UsuarioService;
import pe.edu.upc.viviestu.service.ZonaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/favoritos")
public class FavoritoController {
    @Autowired
    private FavoritoService service;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ZonaService zonaService;

    @GetMapping("/lista")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public List<ZonaDTO> listarZonasFavoritas() {

        return service.listAll().stream()
                .map(fav -> {
                    ModelMapper m = new ModelMapper();
                    return m.map(fav.getZona(), ZonaDTO.class); // devolvemos solo ZONA
                })
                .collect(Collectors.toList());
    }

    // NUEVO FAVORITO (USUARIO)
    @PostMapping("/nuevo")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public ResponseEntity<String> insertar(@RequestBody FavoritoDTO dto) {

        // Validar que usuario exista
        Usuario u = usuarioService.listId(dto.getUsuario().getIdUsuario());
        if (u == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El usuario no existe.");
        }

        // Validar que zona exista
        Zona z = zonaService.listId(dto.getZona().getIdZona());
        if (z == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("La zona no existe.");
        }

        ModelMapper m = new ModelMapper();
        Favorito f = m.map(dto, Favorito.class);

        service.insert(f);

        return ResponseEntity.ok("Zona agregada a favoritos.");
    }

    // OBTENER POR ID (USUARIO Y ADMIN)
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {

        Favorito f = service.listId(id);

        if (f == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un favorito con el ID: " + id);
        }

        ModelMapper m = new ModelMapper();
        FavoritoDTO dto = m.map(f, FavoritoDTO.class);

        return ResponseEntity.ok(dto);
    }

    // ELIMINAR (USUARIO Y ADMIN)
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {

        Favorito f = service.listId(id);

        if (f == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un favorito con el ID: " + id);
        }

        service.delete(id);

        return ResponseEntity.ok("Favorito eliminado correctamente.");
    }

}
