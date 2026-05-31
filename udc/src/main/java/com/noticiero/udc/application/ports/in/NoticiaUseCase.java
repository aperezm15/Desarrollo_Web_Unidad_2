package com.noticiero.udc.application.ports.in;

import com.noticiero.udc.domain.models.Noticia;

import java.util.List;
import java.util.Optional;

public interface NoticiaUseCase {
    Noticia crearNoticia(Noticia noticia);
    Noticia actualizarNoticia(Noticia noticia);
    List<Noticia> listarNoticias();
    Optional<Noticia> obtenerNoticiaPorId(Long id);
    void eliminarNoticia(Long id);
}
