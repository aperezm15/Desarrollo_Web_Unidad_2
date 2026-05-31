package com.noticiero.udc.infrastructure.adapters.persistence;


import com.noticiero.udc.domain.models.Usuario;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "noticias")
public class NoticiaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String categoria;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(nullable = false)
    private String pais;

    @Column(nullable = false)
    private String departamento;

    @Column(nullable = false)
    private String ciudad;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "periodista_id", nullable = false)
    private UserEntity periodista;

    @Column(name = "programa_emite")
    private String programaEmite;

    @Column(name = "javaFechaEmision")
    private LocalDateTime javaFechaEmision;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(name = "nivel_publico", nullable = false)
    private String nivelPublico;

    public NoticiaEntity() {}

    public NoticiaEntity(Long id, String categoria, LocalDate fecha, String pais, String departamento,
                         String ciudad, UserEntity periodista, String programaEmite, LocalDateTime javaFechaEmision,
                         String descripcion, String nivelPublico) {
        this.id = id;
        this.categoria = categoria;
        this.fecha = fecha;
        this.pais = pais;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.periodista = periodista;
        this.programaEmite = programaEmite;
        this.javaFechaEmision = javaFechaEmision;
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

    public UserEntity getPeriodista() {
        return periodista;
    }

    public void setPeriodista(UserEntity periodista) {
        this.periodista = periodista;
    }

    public String getProgramaEmite() {
        return programaEmite;
    }

    public void setProgramaEmite(String programaEmite) {
        this.programaEmite = programaEmite;
    }

    public LocalDateTime getJavaFechaEmision() {
        return javaFechaEmision;
    }

    public void setJavaFechaEmision(LocalDateTime javaFechaEmision) {
        this.javaFechaEmision = javaFechaEmision;
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
