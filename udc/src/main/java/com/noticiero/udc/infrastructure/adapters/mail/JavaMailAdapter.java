package com.noticiero.udc.infrastructure.adapters.mail;

import com.noticiero.udc.application.ports.out.EmailServicePort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class JavaMailAdapter implements EmailServicePort {

    private final JavaMailSender mailSender;

    public JavaMailAdapter(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    @Override
    public void enviarCorreoVerificacion(String emailDestino, String nombreUsuario, String token) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(emailDestino);
        message.setSubject("Activa tu cuenta - Noticiero UDC");

        // Construimos el enlace dinámico que apuntará a nuestro controlador web
        String urlVerificacion = "http://localhost:8080/usuarios/verificar?token=" + token;

        String cuerpo = "Hola " + nombreUsuario + ",\n\n" +
                "¡Gracias por registrarte en nuestro sistema de noticias!\n" +
                "Para activar tu cuenta y poder iniciar sesión, por favor haz clic en el siguiente enlace:\n\n" +
                urlVerificacion + "\n\n" +
                "Si tú no solicitaste este registro, puedes ignorar este correo.\n" +
                "Atentamente,\nEl equipo de la UDC.";

        message.setText(cuerpo);
        mailSender.send(message);
    }
}
