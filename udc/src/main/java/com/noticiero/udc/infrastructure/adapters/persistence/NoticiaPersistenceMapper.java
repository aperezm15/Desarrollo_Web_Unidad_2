package com.noticiero.udc.infrastructure.adapters.persistence;

import com.noticiero.udc.domain.models.Noticia;

public class NoticiaPersistenceMapper {
    private final UserPersistenceMapper userMapper;

    public NoticiaPersistenceMapper(UserPersistenceMapper userMapper) {
        this.userMapper = userMapper;
    }

    public NoticiaEntity toEntity(Noticia domain) {
        if (domain == null) return null;

        return new NoticiaEntity(
                domain.getId(),
                domain.getCategoria(),
                domain.getFecha(),
                domain.getPais(),
                domain.getDepartamento(),
                domain.getCiudad(),
                userMapper.toEntity(domain.getPeriodista()),
                domain.getProgramaEmite(),
                domain.getFechaEmite(),
                domain.getDescripcion(),
                domain.getNivelPublico()
        );
    }

    public Noticia toDomain(NoticiaEntity entity) {
        if (entity == null) return null;
        return new Noticia(
                entity.getId(),
                entity.getCategoria(),
                entity.getFecha(),
                entity.getPais(),
                entity.getDepartamento(),
                entity.getCiudad(),
                userMapper.toDomain(entity.getPeriodista()),
                entity.getProgramaEmite(),
                entity.getJavaFechaEmision(),
                entity.getDescripcion(),
                entity.getNivelPublico()
        );
    }

}
