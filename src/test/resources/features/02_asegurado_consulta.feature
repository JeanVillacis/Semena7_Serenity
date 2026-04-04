@HU-002
Feature: HU-002 Consultar asegurados
  Como gestor de seguros
  Quiero consultar asegurados registrados en el sistema
  Para verificar su información antes de vincularlos a pólizas o vehículos

  Background:
    Given que el gestor está autenticado en el sistema
    And accede al módulo de asegurados

  @CP001-HU-002 @alto
  Scenario: CP001 - Visualización del listado con asegurados registrados
    When consulta el listado de asegurados
    Then el sistema presenta un listado con los asegurados registrados
    And cada fila muestra nombre, identificación, correo y teléfono

  @CP002-HU-002 @alto
  Scenario Outline: CP002 - Consulta del detalle de un asegurado existente
    When busca al asegurado con identificación "<identificacion>"
    Then el sistema muestra al asegurado "<nombre>" en los resultados
    And el sistema muestra la información del asegurado con identificación "<identificacion>"

    Examples:
      | nombre     | identificacion |
      | Juan Pérez | 1712345678     |

  @CP003-HU-002 @medio
  Scenario Outline: CP003 - Consulta de un asegurado que no existe
    When busca un asegurado con identificación "<identificacion>" que no existe en el sistema
    Then el sistema no muestra ningún asegurado en los resultados

    Examples:
      | identificacion |
      | 0000000000     |
      | 9999999999     |
