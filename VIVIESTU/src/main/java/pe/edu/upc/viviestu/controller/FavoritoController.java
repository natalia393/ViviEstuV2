package pe.edu.upc.viviestu.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.viviestu.dto.FavoritoDTO;
import pe.edu.upc.viviestu.model.Favorito;
import pe.edu.upc.viviestu.service.FavoritoService;

@RestController
@RequestMapping("/favorito")
public class FavoritoController {
    @Autowired
    private FavoritoService service;

    @PostMapping("/nuevo")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USUARIO')")
    public void insertar(@RequestBody FavoritoDTO dto) {
        ModelMapper m=new ModelMapper();
        Favorito u=m.map(dto, Favorito.class);
        service.insert(u);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USUARIO')")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Favorito f = service.listId(id);
        if (f == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        service.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }
}
