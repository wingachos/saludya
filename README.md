# SaludYa - API MVC

API REST de ejemplo con Spring Boot, Spring Data JPA y PostgreSQL. El primer módulo implementado es la gestión de doctores.

## Requisitos

- Java 17 o superior
- Maven 3.6.3 o superior
- Docker con Docker Compose (opcional, para iniciar PostgreSQL)

## Ejecutar

1. Inicia PostgreSQL:

   ```bash
   docker compose up -d postgres
   ```

2. Inicia la aplicación:

   ```bash
   mvn spring-boot:run
   ```

La API queda disponible en `http://localhost:8080`. Hibernate crea o actualiza la tabla `doctores` al iniciar.

La conexión predeterminada usa la base `saludya` con usuario y contraseña `saludya`, igual que el servicio del archivo `compose.yaml`. Para usar otra configuración, define `DB_URL`, `DB_USERNAME` y `DB_PASSWORD` en el entorno.

## Estructura MVC

```text
src/main/java/
├── app/           # Inicio y configuración de Spring Boot
├── model/         # Entidad Doctor
├── view/          # Representación de salida de la API
├── controller/    # Endpoints HTTP
├── dao/           # Acceso a datos con JPA
├── service/       # Reglas de negocio
└── exception/     # Manejo uniforme de errores
```

Esta separación sigue las capas `model`, `view`, `controller` y `dao` del esquema, y añade `service`, `exception` y `app` para las responsabilidades propias de Spring Boot.

## Endpoints

| Método | Ruta | Acción |
| --- | --- | --- |
| GET | `/api/doctores` | Listar doctores |
| GET | `/api/doctores/{id}` | Obtener un doctor |
| POST | `/api/doctores` | Crear doctor |
| PUT | `/api/doctores/{id}` | Actualizar doctor |
| DELETE | `/api/doctores/{id}` | Eliminar doctor |

Ejemplo de cuerpo para `POST` o `PUT`:

```json
{
  "nombres": "Ana",
  "apellidos": "Pérez",
  "especialidad": "Medicina general",
  "email": "ana.perez@saludya.com",
  "telefono": "+51 999 999 999",
  "activo": true
}
```

Las validaciones devuelven HTTP 400; los recursos inexistentes, 404; y un correo duplicado, 409.
