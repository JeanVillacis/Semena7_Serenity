@HU-004
Feature: HU-004 Consultar vehículos
  Como gestor de seguros
  Quiero consultar la lista de vehículos y ver el detalle de uno específico
  Para verificar datos antes de asociar una póliza

  Background:
    Given que el gestor está autenticado en el sistema
    And accede al módulo de vehículos

  @CP001-HU-004 @alto
  Scenario: CP001 - Visualización del listado de vehículos registrados
    When consulta el listado de vehículos
    Then el sistema presenta un listado con los vehículos registrados
    And cada fila muestra marca, modelo, año y placa

  @CP002-HU-004 @alto
  Scenario Outline: CP002 - Consulta del detalle de un vehículo existente
    When busca el vehículo con placa "<placa>"
    Then el sistema muestra el vehículo "<marca> <modelo>" con placa "<placa>"

    Examples:
      | marca     | modelo | placa    |
      | Chevrolet | Aveo   | PBA-1234 |

  @CP003-HU-004 @medio
  Scenario Outline: CP003 - Consulta de un vehículo que no existe
    When busca un vehículo con placa "<placa>" que no existe en el sistema
    Then el sistema no muestra ningún vehículo en los resultados

    Examples:
      | placa    |
      | ZZZ-0000 |
