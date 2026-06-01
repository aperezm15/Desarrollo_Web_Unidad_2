package com.noticiero.udc.infrastructure.adapters.entrypoints.web;

import com.noticiero.udc.application.services.UserService;
import com.noticiero.udc.domain.models.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthViewController {

    private final UserService userService;

    public AuthViewController(UserService userService) {
        this.userService = userService;
    }

    // Muestra la pantalla de Login (Raíz del sitio y /login)
    @GetMapping({"/", "/login"})
    public String mostrarLogin(HttpSession session) {
        // Redirección inteligente: Si ya está logueado, lo mandamos directo al portal
        if (session.getAttribute("usuarioLogueado") != null) {
            return "redirect:/noticias";
        }
        return "auth/login"; // Buscará templates/auth/login.html
    }

    // Procesa el formulario de Login
    @PostMapping("/login")
    public String procesarLogin(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        try {
            // Invocamos el método expuesto en tu servicio
            Usuario usuario = userService.ObtenerPorEmail(email);

            // Comparamos la contraseña directamente con el dominio
            // Nota: Si tu entidad Usuario almacena el password como Value Object (ej: usuario.getPassword().getValue()), ajusta el .equals()
            if (usuario != null && usuario.getPassword().equals(password)) {

                // Guardamos las credenciales y el rol en la sesión HTTP
                session.setAttribute("usuarioLogueado", usuario);
                session.setAttribute("usuarioRol", usuario.getRole().toString());

                return "redirect:/noticias";
            }

            model.addAttribute("error", "Credenciales incorrectas.");
            return "auth/login";

        } catch (com.noticiero.udc.domain.exceptions.InvalidUserEmailException e) {
            // Captura la excepción si el correo no existe en la BD
            model.addAttribute("error", "El correo electrónico no está registrado.");
            return "auth/login";
        }
    }

    // Cierre de sesión
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Destruye la sesión por completo y limpia las cookies del servidor
        return "redirect:/noticias";
    }
}