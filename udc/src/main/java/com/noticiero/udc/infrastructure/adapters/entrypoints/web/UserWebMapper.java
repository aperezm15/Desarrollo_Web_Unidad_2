package com.noticiero.udc.infrastructure.adapters.entrypoints.web;

import com.noticiero.udc.domain.models.Usuario;
import com.noticiero.udc.domain.enums.UserRole;
import com.noticiero.udc.domain.enums.UserStatus;
import com.noticiero.udc.domain.valueobjects.UserEmail;
import com.noticiero.udc.domain.valueobjects.UserName;
import org.springframework.stereotype.Component;

@Component
public class UserWebMapper {


    public Usuario toDomain(UserWebDto dto) {
        if (dto == null) return null;

        UserRole mappedRole = null;
        if (dto.getRole() != null && !dto.getRole().isEmpty()) {
            mappedRole = UserRole.valueOf(dto.getRole());
        }

        UserStatus mappedStatus = null;
        if (dto.getStatus() != null && !dto.getStatus().isEmpty()) {
            mappedStatus = UserStatus.valueOf(dto.getStatus());
        }

        return new Usuario(
                dto.getId(),
                new UserName(dto.getNombre()),
                new UserEmail(dto.getEmail()),
                dto.getPassword(),
                mappedRole,
                mappedStatus
        );
    }


    public UserWebDto toDto(Usuario domain) {
        if (domain == null) return null;

        String roleStr = domain.getRole() != null ? domain.getRole().name() : null;
        String statusStr = domain.getStatus() != null ? domain.getStatus().name() : null;

        return new UserWebDto(
                domain.getId(),
                domain.getNombre().getValue(),
                domain.getEmail().getValue(),
                domain.getPassword(),
                roleStr,
                statusStr
        );
    }
}