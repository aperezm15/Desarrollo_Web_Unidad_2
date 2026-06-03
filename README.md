# 🚀 Desarrollo Web - Unidad 2 y 3

> **Asignatura:** Desarrollo de Software Web  
> **Estudiante:** Alvaro Jesus Perez Menendez  
> **Código:** 7502420053  
> **Institución:** Universidad de Cartagena

---

## 📐 Arquitectura del Proyecto

Este proyecto está diseñado aplicando **Arquitectura Hexagonal (Puertos y Adaptadores)**, lo que permite desacoplar la lógica de negocio central de las tecnologías externas (como la base de datos, la web o el servicio de correo).

<details>
<summary><b>📂 Haz clic aquí para desplegar la estructura de carpetas</b></summary>

```text
Desarrollo_Web_Unidad_2/
├── 📁 .idea/
├── 📁 .postman/
├── 📁 postman/
└── 📁 udc/
    └── 📁 src/
        └── 📁 main/
            └── 📁 java/
                └── 📦 com.noticiero.udc/
                    ├── 🧱 application/ (Puertos y Casos de Uso)
                    │   ├── 📥 ports/in/
                    │   └── 📤 ports/out/
                    ├── ⬢ domain/ (Lógica Central del Negocio)
                    │   ├── 🔢 enums/
                    │   ├── ⚠️ exceptions/
                    │   ├── 🔹 models/
                    │   └── 💎 valueobjects/
                    └── 🔌 infrastructure/ (Adaptadores y Configuración)
                        ├── 🌐 adapters/entrypoints.web/
                        ├── ✉️ adapters/mail/
                        ├── 🗄️ adapters/persistence/
                        └── ⚙️ config/
  ```
  </details>
  
## 🛠️ Tecnologías Utilizadas

* **Lenguaje y Gestion:** Java 21 MAVEN

* **Framework Base:** Spring boot

* **Base de Datos:** MySQL Workbench

* **Asistente IA:** Geminis

## 📦 Dependencias Utilizadas (Maven)
* **spring-boot-starter-web** - Para la creación de la API y controladores web.

* **spring-boot-starter-thymeleaf** - Motor de plantillas para renderizar las vistas HTML.

* **spring-boot-starter-data-jpa** - Persistencia de datos con Hibernate.

* **mysql-connector-j** - Conector oficial para la base de datos MySQL.

* **spring-boot-starter-validation** - Validación de datos en modelos y DTOs.

* **spring-boot-starter-mail** - Soporte para el envío de correos electrónicos.

* **spring-boot-devtools** - Herramientas de desarrollo para recarga rápida en caliente.

* **spring-boot-starter-test** - Entorno de pruebas unitarias e integración.

* **spring-boot-maven-plugin** - Plugin de empaquetado y ejecución de Maven.

* **spring-boot-starter** - Núcleo de soporte de Spring Boot.

## 🌿 Estrategia de Ramas (Git)
El repositorio está organizado mediante subramas funcionales que convergen en las entregas principales:

* **main:** Contiene todo el código unificado e integrado de las subramas.

* **Unidad2:** Rama de respaldo histórico donde se congela y guarda el proceso final de la Unidad 2.

* **Unidad3:** Rama activa de trabajo donde se desarrollarán todos los requerimientos solicitados para la Unidad 3.

### Subramas de Trabajo (Features):

* **auth-roles** - Gestión de autenticación y roles de usuario.

* **crud-noticias** - Operaciones básicas para la gestión de noticias.

* **crud-usuarios** - Operaciones básicas para la administración de usuarios.

* **emailService-crudUsuario** - Integración del servicio de correos con el flujo de usuarios.

* **vistas-noticias** - Interfaz gráfica y pantallas para visualizar las noticias.

## 🚀 Instrucciones de Ejecución (Unidad 2)
Para levantar y probar el proyecto de la Unidad 2, sigue estos pasos:

1. Configuracion de la Base de Datos, o crear una llamada **"noticiero_bd"**

2. Corre la aplicación ejecutando la clase principal UdcApplication.

3. Abre tu navegador web favorito e ingresa a la siguiente dirección para interactuar con la interfaz del sistema:
```text
http://localhost:8080/
```

## PETICIONES POSTMAN
```text
POST (Login)
http://localhost:8080/api/usuarios/login

{
    "email": "",
    "password": ""
}

GET (Activar cuenta)
http://localhost:8080/api/usuarios/verificar?token=AQUÍ_VA_EL_UUID

POST (Registrarse)
http://localhost:8080/api/usuarios
{
    "nombre": "",
    "email": "",
    "password": "",
    "role": "" (LECTOR, PERIODISTA, ADMIN)
}

GET (Listar Usuarios)
http://localhost:8080/api/usuarios

GET (Listar Usuario con id)
http://localhost:8080/api/usuarios/#

PUT (Actualizar Usuario)
http://localhost:8080/api/usuarios/#

DELETE (Eliminar usuario
http://localhost:8080/api/usuarios/#



POST (crear noticia)
http://localhost:8080/api/noticias
{
        "categoria": "",
        "fecha": "",
        "pais": "",
        "departamento": "",
        "ciudad": "",
        "idPeriodista": ,
        "programaEmite": "",
        "fechaEmision": null,
        "descripcion": "",
        "nivelPublico": ""
    }


GET (Listar lista de noticias)
http://localhost:8080/api/noticias


GET (buscar noticia por id)
http://localhost:8080/api/noticias/#

PUT (Atualizar)
http://localhost:8080/api/noticias/#

```
