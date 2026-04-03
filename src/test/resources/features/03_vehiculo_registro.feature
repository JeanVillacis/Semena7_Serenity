# language: es
@HU-003
Característica: HU-003 Registrar vehículo
  Como gestor de seguros
  Quiero registrar los datos de un vehículo en el sistema
  Para que pueda ser asociado a una póliza de seguro

  Antecedentes:
    Dado que el gestor está autenticado en el sistema
    Y accede al módulo de vehículos

  @CP001-HU-003 @critico
  Esquema del escenario: CP001 - Registro exitoso de vehículo con datos válidos
    Cuando registra un vehículo con marca "<marca>", modelo "<modelo>", año "<anio>" y placa "<placa>"
    Entonces el sistema registra el vehículo exitosamente
    Y el vehículo "<marca> <modelo>" con placa "<placa>" queda registrado

    Ejemplos:
      | marca     | modelo | anio | placa    |
      | Chevrolet | Aveo   | 2022 | PBA-1234 |

  @CP002-HU-003 @alto
  Esquema del escenario: CP002 - Intento de registro omitiendo un campo obligatorio
    Cuando intenta registrar un vehículo con marca "<marca>", modelo "<modelo>", año "<anio>" y placa "<placa>"
    Entonces el sistema notifica que el campo "<campo_omitido>" del vehículo es obligatorio
    Y no se crea ningún registro de vehículo

    Ejemplos:
      | campo_omitido | marca     | modelo | anio | placa    |
      | marca         |           | Aveo   | 2022 | SER-0002 |
      | modelo        | Chevrolet |        | 2022 | SER-0003 |
      | placa         | Chevrolet | Aveo   | 2022 |          |

  @CP003-HU-003 @critico
  Esquema del escenario: CP003 - Intento de registro con placa ya existente
    Cuando intenta registrar un vehículo con marca "<marca>", modelo "<modelo>", año "<anio>" y placa "<placa>"
    Entonces el sistema notifica que ya existe un vehículo con esa placa
    Y no se crea ningún registro de vehículo

    Ejemplos:
      | marca | modelo | anio | placa    |
      | Kia   | Rio    | 2023 | PBA-1234 |

  @CP004-HU-003 @alto
  Esquema del escenario: CP004 - Intento de registro con formato de datos inválido
    Cuando intenta registrar un vehículo con marca "<marca>", modelo "<modelo>", año "<anio>" y placa "<placa>"
    Entonces el sistema notifica que el campo año tiene formato inválido
    Y no se crea ningún registro de vehículo

    Ejemplos:
      | marca     | modelo | anio      | placa    |
      | Chevrolet | Aveo   | veintidos | SER-0004 |
