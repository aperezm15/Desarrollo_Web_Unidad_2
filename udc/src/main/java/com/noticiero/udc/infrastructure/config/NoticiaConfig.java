package com.noticiero.udc.infrastructure.config;

import com.noticiero.udc.application.ports.in.NoticiaUseCase;
import com.noticiero.udc.application.ports.out.NoticiaRepositoryPort;
import com.noticiero.udc.application.services.NoticiaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NoticiaConfig {

    @Bean
    public NoticiaUseCase noticiaService(NoticiaRepositoryPort noticiaRepositoryPort) {
        return new NoticiaService(noticiaRepositoryPort);
    }
}
