package com.noticiero.udc.application.services;

import com.noticiero.udc.application.ports.in.UserCrudUseCase;
import com.noticiero.udc.application.ports.out.EmailServicePort;
import com.noticiero.udc.application.ports.out.UserRepositoryPort;
import com.noticiero.udc.domain.enums.UserStatus;
import com.noticiero.udc.domain.exceptions.InvalidUserEmailException;
import com.noticiero.udc.domain.exceptions.InvalidUserIdException;
import com.noticiero.udc.domain.exceptions.InvalidUserTokenException;
import com.noticiero.udc.domain.models.Usuario;
import jakarta.transaction.Transactional;

import java.util.List;

public class UserService implements UserCrudUseCase {
    private final UserRepositoryPort userRepositoryPort;
    private final EmailServicePort emailServicePort;

    public UserService(UserRepositoryPort userRepositoryPort, EmailServicePort emailServicePort) {
        this.userRepositoryPort = userRepositoryPort;
        this.emailServicePort = emailServicePort;
    }

    @Override
    @Transactional
    public Usuario crearUsuario(Usuario usuario) {
        // 1. Validar primero si el email ya existe para frenar el proceso de inmediato si es el caso
        if (userRepositoryPort.findByEmail(usuario.getEmail()).isPresent()) {
            throw new InvalidUserEmailException("El email ya existe en el sistema");
        }

        // 2. Si no existe, preparamos el token y el estado PENDIENTE
        String token = java.util.UUID.randomUUID().toString();
        usuario.setVerificationToken(token);
        usuario.setStatus(UserStatus.PENDIENTE);

        // 3. Guardamos definitivamente en la base de datos
        Usuario usuarioGuardado = userRepositoryPort.save(usuario);

        // 4. Disparamos el correo
        emailServicePort.enviarCorreoVerificacion(
                usuarioGuardado.getEmail().getValue(),
                usuarioGuardado.getNombre().getValue(),
                token
        );

        return usuarioGuardado;
    }


    @Override
    public Usuario ObtenerPorId(Long id) {
        if (userRepositoryPort.findById(id).isPresent()) {
            return userRepositoryPort.findById(id).get();
        } else {
            throw new InvalidUserIdException("El id "+id+ " de usuario no existe en el sistema");
        }

    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        if (userRepositoryPort.findById(usuario.getId()).isPresent()) {
            return userRepositoryPort.save(usuario);
        } else {
            throw new InvalidUserIdException("El usuario que se quiere actualizar con el id "+usuario.getId()+" no existe en el sistema");
        }
    }

    @Override
    public List<Usuario> ListarUsuarios() {
        return userRepositoryPort.findAll();
    }

    @Override
    public void eliminarPorId(Long id) {
       if (userRepositoryPort.findById(id).isPresent()) {
           userRepositoryPort.deleteById(id);
       } else {
           throw new InvalidUserIdException("No se puede eliminar el usuario con id "+ id);
       }
    }

    public void verificarCuenta(String token) {
        Usuario usuario = userRepositoryPort.buscarPorToken(token)
                .orElseThrow(() -> new InvalidUserTokenException("El token de verificacion no es valido o ya cacuco"));

        usuario.ActivarCuenta();

        userRepositoryPort.save(usuario);

    }
}
