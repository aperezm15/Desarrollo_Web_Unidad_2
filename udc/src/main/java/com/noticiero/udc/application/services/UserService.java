package com.noticiero.udc.application.services;

import com.noticiero.udc.application.ports.in.UserCrudUseCase;
import com.noticiero.udc.application.ports.out.UserRepositoryPort;
import com.noticiero.udc.domain.exceptions.InvalidUserEmailException;
import com.noticiero.udc.domain.exceptions.InvalidUserIdException;
import com.noticiero.udc.domain.models.Usuario;

import java.util.List;

public class UserService implements UserCrudUseCase {
    private final UserRepositoryPort userRepositoryPort;

    public UserService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public Usuario crearUsuario(Usuario usuario) {
        if (userRepositoryPort.findByEmail(usuario.getEmail()).isPresent()) {
            throw new InvalidUserEmailException("El email ya existe en el sistema");
        }
        return userRepositoryPort.save(usuario);
    }


    @Override
    public Usuario ObtenerPorId(Long id) {
        if (userRepositoryPort.finById(id).isPresent()) {
            return userRepositoryPort.finById(id).get();
        } else {
            throw new InvalidUserIdException("El id "+id+ " de usuario no existe en el sistema");
        }

    }

    @Override
    public Usuario actualizarUsuario(Usuario usuario) {
        if (userRepositoryPort.finById(usuario.getId()).isPresent()) {
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
       if (userRepositoryPort.finById(id).isPresent()) {
           userRepositoryPort.deleteById(id);
       } else {
           throw new InvalidUserIdException("No se puede eliminar el usuario con id "+ id);
       }
    }
}
