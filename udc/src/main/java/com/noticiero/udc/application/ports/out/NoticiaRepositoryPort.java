package com.noticiero.udc.application.ports.out;

import com.noticiero.udc.domain.models.Noticia;

import java.util.List;
import java.util.Optional;

public interface NoticiaRepositoryPort {
    Noticia guardar(Noticia noticia);
    List<Noticia> obtenerTodas();
    Optional<Noticia> obtenerPorId(Long id);
    void eliminar(Long id);
    Noticia actualizar(Noticia noticia);
}