package com.noticiero.udc.infrastructure.adapters.entrypoints.web;

import com.noticiero.udc.application.ports.in.UserCrudUseCase;
import com.noticiero.udc.domain.models.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class UserController {

    private final UserCrudUseCase userCrudUseCase;
    private final UserWebMapper userWebMapper;

    // Inyección del caso de uso (puerto de entrada) y el mapper web
    public UserController(UserCrudUseCase userCrudUseCase, UserWebMapper userWebMapper) {
        this.userCrudUseCase = userCrudUseCase;
        this.userWebMapper = userWebMapper;
    }

    @GetMapping
    public String listarUsuarios(Model model) {
        List<Usuario> usuariosDomain = userCrudUseCase.ListarUsuarios();
        List<UserWebDto> dtos = new ArrayList<>();

        for (Usuario u : usuariosDomain) {
            dtos.add(userWebMapper.toDto(u));
        }

        model.addAttribute("usuarios", dtos);
        return "usuarios/lista";
    }


    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("userDto", new UserWebDto());
        return "usuarios/formulario";
    }


    @PostMapping("/guardar")
    public String guardarUsuario(@ModelAttribute("userDto") UserWebDto dto, Model model) {
        try {
            Usuario usuarioADominio = userWebMapper.toDomain(dto);

            if (dto.getId() == null) {
                userCrudUseCase.crearUsuario(usuarioADominio);
            } else {
                userCrudUseCase.actualizarUsuario(usuarioADominio);
            }

            return "redirect:/usuarios"; // Éxito: refresca la lista
        } catch (Exception ex) {
            // Si salta InvalidUserNameException, InvalidUserEmailException, etc., la atrapamos aquí
            model.addAttribute("error", ex.getMessage());
            model.addAttribute("userDto", dto); // Conservamos los campos que digitó el usuario
            return "usuarios/formulario"; // Regresa al formulario mostrando el error en pantalla
        }
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model) {
        try {
            Usuario usuario = userCrudUseCase.ObtenerPorId(id);
            UserWebDto dto = userWebMapper.toDto(usuario);
            model.addAttribute("userDto", dto);
            return "usuarios/formulario";
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
            return "redirect:/usuarios";
        }
    }


    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable("id") Long id, Model model) {
        try {
            userCrudUseCase.eliminarPorId(id);
        } catch (Exception ex) {
            model.addAttribute("error", ex.getMessage());
        }
        return "redirect:/usuarios";
    }
}