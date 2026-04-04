@HU-006
Feature: HU-006 Consultar pólizas
  Como gestor de seguros
  Quiero consultar pólizas registradas y ver el detalle de una específica
  Para verificar estado, vigencia y vehículo asociado

  Background:
    Given que el gestor está autenticado en el sistema
    And accede al módulo de pólizas

  @CP001-HU-006 @alto
  Scenario: CP001 - Visualización del listado de pólizas registradas
    When consulta las pólizas registradas
    Then el sistema presenta un listado con las pólizas registradas
    And muestra número de póliza, asegurado, estado y fechas de vigencia

  @CP002-HU-006 @alto
  Scenario: CP002 - Consulta del detalle de una póliza existente
    When consulta las pólizas registradas
    Then el sistema muestra el detalle de la póliza "POL-2026-001"
    And se muestra el asegurado "Juan" y el vehículo asociado

  @CP003-HU-006 @medio
  Scenario: CP003 - Consulta de una póliza que no existe
    When consulta las pólizas registradas
    Then la póliza "POL-0000-000" no aparece en el listado
