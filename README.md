# Pruebas_Serenity — Automatización Frontend con Serenity BDD + Screenplay

Proyecto de pruebas automatizadas end-to-end para el sistema de gestión de seguros **InsurTech**, implementado con el patrón **Screenplay** sobre **Serenity BDD + Cucumber**.

---

## Tecnologías

| Herramienta | Versión |
|---|---|
| Java | 17 |
| Serenity BDD | 4.2.9 |
| Cucumber | 7.18.0 |
| Selenium | 4.21.0 |
| WebDriverManager | 5.9.1 |
| JUnit | 4.13.2 |
| Gradle | 8+ |

---

## Estructura del proyecto

```
src/test/
├── java/screenplay/
│   ├── runner/               # TestRunner (CucumberWithSerenity)
│   ├── stepdefinitions/      # Definiciones de pasos por HU
│   ├── tasks/                # Tareas del patrón Screenplay
│   ├── questions/            # Preguntas / aserciones
│   ├── actions/              # Interacciones personalizadas (FillReactInput)
│   ├── interactions/         # Esperas personalizadas
│   ├── ui/                   # Page Objects (locadores XPath/CSS)
│   └── model/                # Clases de datos (AseguradoData, VehiculoData, PolizaData)
└── resources/
    └── features/             # Escenarios BDD en español (HU-001 a HU-006)
```

---

## Historias de usuario cubiertas

| HU | Módulo | Casos de prueba |
|---|---|---|
| HU-001 | Registro de asegurado | CP001 al CP005 |
| HU-002 | Consulta de asegurados | CP001 al CP003 |
| HU-003 | Registro de vehículo | CP001 al CP004 |
| HU-004 | Consulta de vehículos | CP001 al CP003 |
| HU-005 | Registro de póliza | CP001, CP003, CP004, CP005 |
| HU-006 | Consulta de pólizas | CP001 al CP003 |

---

## Prerequisitos

1. **Java 17** instalado y configurado en `JAVA_HOME`
2. **Google Chrome** instalado (WebDriverManager descarga el driver automáticamente)
3. **Sistema InsurTech corriendo** en `http://localhost:4000`

> El sistema completo se levanta con Docker Compose desde el repositorio del backend:
> ```bash
> docker-compose down -v   # limpia volúmenes (BD limpia)
> docker-compose up -d     # levanta todos los servicios
> ```
> Espera ~30 segundos a que los microservicios Spring Boot estén listos.

---

## Ejecutar las pruebas

```bash
# Ejecutar todos los tests y generar reporte
./gradlew clean test aggregate

# Ejecutar solo una HU específica
./gradlew clean test aggregate -Dcucumber.filter.tags="@HU-001"

# Ejecutar un caso de prueba específico
./gradlew clean test aggregate -Dcucumber.filter.tags="@CP001-HU-003"
```

> **Importante:** Ejecutar siempre con la base de datos limpia (`docker-compose down -v && docker-compose up -d`). Los tests son secuenciales y dependen de datos previos (ej: HU-003 CP003 requiere que CP001 haya registrado la placa).

---

## Ver el reporte

Una vez ejecutados los tests, abrir en el navegador:

```
target/site/serenity/index.html
```

El reporte incluye resultados por HU, capturas de pantalla de cada paso y detalle de errores.

---

## Configuración

| Archivo | Descripción |
|---|---|
| `serenity.properties` | URL base, driver, modo headless, screenshots |
| `src/test/resources/serenity.conf` | Nombre del proyecto, directorio de features |

Para ejecutar en modo headless (sin abrir Chrome):

```properties
# serenity.properties
headless.mode=true
```

---

## Patrón Screenplay

Las pruebas siguen el patrón **Screenplay** con tres elementos clave:

- **Tasks** — acciones de negocio (`Login`, `RegisterAsegurado`, `SearchInList`)
- **Questions** — verificaciones (`PageContent`, `ElementVisibility`, `AlertMessage`)
- **Page Objects** — locadores centralizados (`AseguradoPage`, `VehiculoForm`, `PolizaPage`)

La interacción `FillReactInput` resuelve la compatibilidad con formularios **React Hook Form**, disparando los eventos nativos que React necesita para validar campos (`valueAsNumber`, `setValueAs`).
