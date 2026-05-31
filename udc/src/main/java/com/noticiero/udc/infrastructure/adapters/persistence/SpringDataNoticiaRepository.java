package com.noticiero.udc.infrastructure.adapters.persistence;

import com.noticiero.udc.domain.models.Noticia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataNoticiaRepository extends JpaRepository<NoticiaEntity, Long> {
}
