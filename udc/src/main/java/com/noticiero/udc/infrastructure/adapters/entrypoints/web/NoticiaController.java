package com.noticiero.udc.infrastructure.adapters.entrypoints.web;

import com.noticiero.udc.domain.models.Noticia;
import com.noticiero.udc.domain.models.Usuario;
import com.noticiero.udc.application.ports.in.NoticiaUseCase;
import com.noticiero.udc.application.services.UserService; // Ajusta este import según tu interfaz/servicio de usuario
import com.noticiero.udc.infrastructure.adapters.entrypoints.web.NoticiaDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/noticias")
public class NoticiaController {

    private final NoticiaUseCase noticiaUseCase;
    private final UserService userService; // Lo requerimos para asociar al Periodista existente

    public NoticiaController(NoticiaUseCase noticiaUseCase, UserService userService) {
        this.noticiaUseCase = noticiaUseCase;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody NoticiaDTO dto) {
        // 1. Buscar que el periodista exista en el sistema
        java.util.Optional<Usuario> periodistaOpt = java.util.Optional.ofNullable(userService.ObtenerPorId(dto.getIdPeriodista()));
        if (periodistaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error: El usuario con ID " + dto.getIdPeriodista() + " no existe.");
        }

        // 2. Convertir DTO a Modelo de Dominio
        Noticia noticia = toDomain(dto, periodistaOpt.get());

        // 3. Guardar mediante el caso de uso
        Noticia nuevaNoticia = noticiaUseCase.crearNoticia(noticia);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(nuevaNoticia));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody NoticiaDTO dto) {
        if (noticiaUseCase.obtenerNoticiaPorId(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Noticia no encontrada.");
        }

        java.util.Optional<Usuario> periodistaOpt = java.util.Optional.ofNullable(userService.ObtenerPorId(dto.getIdPeriodista()));
        if (periodistaOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El periodista especificado no existe.");
        }

        dto.setId(id);
        Noticia noticia = toDomain(dto, periodistaOpt.get());
        Noticia actualizada = noticiaUseCase.actualizarNoticia(noticia);
        return ResponseEntity.ok(toDTO(actualizada));
    }

    @GetMapping
    public ResponseEntity<List<NoticiaDTO>> listar() {
        List<NoticiaDTO> noticias = noticiaUseCase.listarNoticias()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(noticias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        return noticiaUseCase.obtenerNoticiaPorId(id)
                .map(noticia -> ResponseEntity.ok(toDTO(noticia)))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (noticiaUseCase.obtenerNoticiaPorId(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Noticia no encontrada.");
        }
        noticiaUseCase.eliminarNoticia(id);
        return ResponseEntity.noContent().build();
    }

    // ==========================================
    // MAPPERS MANUALES DEL CONTROLADOR
    // ==========================================
    private Noticia toDomain(NoticiaDTO dto, Usuario periodista) {
        return new Noticia(
                dto.getId(),
                dto.getCategoria(),
                dto.getFecha(),
                dto.getPais(),
                dto.getDepartamento(),
                dto.getCiudad(),
                periodista,
                dto.getProgramaEmite(),
                dto.getFechaEmision(),
                dto.getDescripcion(),
                dto.getNivelPublico()
        );
    }

    private NoticiaDTO toDTO(Noticia domain) {
        return new NoticiaDTO(
                domain.getId(),
                domain.getCategoria(),
                domain.getFecha(),
                domain.getPais(),
                domain.getDepartamento(),
                domain.getCiudad(),
                domain.getPeriodista().getId(), // Extraemos solo el ID para el DTO
                domain.getProgramaEmite(),
                domain.getFechaEmite(),
                domain.getDescripcion(),
                domain.getNivelPublico()
        );
    }
}