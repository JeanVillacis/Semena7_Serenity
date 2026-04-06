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
| Gradle | 9.2.1 |

---

## Estructura del proyecto

```
src/test/
├── java/screenplay/
│   ├── config/               # Configuración centralizada (TestConfig)
│   ├── runner/               # TestRunner (CucumberWithSerenity)
│   ├── stepdefinitions/      # Definiciones de pasos por HU
│   ├── tasks/                # Tareas del patrón Screenplay
│   ├── questions/            # Preguntas / aserciones
│   ├── actions/              # Interacciones personalizadas (FillReactInput)
│   ├── interactions/         # Esperas personalizadas
│   ├── ui/                   # Page Objects (locadores XPath)
│   └── model/                # Clases de datos (AseguradoData, VehiculoData, PolizaData)
└── resources/
    └── features/             # Escenarios BDD en español (HU-001 a HU-013)
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
| HU-007 | Registro de reclamos de siniestro | CP001 al CP008 |
| HU-013 | Consulta de estado de reclamo por asegurado | CP001 al CP003 |

---

## Prerequisitos

1. **Java 17** instalado y configurado en `JAVA_HOME`
2. **Google Chrome** instalado (WebDriverManager descarga el driver automáticamente)
3. **Sistema InsurTech corriendo** en `http://localhost:4000`

> El sistema se levanta con Docker Compose desde el repositorio del backend:
> ```bash
> docker-compose down -v   # limpia volúmenes (BD limpia)
> docker-compose up -d     # levanta todos los servicios
> ```
> Espera ~30 segundos a que los servicios estén listos antes de ejecutar las pruebas.

---

## Configuración de variables de entorno

Las credenciales y la URL base se leen desde variables de entorno para evitar datos sensibles en el código fuente.

Copia el archivo de ejemplo y completa los valores:

```bash
cp .env.example .env
```

`.env.example`:
```env
WEBDRIVER_BASE_URL=http://localhost:4000
GESTOR_USERNAME=
GESTOR_PASSWORD=
```

> `.env` está en `.gitignore` y nunca debe subirse al repositorio.

También puedes pasar los valores directamente por línea de comandos:

```bash
./gradlew test -Dgestor.username=gestor01 -Dgestor.password=gestor123
```

El orden de precedencia es: **variable de entorno → `-D` system property → valor por defecto**.

---

## Ejecutar las pruebas

> **Importante:** ejecuta siempre con la base de datos limpia (`docker-compose down -v && docker-compose up -d`). Los tests son secuenciales y dependen de datos creados por tests anteriores.

```bash
# Ejecutar todos los tests y generar reporte
./gradlew clean test aggregate

# Ejecutar solo una HU específica
./gradlew clean test aggregate -Dcucumber.filter.tags="@HU-001"

# Ejecutar un caso de prueba específico
./gradlew clean test aggregate -Dcucumber.filter.tags="@CP001-HU-003"
```

---

## Ver el reporte

Una vez ejecutados los tests, abrir en el navegador:

```
target/site/serenity/index.html
```

El reporte incluye resultados por HU, capturas de pantalla de cada paso y detalle de errores.

> Para un análisis detallado de la última ejecución — métricas globales, resultados por HU, observaciones técnicas y recomendaciones — ver [INFORME.md](INFORME.md).

---

## Configuración general

El archivo `serenity.properties` controla el comportamiento del framework:

| Propiedad | Descripción |
|---|---|
| `webdriver.base.url` | URL base del sistema bajo prueba |
| `headless.mode` | `true` para ejecutar sin abrir Chrome |
| `serenity.take.screenshots` | Frecuencia de capturas de pantalla |

---

## Patrón Screenplay

Las pruebas siguen el patrón **Screenplay** con tres elementos clave:

- **Tasks** — acciones de negocio (`Login`, `RegisterAsegurado`, `SearchInList`)
- **Questions** — verificaciones (`PageContent`, `ElementVisibility`, `AlertMessage`)
- **Page Objects** — locadores centralizados (`AseguradoPage`, `VehiculoForm`, `PolizaPage`)

La interacción `FillReactInput` resuelve la compatibilidad con formularios **React Hook Form**, disparando los eventos nativos que React necesita para registrar cambios en sus inputs controlados.
