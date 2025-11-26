package pe.edu.upc.viviestu.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.viviestu.dto.RecomendarDTO;
import pe.edu.upc.viviestu.dto.ZonaComentarioDTO;
import pe.edu.upc.viviestu.dto.ZonaDTO;
import pe.edu.upc.viviestu.dto.ZonaReporteDTO;
import pe.edu.upc.viviestu.model.Zona;
import pe.edu.upc.viviestu.service.ZonaService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/zona")
public class ZonaController {
    @Autowired
    private ZonaService service;

    @GetMapping("/todos")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USUARIO')")
    public List<ZonaDTO> listar(){
        return service.listAll().stream().map(a->{
            ModelMapper m=new ModelMapper();
            return m.map(a,ZonaDTO.class);
        }).collect(Collectors.toList());
    }
    @PostMapping("/nuevo")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void insertar(@RequestBody ZonaDTO dto) {
        ModelMapper m=new ModelMapper();
        Zona z=m.map(dto, Zona.class);
        service.insert(z);
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USUARIO')")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {
        Zona z = service.listId(id);
        if (z == null) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        ModelMapper m = new ModelMapper();
        ZonaDTO dto = m.map(z, ZonaDTO.class);
        return ResponseEntity.ok(dto);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {
        Zona z = service.listId(id);
        if (z == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe un registro con el ID: " + id);
        }
        service.delete(id);
        return ResponseEntity.ok("Registro con ID " + id + " eliminado correctamente.");
    }
    @PutMapping("/editar")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> modificar(@RequestBody ZonaDTO dto) {
        ModelMapper m = new ModelMapper();
        Zona z = m.map(dto, Zona.class);
        Zona existente = service.listId(z.getIdZona());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se puede modificar. No existe un registro con el ID: " + z.getIdZona());
        }
        service.edit(z);
        return ResponseEntity.ok("Registro con ID " + z.getIdZona() + " modificado correctamente.");
    }


    @PutMapping("/recomendacion")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<String> recomendar(@RequestBody RecomendarDTO dto) {
        if (dto.getIdZona() == null || dto.getRecomendado() == null) {
            return ResponseEntity.badRequest().body("idZona y recomendado son obligatorios.");
        }

        Zona existente = service.listId(dto.getIdZona());
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una zona con el ID: " + dto.getIdZona());
        }

        // Cambiar SOLO el campo necesario
        existente.setRecomendado(dto.getRecomendado());
        service.edit(existente);

        return ResponseEntity.ok("Zona " + dto.getIdZona() + " marcada como "
                + (dto.getRecomendado() ? "recomendada" : "no recomendada") + ".");
    }

    @GetMapping("/promedios-comentarios")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<List<ZonaComentarioDTO>> listarPromediosYComentarios() {
        List<ZonaComentarioDTO> lista = service.obtenerPromediosYComentarios();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/recomendadas")
    @PreAuthorize("hasAuthority('USUARIO')")
    public ResponseEntity<?> listarRecomendadasPorPreferencias(
            @RequestParam Long idUsuario) {

        List<ZonaReporteDTO> zonas = service.listarZonasQueCumplen(idUsuario);
        return ResponseEntity.ok(zonas);
    }
}
