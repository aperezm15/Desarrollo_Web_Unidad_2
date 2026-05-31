package com.noticiero.udc.infrastructure.adapters.persistence;

import com.noticiero.udc.domain.models.Noticia;
import com.noticiero.udc.application.ports.out.NoticiaRepositoryPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class NoticiaRepositoryAdapter implements NoticiaRepositoryPort {

    private final SpringDataNoticiaRepository springDataNoticiaRepository;
    private final NoticiaPersistenceMapper noticiaPersistenceMapper;

    // Inyección de dependencias por constructor
    public NoticiaRepositoryAdapter(SpringDataNoticiaRepository springDataNoticiaRepository,
                                    NoticiaPersistenceMapper noticiaPersistenceMapper) {
        this.springDataNoticiaRepository = springDataNoticiaRepository;
        this.noticiaPersistenceMapper = noticiaPersistenceMapper;
    }

    @Override
    public Noticia guardar(Noticia noticia) {
        NoticiaEntity entity = noticiaPersistenceMapper.toEntity(noticia);
        NoticiaEntity savedEntity = springDataNoticiaRepository.save(entity);
        return noticiaPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public Noticia actualizar(Noticia noticia) {
        // En JPA, el método .save() funciona tanto para insertar como para actualizar
        // si el objeto ya lleva un ID existente.
        NoticiaEntity entity = noticiaPersistenceMapper.toEntity(noticia);
        NoticiaEntity updatedEntity = springDataNoticiaRepository.save(entity);
        return noticiaPersistenceMapper.toDomain(updatedEntity);
    }

    @Override
    public List<Noticia> obtenerTodas() {
        return springDataNoticiaRepository.findAll()
                .stream()
                .map(noticiaPersistenceMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Noticia> obtenerPorId(Long id) {
        return springDataNoticiaRepository.findById(id)
                .map(noticiaPersistenceMapper::toDomain);
    }

    @Override
    public void eliminar(Long id) {
        springDataNoticiaRepository.deleteById(id);
    }
}