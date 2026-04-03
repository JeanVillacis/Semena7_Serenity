# language: es
@HU-006
Característica: HU-006 Consultar pólizas
  Como gestor de seguros
  Quiero consultar pólizas registradas y ver el detalle de una específica
  Para verificar estado, vigencia y vehículo asociado

  Antecedentes:
    Dado que el gestor está autenticado en el sistema
    Y accede al módulo de pólizas

  @CP001-HU-006 @alto
  Escenario: CP001 - Visualización del listado de pólizas registradas
    Cuando consulta las pólizas registradas
    Entonces el sistema presenta un listado con las pólizas registradas
    Y muestra número de póliza, asegurado, estado y fechas de vigencia

  @CP002-HU-006 @alto
  Escenario: CP002 - Consulta del detalle de una póliza existente
    Cuando consulta las pólizas registradas
    Entonces el sistema muestra el detalle de la póliza "POL-2026-001"
    Y se muestra el asegurado "Juan" y el vehículo asociado

  @CP003-HU-006 @medio
  Escenario: CP003 - Consulta de una póliza que no existe
    Cuando consulta las pólizas registradas
    Entonces la póliza "POL-0000-000" no aparece en el listado
