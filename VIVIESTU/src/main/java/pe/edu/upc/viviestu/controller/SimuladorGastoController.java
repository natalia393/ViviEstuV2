package pe.edu.upc.viviestu.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.viviestu.dto.SimuladorGastoDTO;
import pe.edu.upc.viviestu.model.SimuladorGasto;
import pe.edu.upc.viviestu.model.Usuario;
import pe.edu.upc.viviestu.model.Zona;
import pe.edu.upc.viviestu.service.SimuladorGastoService;
import pe.edu.upc.viviestu.service.UsuarioService;
import pe.edu.upc.viviestu.service.ZonaService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/simulador")
public class SimuladorGastoController {
    @Autowired
    private SimuladorGastoService service;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ZonaService zonaService;

    // LISTAR TODO
    @GetMapping("/lista")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public List<SimuladorGastoDTO> listar() {
        return service.listAll().stream().map(entity -> {
            ModelMapper m = new ModelMapper();
            return m.map(entity, SimuladorGastoDTO.class);
        }).collect(Collectors.toList());
    }

    // NUEVO
    @PostMapping("/nuevo")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public ResponseEntity<String> insertar(@RequestBody SimuladorGastoDTO dto) {

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
        SimuladorGasto s = m.map(dto, SimuladorGasto.class);

        // Calcular costo total
        s.setCostoTotalEstimado(s.getCostoAlquiler() + s.getCostoTransporte());

        // Registrar fecha
        s.setFechaSimulacion(LocalDate.now());

        service.insert(s);

        return ResponseEntity.ok("Simulación creada correctamente.");
    }

    // LISTAR POR ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public ResponseEntity<?> listarId(@PathVariable("id") Integer id) {

        SimuladorGasto s = service.listId(id);

        if (s == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una simulación con el ID: " + id);
        }

        ModelMapper m = new ModelMapper();
        SimuladorGastoDTO dto = m.map(s, SimuladorGastoDTO.class);

        return ResponseEntity.ok(dto);
    }

    // ELIMINAR
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public ResponseEntity<String> eliminar(@PathVariable("id") Integer id) {

        SimuladorGasto s = service.listId(id);

        if (s == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una simulación con el ID: " + id);
        }

        service.delete(id);

        return ResponseEntity.ok("Simulación eliminada correctamente.");
    }

    // EDITAR
    @PutMapping("/editar")
    @PreAuthorize("hasAnyAuthority('ADMIN','USUARIO')")
    public ResponseEntity<String> modificar(@RequestBody SimuladorGastoDTO dto) {

        ModelMapper m = new ModelMapper();
        SimuladorGasto s = m.map(dto, SimuladorGasto.class);

        SimuladorGasto existente = service.listId(s.getIdSimulador());

        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No existe una simulación con ese ID.");
        }

        // Recalcular costo total
        s.setCostoTotalEstimado(s.getCostoAlquiler() + s.getCostoTransporte());

        // Mantener fecha original
        s.setFechaSimulacion(existente.getFechaSimulacion());

        service.edit(s);

        return ResponseEntity.ok("Simulación actualizada correctamente.");
    }

}
