package com.noticiero.udc.infrastructure.adapters.persistence;

import com.noticiero.udc.domain.models.Usuario;
import com.noticiero.udc.domain.valueobjects.UserEmail;
import com.noticiero.udc.domain.valueobjects.UserName;
import org.springframework.stereotype.Component;

@Component
public class UserPersistenceMapper {

    public UserEntity toEntity(Usuario domain) {
        if (domain == null) return null;

        UserEntity entity = new UserEntity(
                domain.getId(),
                domain.getNombre().getValue(),
                domain.getEmail().getValue(),
                domain.getPassword(),
                domain.getRole(),
                domain.getStatus()
        );
        // PASAMOS EL TOKEN DEL DOMINIO A LA ENTIDAD ANTES DE GUARDAR
        entity.setVerificationToken(domain.getVerificationToken());
        return entity;
    }

    public Usuario toDomain(UserEntity entity) {
        if (entity == null) return null;

        return new Usuario(
                entity.getId(),
                new UserName(entity.getNombre()),
                new UserEmail(entity.getEmail()),
                entity.getPassword(),
                entity.getRole(),
                entity.getStatus(),
                entity.getVerificationToken() // YA TIENE LOS 7 PARÁMETROS PERFECTO
        );
    }
}