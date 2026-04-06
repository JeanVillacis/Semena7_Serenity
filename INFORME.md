# INFORME DE RESULTADOS DE PRUEBAS AUTOMATIZADAS
## Sistema de Gestión de Seguros — Pruebas_Serenity

---

| Campo              | Detalle                          |
|--------------------|----------------------------------|
| **Proyecto**       | Pruebas_Serenity                 |
| **Framework**      | Serenity BDD + Gradle 9.2.1      |
| **Patrón**         | Screenplay Pattern               |
| **Navegador**      | Google Chrome (ChromeDriver 146) |
| **Fecha ejecución**| 04 de abril de 2026              |
| **Inicio**         | 17:59:46                         |
| **Fin**            | 18:01:43                         |
| **Elaborado por**  | Jean Villacis                    |
| **Generado con**   | Serenity BDD Reports             |

---

## 1. RESUMEN EJECUTIVO

La ejecución de la suite de pruebas automatizadas sobre el sistema de gestión de seguros arrojó resultados **completamente satisfactorios**, con una tasa de éxito del **100%** sobre la totalidad de los casos de prueba ejecutados.

Se cubrieron **8 historias de usuario** que abarcan los módulos críticos del sistema: registro y consulta de asegurados, registro y consulta de vehículos, registro y consulta de pólizas, registro de reclamos de siniestros, y consulta del estado de reclamos. No se registró ningún fallo, error o caso ignorado.

---

## 2. MÉTRICAS GLOBALES DE EJECUCIÓN

| Métrica                          | Valor       |
|----------------------------------|-------------|
| Total de Escenarios              | 32          |
| Total de Casos de Prueba         | 40          |
| Casos Exitosos                   | 40          |
| Fallos                           | 0           |
| Ignorados / Saltados             | 0           |
| **Tasa de Éxito**                | **100%**    |
| Duración Total (reloj)           | 1m 56s      |
| Tiempo Acumulado de Pruebas      | 1m 54s      |
| Tiempo Promedio por Test         | 2s          |
| Test más rápido                  | 1s          |
| Test más lento                   | 6s          |

---

## 3. COBERTURA POR HISTORIA DE USUARIO

| ID     | Historia de Usuario                              | Escenarios | Casos | Duración   | % Éxito |
|--------|--------------------------------------------------|:----------:|:-----:|:----------:|:-------:|
| HU-001 | Registro de asegurado                            | 5          | 7     | 24.462s    | 100%    |
| HU-002 | Consultar asegurados                             | 3          | 4     | 8.086s     | 100%    |
| HU-003 | Registrar vehículo                               | 4          | 6     | 29.498s    | 100%    |
| HU-004 | Consultar vehículos                              | 3          | 3     | 6.139s     | 100%    |
| HU-005 | Registrar póliza de seguro                       | 4          | 5     | 13.827s    | 100%    |
| HU-006 | Consultar pólizas                                | 3          | 3     | 5.592s     | 100%    |
| HU-007 | Registrar Reclamo de Siniestro                   | 7          | 9     | 21.251s    | 100%    |
| HU-013 | Consulta de estado de reclamo por el asegurado   | 3          | 3     | 8.241s     | 100%    |
| **TOTAL** |                                               | **32**     | **40**| **1m 56s** | **100%**|

---

## 4. DETALLE DE RESULTADOS POR HISTORIA DE USUARIO

### 4.1 HU-001 — Registro de Asegurado

**Objetivo:** Verificar que el gestor pueda registrar asegurados correctamente y que el sistema prevenga registros inválidos o duplicados.

| CP    | Caso de Prueba                                              | Duración | Resultado |
|-------|-------------------------------------------------------------|:--------:|:---------:|
| CP001 | Registro exitoso con todos los datos válidos                | 4.131s   | ✅ PASSED |
| CP002 | Intento de registro omitiendo campo obligatorio — nombre    | 2.788s   | ✅ PASSED |
| CP002 | Intento de registro omitiendo campo obligatorio — apellido  | 2.887s   | ✅ PASSED |
| CP002 | Intento de registro omitiendo campo obligatorio — ID        | 2.695s   | ✅ PASSED |
| CP003 | Intento de registro sin completar ningún campo              | 2.269s   | ✅ PASSED |
| CP004 | Intento de registro con identificación ya existente         | 6.820s   | ✅ PASSED |
| CP005 | Intento de registro con correo electrónico inválido         | 2.872s   | ✅ PASSED |

**Datos de prueba relevantes:** nombre: "Juan", apellido: "Pérez", identificación: "1712345678", dirección: "Av. Amazonas N36-152", teléfono: "0991234567", correo: "juan.perez@correo.com".

**Observaciones:** El caso CP004 (identificación duplicada) presentó el tiempo de ejecución más alto del módulo (6.820s), lo cual es esperado dado que el sistema debe consultar la base de datos antes de rechazar el registro.

---

### 4.2 HU-002 — Consultar Asegurados

**Objetivo:** Verificar que el gestor pueda visualizar el listado de asegurados y consultar el detalle individual, incluyendo el manejo de búsquedas sin resultados.

| CP    | Caso de Prueba                                          | Duración | Resultado |
|-------|---------------------------------------------------------|:--------:|:---------:|
| CP001 | Visualización del listado con asegurados registrados    | 2.035s   | ✅ PASSED |
| CP002 | Consulta del detalle de un asegurado existente          | 2.144s   | ✅ PASSED |
| CP003 | Consulta de un asegurado que no existe — iteración #1   | 2.069s   | ✅ PASSED |
| CP003 | Consulta de un asegurado que no existe — iteración #2   | 1.838s   | ✅ PASSED |

**Observaciones:** Los tiempos de ejecución son uniformes y dentro del rango esperado. El manejo de búsquedas sin resultados funciona correctamente en múltiples iteraciones de datos.

---

### 4.3 HU-003 — Registrar Vehículo

**Objetivo:** Verificar que el gestor pueda registrar vehículos con datos válidos y que el sistema valide campos obligatorios, duplicidad de placa y formato de datos.

| CP    | Caso de Prueba                                              | Duración | Resultado |
|-------|-------------------------------------------------------------|:--------:|:---------:|
| CP001 | Registro exitoso de vehículo con datos válidos              | 4.792s   | ✅ PASSED |
| CP002 | Intento de registro omitiendo campo obligatorio — marca     | 6.624s   | ✅ PASSED |
| CP002 | Intento de registro omitiendo campo obligatorio — modelo    | 2.554s   | ✅ PASSED |
| CP002 | Intento de registro omitiendo campo obligatorio — placa     | 2.558s   | ✅ PASSED |
| CP003 | Intento de registro con placa ya existente                  | 6.614s   | ✅ PASSED |
| CP004 | Intento de registro con formato de datos inválido           | 6.356s   | ✅ PASSED |

**Datos de prueba relevantes:** marca: "Chevrolet", modelo: "Aveo", año: "2022", placa: "PBA-1234".

**Observaciones:** Los casos CP003 y CP004 muestran tiempos superiores (~6.5s), consistentes con operaciones que involucran validación contra base de datos. HU-003 es la historia con mayor tiempo total de ejecución (29.498s), lo que refleja la complejidad de sus validaciones de negocio.

---

### 4.4 HU-004 — Consultar Vehículos

**Objetivo:** Verificar la visualización del listado de vehículos con sus atributos y la funcionalidad de búsqueda por placa.

| CP    | Caso de Prueba                                          | Duración | Resultado |
|-------|---------------------------------------------------------|:--------:|:---------:|
| CP001 | Visualización del listado de vehículos registrados      | 2.122s   | ✅ PASSED |
| CP002 | Consulta del detalle de un vehículo existente           | 1.918s   | ✅ PASSED |
| CP003 | Consulta de un vehículo que no existe                   | 2.099s   | ✅ PASSED |

**Observaciones:** El sistema presenta correctamente los campos marca, modelo, año y placa en el listado. La búsqueda por placa inexistente ("ZZZ-0000") retorna resultados vacíos sin errores.

---

### 4.5 HU-005 — Registrar Póliza de Seguro

**Objetivo:** Verificar el registro exitoso de pólizas y las validaciones de negocio: número duplicado, rango de vigencia inválido y valor asegurado incorrecto.

| CP    | Caso de Prueba                                                  | Duración | Resultado |
|-------|-----------------------------------------------------------------|:--------:|:---------:|
| CP001 | Registro exitoso de póliza con datos válidos                    | 2.947s   | ✅ PASSED |
| CP003 | Registro con número de póliza duplicado                         | 2.849s   | ✅ PASSED |
| CP004 | Registro con rango de vigencia inválido (fin antes que inicio)  | 2.754s   | ✅ PASSED |
| CP005 | Registro con valor asegurado igual a cero (0)                   | 2.787s   | ✅ PASSED |
| CP005 | Registro con valor asegurado negativo (-5000)                   | 2.490s   | ✅ PASSED |

**Datos de prueba relevantes:** póliza: "POL-2026-001", valor: $25,000.00, vigencia: 2026-01-01 al 2027-12-31.

**Observaciones:** Las validaciones de negocio (duplicidad de número, rango de fechas, valor asegurado) funcionan correctamente. Se nota la ausencia de CP002 en el reporte, lo que sugiere que ese caso podría estar cubierto implícitamente en CP001 o fue omitido del alcance de esta iteración.

---

### 4.6 HU-006 — Consultar Pólizas

**Objetivo:** Verificar que el sistema presente el listado de pólizas con los atributos requeridos y permita consultar el detalle incluyendo asegurado y vehículo asociado.

| CP    | Caso de Prueba                                              | Duración | Resultado |
|-------|-------------------------------------------------------------|:--------:|:---------:|
| CP001 | Visualización del listado de pólizas registradas            | 1.990s   | ✅ PASSED |
| CP002 | Consulta del detalle de una póliza existente                | 1.803s   | ✅ PASSED |
| CP003 | Consulta de una póliza que no existe                        | 1.799s   | ✅ PASSED |

**Observaciones:** HU-006 es la historia con menor tiempo de ejecución total (5.592s). El sistema muestra correctamente número de póliza, asegurado, estado y fechas de vigencia. CP002 verifica la trazabilidad póliza-asegurado-vehículo.

---

### 4.7 HU-007 — Registrar Reclamo de Siniestro

**Objetivo:** Verificar el flujo completo de registro de reclamos, incluyendo adjuntar fotografías, y las validaciones de campos obligatorios, montos inválidos, fechas fuera de rango y formatos de archivo no permitidos.

| CP    | Caso de Prueba                                                      | Duración | Resultado |
|-------|---------------------------------------------------------------------|:--------:|:---------:|
| CP001 | Registro exitoso con datos completos y fotografía válida            | 2.374s   | ✅ PASSED |
| CP002 | Intento de registro sin seleccionar póliza                          | 2.202s   | ✅ PASSED |
| CP003 | Intento de registro sin completar campo obligatorio — iteración #1  | 2.445s   | ✅ PASSED |
| CP003 | Intento de registro sin completar campo obligatorio — iteración #2  | 2.370s   | ✅ PASSED |
| CP004 | Intento de registro con monto estimado inválido — iteración #1      | 2.125s   | ✅ PASSED |
| CP004 | Intento de registro con monto estimado inválido — iteración #2      | 2.228s   | ✅ PASSED |
| CP005 | Intento de registro con fecha de incidente fuera de rango           | 2.499s   | ✅ PASSED |
| CP006 | Intento de registro con archivo en formato no permitido             | 2.530s   | ✅ PASSED |
| CP008 | Intento de registro sin adjuntar ninguna fotografía                 | 2.478s   | ✅ PASSED |

**Datos de prueba relevantes:** póliza: "POL-2026-001", fecha: "2026-03-15", descripción: "Colisión lateral", monto: $3,500.00, ubicación: "Av. Amazonas, Quito", fotografía: "foto_siniestro.jpg".

**Observaciones:** Esta es la historia con mayor número de casos (9) y escenarios (7), lo que refleja su criticidad en el negocio. El flujo exitoso (CP001) valida la generación de número de seguimiento y el estado inicial "REGISTRADO". Se observa que CP007 no aparece en esta ejecución, lo que puede indicar que fue excluido del scope o es un escenario planificado para una iteración futura.

---

### 4.8 HU-013 — Consulta de Estado de Reclamo por el Asegurado

**Objetivo:** Verificar que el sistema aplique correctamente la lógica de evaluación automática de reclamos y muestre los estados correspondientes (APROBADO, EN REVISION MANUAL, DESCARTADO).

| CP    | Caso de Prueba                                              | Duración | Resultado |
|-------|-------------------------------------------------------------|:--------:|:---------:|
| CP001 | Consulta de reclamo aprobado automáticamente                | 2.905s   | ✅ PASSED |
| CP002 | Consulta de reclamo en revisión manual                      | 2.907s   | ✅ PASSED |
| CP003 | Consulta de reclamo descartado                              | 2.429s   | ✅ PASSED |

**Reglas de negocio validadas:**
- Reclamo por monto $3,500.00 → **APROBADO** (muestra monto aprobado y deducible aplicado)
- Reclamo por monto $6,000.00 → **EN REVISIÓN MANUAL** (muestra mensaje de revisión manual)
- Reclamo por monto $150.00 → **DESCARTADO** (muestra motivo del descarte)

**Observaciones:** La lógica de evaluación automática basada en umbrales de monto funciona correctamente para los tres estados posibles. Los tiempos de CP001 y CP002 son prácticamente idénticos (~2.9s), lo que indica estabilidad en la ejecución.

---

## 5. ANÁLISIS DE PRIORIDADES (CRITICIDAD)

Basado en las etiquetas identificadas en la ejecución:

| Prioridad | Descripción                                                     | Casos Cubiertos |
|-----------|-----------------------------------------------------------------|:---------------:|
| Crítico   | Flujos de negocio principales (registro exitoso, validaciones clave) | ~8          |
| Alto      | Validaciones de campos obligatorios, manejo de errores de negocio | Mayoría        |
| Medio     | Consultas sin resultados, búsquedas de registros inexistentes   | ~3              |

Todos los casos críticos y de alta prioridad pasaron exitosamente, confirmando la estabilidad del sistema en sus funcionalidades nucleares.

---

## 6. ANÁLISIS DE RENDIMIENTO

| Módulo   | Duración Total | % del Tiempo Total | Promedio por Test |
|----------|:--------------:|:------------------:|:-----------------:|
| HU-003   | 29.498s        | 25.8%              | 4.916s            |
| HU-001   | 24.462s        | 21.4%              | 3.494s            |
| HU-007   | 21.251s        | 18.6%              | 2.361s            |
| HU-005   | 13.827s        | 12.1%              | 2.765s            |
| HU-013   | 8.241s         | 7.2%               | 2.747s            |
| HU-002   | 8.086s         | 7.1%               | 2.022s            |
| HU-004   | 6.139s         | 5.4%               | 2.046s            |
| HU-006   | 5.592s         | 4.9%               | 1.864s            |
| **Total**| **~114.3s**    | **100%**           | **~2.857s**       |

Los módulos de registro (HU-001, HU-003) presentan tiempos mayores por las operaciones de escritura en base de datos y las validaciones de duplicidad, lo cual es el comportamiento esperado.

---

## 7. OBSERVACIONES TÉCNICAS

1. **Advertencias de CDP (Chrome DevTools Protocol):** Durante la ejecución se registraron advertencias del tipo `Unable to find CDP implementation matching 146` para ChromeDriver 146.0.7680.178. Estas advertencias no afectaron la ejecución de los tests pero se recomienda actualizar la dependencia `selenium-devtools` a la versión correspondiente al navegador instalado (v146).

2. **Patrón Screenplay:** La implementación sigue correctamente el patrón Screenplay con separación clara de `StepDefinitions`, lo que favorece el mantenimiento y la escalabilidad de la suite.

3. **Datos de prueba dependientes de estado:** Los módulos de consulta (HU-002, HU-004, HU-006, HU-013) dependen de datos creados por los módulos de registro. La suite asume un orden de ejecución implícito que debe estar documentado y controlado para garantizar la reproducibilidad.

---

## 8. CONCLUSIONES

- La suite de pruebas automatizadas con Serenity BDD valida exitosamente los **8 módulos funcionales** del sistema de gestión de seguros con una cobertura del **100%** de casos ejecutados.
- No se detectaron defectos en ninguno de los flujos de negocio cubiertos.
- El rendimiento de la suite es óptimo, con un tiempo promedio de ejecución de **2s por caso** y un tiempo total inferior a **2 minutos**, lo que la hace viable para integración en pipelines de CI/CD.
- Se recomienda actualizar la dependencia de `selenium-devtools` para eliminar las advertencias de CDP y garantizar compatibilidad futura con versiones del navegador.

---

## 9. RECOMENDACIONES

| Prioridad | Recomendación                                                                                          |
|:---------:|--------------------------------------------------------------------------------------------------------|
| Alta      | Actualizar dependencia `selenium-devtools-v146` para eliminar advertencias de CDP                      |
| Media     | Documentar formalmente el orden de ejecución de la suite para garantizar reproducibilidad              |
| Media     | Establecer datos de prueba independientes por módulo para desacoplar la dependencia entre HUs         |
| Baja      | Evaluar la incorporación de pruebas de regresión automatizadas en el pipeline de CI/CD                 |

---
