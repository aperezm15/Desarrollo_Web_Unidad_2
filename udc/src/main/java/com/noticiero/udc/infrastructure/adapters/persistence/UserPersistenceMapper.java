package com.noticiero.udc.infrastructure.adapters.persistence;


import com.noticiero.udc.domain.models.Usuario;
import com.noticiero.udc.domain.valueobjects.UserEmail;
import com.noticiero.udc.domain.valueobjects.UserName;
import org.springframework.stereotype.Component;

@Component
public class UserPersistenceMapper {

    public UserEntity toEntity(Usuario domain) {
        if (domain == null) return null;

        return new UserEntity(
                domain.getId(),
                domain.getNombre().getValue(),
                domain.getEmail().getValue(),
                domain.getPassword(),
                domain.getRole(),
                domain.getStatus()
        );

    }

    public Usuario toDomain(UserEntity entity) {
        if (entity == null) return null;

        return new Usuario(
                entity.getId(),
                new UserName(entity.getNombre()),
                new UserEmail(entity.getEmail()),
                entity.getPassword(),
                entity.getRole(),
                entity.getStatus()
        );
    }
}
