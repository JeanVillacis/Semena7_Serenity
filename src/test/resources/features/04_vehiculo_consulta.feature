# language: es
@HU-004
Característica: HU-004 Consultar vehículos
  Como gestor de seguros
  Quiero consultar la lista de vehículos y ver el detalle de uno específico
  Para verificar datos antes de asociar una póliza

  Antecedentes:
    Dado que el gestor está autenticado en el sistema
    Y accede al módulo de vehículos

  @CP001-HU-004 @alto
  Escenario: CP001 - Visualización del listado de vehículos registrados
    Cuando consulta el listado de vehículos
    Entonces el sistema presenta un listado con los vehículos registrados
    Y cada fila muestra marca, modelo, año y placa

  @CP002-HU-004 @alto
  Esquema del escenario: CP002 - Consulta del detalle de un vehículo existente
    Cuando busca el vehículo con placa "<placa>"
    Entonces el sistema muestra el vehículo "<marca> <modelo>" con placa "<placa>"

    Ejemplos:
      | marca     | modelo | placa    |
      | Chevrolet | Aveo   | PBA-1234 |

  @CP003-HU-004 @medio
  Esquema del escenario: CP003 - Consulta de un vehículo que no existe
    Cuando busca un vehículo con placa "<placa>" que no existe en el sistema
    Entonces el sistema no muestra ningún vehículo en los resultados

    Ejemplos:
      | placa    |
      | ZZZ-0000 |
