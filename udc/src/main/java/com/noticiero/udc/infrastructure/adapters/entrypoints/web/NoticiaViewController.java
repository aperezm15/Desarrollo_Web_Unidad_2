package com.noticiero.udc.infrastructure.adapters.entrypoints.web;

import com.noticiero.udc.application.ports.in.NoticiaUseCase;
import com.noticiero.udc.application.services.UserService;
import com.noticiero.udc.domain.models.Noticia;
import com.noticiero.udc.domain.models.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/noticias")
public class NoticiaViewController {

    private final NoticiaUseCase noticiaUseCase;
    private final UserService userService;


    public NoticiaViewController(NoticiaUseCase noticiaUseCase, UserService userService) {
        this.noticiaUseCase = noticiaUseCase;
        this.userService = userService;
    }

    @GetMapping
    public String listarNoticias(Model model) {

        model.addAttribute("noticias", noticiaUseCase.listarNoticias());

        return "noticias/lista";
    }

    // Muestra el formulario vacío
    @GetMapping("/nuevo")
    public String mostrarFormulario(Model model) {
        model.addAttribute("noticia", new NoticiaDTO()); // Enviamos un DTO vacío para el binding
        return "noticias/formulario";
    }

    // Procesa el envío del formulario
    @PostMapping("/guardar")
    public String guardarNoticia(@ModelAttribute("noticia") NoticiaDTO dto) {
        // Buscamos al periodista (por ahora manual, luego vendrá de la sesión)
        Usuario periodista = userService.ObtenerPorId(dto.getIdPeriodista());

        // Convertimos DTO a Dominio (Reutiliza tu lógica del RestController)
        Noticia noticia = new Noticia(
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

        noticiaUseCase.crearNoticia(noticia);
        return "redirect:/noticias"; // Redirige a la lista tras guardar
    }

    // --- FLUJO DE EDICIÓN ---

    // 1. Muestra el formulario con los datos de la noticia cargados
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        // Buscamos la noticia por su ID usando el caso de uso
        Noticia noticia = noticiaUseCase.obtenerNoticiaPorId(id)
                .orElseThrow(() -> new RuntimeException("Noticia no encontrada con el ID: " + id)); // Ajusta el método según tu UseCase

        // Mapeamos los datos de la entidad de dominio a un DTO para el formulario
        // Si usas un NoticiaDTO de infraestructura, instáncialo y llénalo aquí
        NoticiaDTO dto = new NoticiaDTO();
        dto.setId(noticia.getId());
        dto.setCategoria(noticia.getCategoria());
        dto.setFecha(noticia.getFecha());
        dto.setPais(noticia.getPais());
        dto.setDepartamento(noticia.getDepartamento());
        dto.setCiudad(noticia.getCiudad());
        dto.setIdPeriodista(noticia.getPeriodista().getId());
        dto.setProgramaEmite(noticia.getProgramaEmite());
        dto.setFechaEmision(noticia.getFechaEmite());
        dto.setDescripcion(noticia.getDescripcion());
        dto.setNivelPublico(noticia.getNivelPublico());

        model.addAttribute("noticia", dto);
        model.addAttribute("esEdicion", true); // Bandera para reutilizar o cambiar textos en la vista
        return "noticias/formulario"; // Reutilizamos tu formulario.html
    }

    // 2. Procesa la actualización de la noticia
    @PostMapping("/actualizar")
    public String actualizarNoticia(@ModelAttribute("noticia") NoticiaDTO dto) {
        Usuario periodista = userService.ObtenerPorId(dto.getIdPeriodista());

        Noticia noticia = new Noticia(
                dto.getId(), // Crucial: Al llevar el ID, JPA sabrá que debe actualizar y no crear uno nuevo
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

        noticiaUseCase.actualizarNoticia(noticia); // Ajusta según el método de tu UseCase
        return "redirect:/noticias";
    }

// --- FLUJO DE ELIMINACIÓN ---

    @GetMapping("/eliminar/{id}")
    public String eliminarNoticia(@PathVariable Long id) {
        noticiaUseCase.eliminarNoticia(id); // Ajusta según el método de tu UseCase
        return "redirect:/noticias";
    }
}
