@HU-007
Feature: HU-007 Registrar Reclamo de Siniestro
  Como asegurado
  Quiero registrar un reclamo de siniestro adjuntando evidencia fotográfica
  Para iniciar el proceso de evaluación y cobertura por parte de la aseguradora

  Background:
    Given que el gestor está autenticado en el sistema
    And accede al módulo de nuevo reclamo

  @CP001-HU-007 @critico
  Scenario: CP001 - Registro exitoso de reclamo con datos completos y fotografía válida
    When registra un reclamo para la póliza "POL-2026-001" con fecha "2026-03-15", descripción "Colisión lateral", monto "3500.00" y ubicación "Av. Amazonas, Quito"
    And adjunta la fotografía válida "foto_siniestro.jpg"
    And confirma el registro
    Then el sistema muestra confirmación de reclamo enviado con éxito
    And asigna un número de seguimiento
    And el reclamo aparece como "REGISTRADO"

  @CP002-HU-007 @critico
  Scenario: CP002 - Intento de registro sin seleccionar póliza
    When intenta registrar un reclamo sin seleccionar ninguna póliza
    And confirma el registro
    Then el sistema notifica que es obligatorio seleccionar una póliza

  @CP003-HU-007 @alto
  Scenario Outline: CP003 - Intento de registro sin completar un campo obligatorio
    When intenta registrar un reclamo para la póliza "POL-2026-001" omitiendo el campo "<campo_omitido>"
    And adjunta la fotografía válida "foto_siniestro.jpg"
    And confirma el registro
    Then el sistema notifica que el campo "<campo_omitido>" es obligatorio
    And no se crea ningún registro de reclamo

    Examples:
      | campo_omitido |
      | descripcion   |
      | ubicacion     |

  @CP004-HU-007 @alto
  Scenario Outline: CP004 - Intento de registro con monto estimado inválido
    When intenta registrar un reclamo para la póliza "POL-2026-001" con monto estimado inválido "<monto_invalido>"
    And adjunta la fotografía válida "foto_siniestro.jpg"
    And confirma el registro
    Then el sistema notifica que el monto estimado no es válido
    And no se crea ningún registro de reclamo

    Examples:
      | monto_invalido |
      | 0              |
      | -500           |

  @CP005-HU-007 @alto
  Scenario Outline: CP005 - Intento de registro con fecha de incidente fuera de rango
    When intenta registrar un reclamo para la póliza "POL-2026-001" con fecha de incidente inválida "<fecha_invalida>"
    And adjunta la fotografía válida "foto_siniestro.jpg"
    And confirma el registro
    Then el sistema notifica que la fecha del incidente no es válida
    And no se crea ningún registro de reclamo

    Examples:
      | fecha_invalida |
      | 2030-12-25     |

  @CP006-HU-007 @alto
  Scenario: CP006 - Intento de registro con archivo en formato no permitido
    When completa el formulario con datos válidos para la póliza "POL-2026-001"
    And adjunta el archivo en formato no permitido "documento.pdf"
    Then el sistema notifica que solo se permiten imágenes

  @CP008-HU-007 @alto
  Scenario: CP008 - Intento de registro sin adjuntar ninguna fotografía
    When completa el formulario con datos válidos para la póliza "POL-2026-001"
    And no adjunta ninguna fotografía
    And confirma el registro
    Then el sistema notifica que es obligatorio adjuntar al menos una fotografía
    And no se crea ningún registro de reclamo
