package com.noticiero.udc.application.services;

import com.noticiero.udc.application.ports.in.NoticiaUseCase;
import com.noticiero.udc.application.ports.out.NoticiaRepositoryPort;
import com.noticiero.udc.domain.models.Noticia;

import java.util.List;
import java.util.Optional;

public class NoticiaService implements NoticiaUseCase {
    private final NoticiaRepositoryPort noticiaRepositoryPort;

    public NoticiaService(NoticiaRepositoryPort noticiaRepositoryPort) {
        this.noticiaRepositoryPort = noticiaRepositoryPort;
    }

    @Override
    public Noticia crearNoticia(Noticia noticia) {
        return noticiaRepositoryPort.guardar(noticia);
    }

    @Override
    public Noticia actualizarNoticia(Noticia noticia) {
        return noticiaRepositoryPort.actualizar(noticia);
    }
    
    @Override
    public List<Noticia> listarNoticias() {
        return noticiaRepositoryPort.obtenerTodas();
    }

    @Override
    public Optional<Noticia> obtenerNoticiaPorId(Long id) {
        return noticiaRepositoryPort.obtenerPorId(id);
    }

    @Override
    public void eliminarNoticia(Long id) {
        noticiaRepositoryPort.eliminar(id);
    }

}
