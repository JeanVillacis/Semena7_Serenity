# language: es
@HU-005
Característica: HU-005 Registrar póliza de seguro
  Como gestor de seguros
  Quiero registrar una póliza asociándola a un asegurado y a un vehículo
  Para que el asegurado pueda presentar reclamos contra esa póliza

  Antecedentes:
    Dado que el gestor está autenticado en el sistema
    Y accede al módulo de pólizas

  @CP001-HU-005 @critico
  Escenario: CP001 - Registro exitoso de póliza con datos válidos
    Cuando registra una póliza "POL-2026-001" seleccionando el primer asegurado y primer vehículo con valor "25000.00", inicio "2026-01-01" y fin "2027-12-31"
    Entonces el sistema registra la póliza exitosamente
    Y la póliza "POL-2026-001" aparece en el listado

  @CP003-HU-005 @critico
  Escenario: CP003 - Registro con número de póliza duplicado
    Cuando registra una póliza "POL-2026-001" seleccionando el primer asegurado y primer vehículo con valor "20000.00", inicio "2026-06-01" y fin "2027-05-31"
    Entonces el sistema notifica que ya existe una póliza con ese número
    Y no se crea la póliza

  @CP004-HU-005 @alto
  Escenario: CP004 - Registro con rango de vigencia inválido
    Cuando intenta registrar una póliza "POL-SER-002" con valor "25000.00", inicio "2026-06-01" y fin "2026-01-01"
    Entonces el sistema notifica que el rango de fechas no es válido
    Y no se crea la póliza

  @CP005-HU-005 @alto
  Esquema del escenario: CP005 - Registro con valor asegurado menor o igual a cero
    Cuando intenta registrar una póliza "<numero>" con valor asegurado "<valor_invalido>"
    Entonces el sistema notifica que el valor asegurado no es válido
    Y no se crea la póliza

    Ejemplos:
      | numero      | valor_invalido |
      | POL-SER-003 | 0              |
      | POL-SER-004 | -5000          |
