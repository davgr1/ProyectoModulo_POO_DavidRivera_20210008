package ProyectoModulo_DavidRivera_20210008.Controller;

import ProyectoModulo_DavidRivera_20210008.Models.DTO.LibroDTO;
import ProyectoModulo_DavidRivera_20210008.Models.Exceptions.ExceptionLibro;
import ProyectoModulo_DavidRivera_20210008.Service.LibroService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/apiLibro")
public class LibroController {
    @Autowired
    public LibroService service;

    @GetMapping("/getDataLibro")
    public ResponseEntity<Page<LibroDTO>> getData(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size
    ){
        if (size <= 0 ||size > 10){
            ResponseEntity.badRequest().body(Map.of(
                    "status", "El tamaño correcto"
            ));
            return ResponseEntity.ok(null);
        }
        Page<LibroDTO> datos = service.getAllLibros(page, size);
        if (datos == null){
            ResponseEntity.badRequest().body(Map.of(
                    "status", "El tamaño no puese pasr de 10"
            ));
        }
        return ResponseEntity.ok(datos);
    }

    @PostMapping("/crateLibro")
    public ResponseEntity<Map<String, Object>> crear(@Valid @RequestBody LibroDTO libro){
        try {
            LibroDTO respuesta = service.createLibro(libro);
            if (respuesta == null){
                return ResponseEntity.badRequest().body(Map.of(
                        "status", "insercion incorecta",
                        "errorType", "ValiDation-Error",
                        "message", "Datos del libro invalido"
                ));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                    "status", "sucess",
                    "data", respuesta
            ));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                    "status", "error",
                    "message", "Error al registrar libro",
                    "data", e.getMessage()
            ));
        }
    }

    @PutMapping("/upadateLibro/{id}")
    public ResponseEntity<?>actualizar(
            @PathVariable Long id,
            @Valid @RequestBody LibroDTO libro,
            BindingResult bindingResult
    ){
        if (bindingResult.hasErrors()){
            Map<String, String> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error ->
                    errores.put(error.getField(), error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }
        try {
            LibroDTO libroActualizar = service.updateLibro(id,libro);
            return ResponseEntity.ok(libroActualizar);
        }catch (ExceptionLibro e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                    "error", "Datos duplicados",
                    "campo", e.getMessage()
            ));
        }
    }

    @DeleteMapping("/eliminarLibro/{id}")
    public ResponseEntity<Map<String, Object>> eliminar(@PathVariable long id){
        try {
            if (!service.deleteLibro(id)){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).header(
                        "X-Mensaje-Error", "Libro no encontrado").body(Map.of(
                        "error", "Not Found",
                        "message", "Libro eliminado",
                        "timestamp", Instant.now().toString()
                ));
            }
            return ResponseEntity.ok().body(Map.of(
                    "status", "proceso completado",
                    "message", "libro eliminado"
            ));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(Map.of(
                    "status", "Error",
                    "message", "Error al eliminar",
                    "detail", e.getMessage()
            ));
        }
    }
}
