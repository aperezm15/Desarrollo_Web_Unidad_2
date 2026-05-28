package com.noticiero.udc.application.ports.in;

import com.noticiero.udc.domain.models.Usuario;

import java.util.List;

public interface UserCrudUseCase {
    Usuario crearUsuario (Usuario usuario);
    Usuario actualizarUsuario (Usuario usuario);
    Usuario ObtenerPorId(Long id);
    void eliminarPorId (Long id);
    List<Usuario> ListarUsuarios ();

}
