package com.noticiero.udc.domain.models;

import com.noticiero.udc.domain.enums.UserRole;
import com.noticiero.udc.domain.enums.UserStatus;
import com.noticiero.udc.domain.valueobjects.UserEmail;
import com.noticiero.udc.domain.valueobjects.UserName;

public class Usuario {
    private Long id;
    private UserName nombre;
    private UserEmail email;
    private String password;
    private UserRole role;
    private UserStatus status;

    public Usuario(Long id, UserName nombre, UserEmail email, String password, UserRole role, UserStatus status) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        setRole(role);
        setStatus(status);
    }

    public void setRole(UserRole role) {
        if (role != null) {
            this.role = role;
        } else {
            this.role = UserRole.LECTOR;
        }
    }

    public void setStatus(UserStatus status) {
        if (status != null) {
            this.status = status;
        } else {
            this.status = UserStatus.INACTIVO;
        }
    }

    public void Activar() {
        this.status = UserStatus.ACTIVO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserName getNombre() {
        return nombre;
    }

    public void setNombre(UserName nombre) {
        this.nombre = nombre;
    }

    public UserEmail getEmail() {
        return email;
    }

    public void setEmail(UserEmail email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public UserStatus getStatus() {
        return status;
    }
}
