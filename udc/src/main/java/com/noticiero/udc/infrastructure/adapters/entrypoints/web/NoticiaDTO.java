package com.noticiero.udc.infrastructure.adapters.entrypoints.web;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class NoticiaDTO {

    private Long id;

    @NotBlank(message = "La categoría es obligatoria")
    private String categoria;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    @NotBlank(message = "El país es obligatorio")
    private String pais;

    @NotBlank(message = "El departamento es obligatorio")
    private String departamento;

    @NotBlank(message = "La ciudad es obligatoria")
    private String ciudad;

    @NotNull(message = "El ID del periodista es obligatorio")
    private Long idPeriodista; // Mapeamos solo el ID para simplificar el JSON/Formulario

    private String programaEmite;
    private LocalDateTime fechaEmision;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    @NotBlank(message = "El nivel de público es obligatorio")
    private String nivelPublico;

    // Constructores
    public NoticiaDTO() {}

    public NoticiaDTO(Long id, String categoria, LocalDate fecha, String pais, String departamento,
                      String ciudad, Long idPeriodista, String programaEmite,
                      LocalDateTime fechaEmision, String descripcion, String nivelPublico) {
        this.id = id;
        this.categoria = categoria;
        this.fecha = fecha;
        this.pais = pais;
        this.departamento = departamento;
        this.ciudad = ciudad;
        this.idPeriodista = idPeriodista;
        this.programaEmite = programaEmite;
        this.fechaEmision = fechaEmision;
        this.descripcion = descripcion;
        this.nivelPublico = nivelPublico;
    }

    // ==========================================
    // GETTERS Y SETTERS
    // ==========================================
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public String getPais() { return pais; }
    public void setPais(String pais) { this.pais = pais; }

    public String getDepartamento() { return departamento; }
    public void setDepartamento(String departamento) { this.departamento = departamento; }

    public String getCiudad() { return ciudad; }
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    public Long getIdPeriodista() { return idPeriodista; }
    public void setIdPeriodista(Long idPeriodista) { this.idPeriodista = idPeriodista; }

    public String getProgramaEmite() { return programaEmite; }
    public void setProgramaEmite(String programaEmite) { this.programaEmite = programaEmite; }

    public LocalDateTime getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(LocalDateTime fechaEmision) { this.fechaEmision = fechaEmision; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getNivelPublico() { return nivelPublico; }
    public void setNivelPublico(String nivelPublico) { this.nivelPublico = nivelPublico; }
}