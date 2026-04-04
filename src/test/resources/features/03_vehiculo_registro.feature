@HU-003
Feature: HU-003 Registrar vehículo
  Como gestor de seguros
  Quiero registrar los datos de un vehículo en el sistema
  Para que pueda ser asociado a una póliza de seguro

  Background:
    Given que el gestor está autenticado en el sistema
    And accede al módulo de vehículos

  @CP001-HU-003 @critico
  Scenario Outline: CP001 - Registro exitoso de vehículo con datos válidos
    When registra un vehículo con marca "<marca>", modelo "<modelo>", año "<anio>" y placa "<placa>"
    Then el sistema registra el vehículo exitosamente
    And el vehículo "<marca> <modelo>" con placa "<placa>" queda registrado

    Examples:
      | marca     | modelo | anio | placa    |
      | Chevrolet | Aveo   | 2022 | PBA-1234 |

  @CP002-HU-003 @alto
  Scenario Outline: CP002 - Intento de registro omitiendo un campo obligatorio
    When intenta registrar un vehículo con marca "<marca>", modelo "<modelo>", año "<anio>" y placa "<placa>"
    Then el sistema notifica que el campo "<campo_omitido>" del vehículo es obligatorio
    And no se crea ningún registro de vehículo

    Examples:
      | campo_omitido | marca     | modelo | anio | placa    |
      | marca         |           | Aveo   | 2022 | SER-0002 |
      | modelo        | Chevrolet |        | 2022 | SER-0003 |
      | placa         | Chevrolet | Aveo   | 2022 |          |

  @CP003-HU-003 @critico
  Scenario Outline: CP003 - Intento de registro con placa ya existente
    When intenta registrar un vehículo con marca "<marca>", modelo "<modelo>", año "<anio>" y placa "<placa>"
    Then el sistema notifica que ya existe un vehículo con esa placa
    And no se crea ningún registro de vehículo

    Examples:
      | marca | modelo | anio | placa    |
      | Kia   | Rio    | 2023 | PBA-1234 |

  @CP004-HU-003 @alto
  Scenario Outline: CP004 - Intento de registro con formato de datos inválido
    When intenta registrar un vehículo con marca "<marca>", modelo "<modelo>", año "<anio>" y placa "<placa>"
    Then el sistema notifica que el campo año tiene formato inválido
    And no se crea ningún registro de vehículo

    Examples:
      | marca     | modelo | anio      | placa    |
      | Chevrolet | Aveo   | veintidos | SER-0004 |
