@HU-013
Feature: HU-013 Consulta de estado de reclamo por el asegurado
  Como asegurado
  Quiero consultar el estado actual de mi reclamo y conocer el motivo de la decisión
  Para tener visibilidad del proceso sin necesidad de contactar a la aseguradora

  Background:
    Given que el gestor está autenticado en el sistema
    And accede al módulo de nuevo reclamo

  @CP001-HU-013 @alto
  Scenario: CP001 - Consulta de reclamo aprobado automáticamente
    When registra un reclamo para la póliza "POL-2026-001" con fecha "2026-03-15", descripción "Colisión evaluada aprobada", monto "3500.00" y ubicación "Av. Amazonas, Quito"
    And adjunta la fotografía válida "foto_siniestro.jpg"
    And confirma el registro
    And el actor recuerda el número de seguimiento del reclamo
    And navega a la consulta de estado del reclamo
    When consulta el estado del reclamo registrado
    Then el sistema muestra el estado "APROBADO"
    And se muestra el monto aprobado y el deducible aplicado

  @CP002-HU-013 @alto
  Scenario: CP002 - Consulta de reclamo en revisión manual
    When registra un reclamo para la póliza "POL-2026-001" con fecha "2026-03-15", descripción "Daño estructural elevado", monto "6000.00" y ubicación "Av. Amazonas, Quito"
    And adjunta la fotografía válida "foto_siniestro.jpg"
    And confirma el registro
    And el actor recuerda el número de seguimiento del reclamo
    And navega a la consulta de estado del reclamo
    When consulta el estado del reclamo registrado
    Then el sistema muestra el estado "EN REVISION MANUAL"
    And se muestra el mensaje de revisión manual

  @CP003-HU-013 @alto
  Scenario: CP003 - Consulta de reclamo descartado
    When registra un reclamo para la póliza "POL-2026-001" con fecha "2026-03-15", descripción "Rasguño mínimo descartado", monto "150.00" y ubicación "Av. Amazonas, Quito"
    And adjunta la fotografía válida "foto_siniestro.jpg"
    And confirma el registro
    And el actor recuerda el número de seguimiento del reclamo
    And navega a la consulta de estado del reclamo
    When consulta el estado del reclamo registrado
    Then el sistema muestra el estado "DESCARTADO"
    And se muestra el motivo del descarte
