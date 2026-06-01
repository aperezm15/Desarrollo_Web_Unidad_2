package com.noticiero.udc.infrastructure.adapters.entrypoints.web;

import com.noticiero.udc.application.ports.in.NoticiaUseCase;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/noticias")
public class NoticiaViewController {

    private final NoticiaUseCase noticiaUseCase;


    public NoticiaViewController(NoticiaUseCase noticiaUseCase) {
        this.noticiaUseCase = noticiaUseCase;
    }

    @GetMapping
    public String listarNoticias(Model model) {

        model.addAttribute("noticias", noticiaUseCase.listarNoticias());

        return "noticias/lista";
    }
}