package com.noticiero.udc.application.ports.out;

public interface EmailServicePort {
    void enviarCorreoVerificacion(String emailDestino, String nombreUsuario, String token);
}
