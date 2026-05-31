package com.noticiero.udc.domain.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Noticia {
    private Long id;
    private String categoria;
    private LocalDate fecha;
    private String pais;
    private String departamento;
    private String ciudad;
    private Usuario usuario;
    private String programaEmite;
    private LocalDateTime fechaEmite;
    private String descripcion;
    private String nivelPublico;

    public Noticia() {}

    public Noticia(Long id, String categoria, LocalDate fecha, String pais, String departamento,
                   String ciudad, Usuario usuario, String programaEmite, LocalDateTime fechaEmite,
                   String descripcion, String nivelPublico) {
        this.id = id;
        this.categoria = categoria;
        this.fecha = fecha;
        this.pais = pais;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.usuario = usuario;
        this.programaEmite = programaEmite;
        this.fechaEmite = fechaEmite;
        this.descripcion = descripcion;
        this.nivelPublico = nivelPublico;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getProgramaEmite() {
        return programaEmite;
    }

    public void setProgramaEmite(String programaEmite) {
        this.programaEmite = programaEmite;
    }

    public LocalDateTime getFechaEmite() {
        return fechaEmite;
    }

    public void setFechaEmite(LocalDateTime fechaEmite) {
        this.fechaEmite = fechaEmite;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNivelPublico() {
        return nivelPublico;
    }

    public void setNivelPublico(String nivelPublico) {
        this.nivelPublico = nivelPublico;
    }
}
