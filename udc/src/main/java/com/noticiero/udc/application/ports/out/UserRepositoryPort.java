package com.noticiero.udc.application.ports.out;

import com.noticiero.udc.domain.models.Usuario;
import com.noticiero.udc.domain.valueobjects.UserEmail;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryPort {
    Usuario save (Usuario usuario);
    Optional<Usuario> finById (Long id);
    Optional<Usuario> findByEmail (UserEmail email);
    List<Usuario> findAll ();
    void deleteById (Long id);
}
