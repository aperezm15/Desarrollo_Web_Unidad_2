package com.noticiero.udc.infrastructure.adapters.entrypoints.web;

import com.noticiero.udc.application.ports.in.NoticiaUseCase;
import com.noticiero.udc.application.services.UserService;
import com.noticiero.udc.domain.models.Noticia;
import com.noticiero.udc.domain.models.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
