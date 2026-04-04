@HU-005
Feature: HU-005 Registrar póliza de seguro
  Como gestor de seguros
  Quiero registrar una póliza asociándola a un asegurado y a un vehículo
  Para que el asegurado pueda presentar reclamos contra esa póliza

  Background:
    Given que el gestor está autenticado en el sistema
    And accede al módulo de pólizas

  @CP001-HU-005 @critico
  Scenario: CP001 - Registro exitoso de póliza con datos válidos
    When registra una póliza "POL-2026-001" seleccionando el primer asegurado y primer vehículo con valor "25000.00", inicio "2026-01-01" y fin "2027-12-31"
    Then el sistema registra la póliza exitosamente
    And la póliza "POL-2026-001" aparece en el listado

  @CP003-HU-005 @critico
  Scenario: CP003 - Registro con número de póliza duplicado
    When registra una póliza "POL-2026-001" seleccionando el primer asegurado y primer vehículo con valor "20000.00", inicio "2026-06-01" y fin "2027-05-31"
    Then el sistema notifica que ya existe una póliza con ese número
    And no se crea la póliza

  @CP004-HU-005 @alto
  Scenario: CP004 - Registro con rango de vigencia inválido
    When intenta registrar una póliza "POL-SER-002" con valor "25000.00", inicio "2026-06-01" y fin "2026-01-01"
    Then el sistema notifica que el rango de fechas no es válido
    And no se crea la póliza

  @CP005-HU-005 @alto
  Scenario Outline: CP005 - Registro con valor asegurado menor o igual a cero
    When intenta registrar una póliza "<numero>" con valor asegurado "<valor_invalido>"
    Then el sistema notifica que el valor asegurado no es válido
    And no se crea la póliza

    Examples:
      | numero      | valor_invalido |
      | POL-SER-003 | 0              |
      | POL-SER-004 | -5000          |
