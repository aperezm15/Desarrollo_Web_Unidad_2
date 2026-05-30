package com.noticiero.udc.infrastructure.adapters.persistence;

import com.noticiero.udc.application.ports.out.UserRepositoryPort;
import com.noticiero.udc.domain.models.Usuario;
import com.noticiero.udc.domain.valueobjects.UserEmail;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class UserRepositoryAdapter implements UserRepositoryPort {


    private final SpringDataUserRepository springDataUserRepository;
    private final UserPersistenceMapper userPersistenceMapper;

    public UserRepositoryAdapter(SpringDataUserRepository springDataUserRepository, UserPersistenceMapper mapper) {
        this.springDataUserRepository = springDataUserRepository;
        this.userPersistenceMapper = mapper;
    }

    @Override
    public Usuario save(Usuario usuario) {
        UserEntity userEntity = userPersistenceMapper.toEntity(usuario);
        UserEntity savedEntity = springDataUserRepository.save(userEntity);
        return userPersistenceMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Usuario> findById(Long id) {
        Optional<UserEntity> entityOptional = springDataUserRepository.findById(id);

        if (entityOptional.isPresent()) {
            Usuario domainUser = userPersistenceMapper.toDomain(entityOptional.get());
            return Optional.of(domainUser);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Usuario> findByEmail(UserEmail email) {
        // Extraemos el String del Value Object para que Spring Data haga el query
        Optional<UserEntity> entityOptional = springDataUserRepository.findByEmail(email.getValue());

        if (entityOptional.isPresent()) {
            Usuario domainUser = userPersistenceMapper.toDomain(entityOptional.get());
            return Optional.of(domainUser);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public List<Usuario> findAll() {
        List<UserEntity> entities = springDataUserRepository.findAll();
        List<Usuario> domainUsers = new ArrayList<>();

        // Ciclo tradicional de toda la vida para traducir la lista completa
        for (UserEntity entity : entities) {
            domainUsers.add(userPersistenceMapper.toDomain(entity));
        }

        return domainUsers;
    }

    @Override
    public void deleteById(Long id) {
        springDataUserRepository.deleteById(id);
    }

    @Override
    public Optional<Usuario> buscarPorToken(String token) {
        return springDataUserRepository.findByVerificationToken(token).map(userPersistenceMapper::toDomain);
    }
}
