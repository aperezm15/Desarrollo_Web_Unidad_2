package com.noticiero.udc.infrastructure.adapters.entrypoints.web;

public class UserWebDto {
    private Long id;
    private String nombre;
    private String email;
    private String password;
    private String role;
    private String status;

    public UserWebDto() {}

    public UserWebDto(Long id, String nombre, String email, String password, String role, String status) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
