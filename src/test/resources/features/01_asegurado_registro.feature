# language: es
@HU-001
Característica: HU-001 Registro de asegurado
  Como gestor de seguros
  Quiero registrar los datos personales de un asegurado en el sistema
  Para que pueda ser vinculado a un vehículo y una póliza de seguro

  Antecedentes:
    Dado que el gestor está autenticado en el sistema
    Y accede al módulo de asegurados

  @CP001-HU-001 @critico
  Esquema del escenario: CP001 - Registro exitoso con todos los datos válidos
    Cuando registra un asegurado con nombre "<nombre>", apellido "<apellido>", identificación "<identificacion>", dirección "<direccion>", teléfono "<telefono>" y correo "<correo>"
    Entonces el sistema crea el asegurado exitosamente
    Y el asegurado "<nombre> <apellido>" queda disponible en el listado de asegurados

    Ejemplos:
      | nombre | apellido | identificacion | direccion            | telefono   | correo                |
      | Juan   | Pérez    | 1712345678     | Av. Amazonas N36-152 | 0991234567 | juan.perez@correo.com |

  @CP002-HU-001 @alto
  Esquema del escenario: CP002 - Intento de registro omitiendo un campo obligatorio
    Cuando intenta registrar un asegurado con nombre "<nombre>", apellido "<apellido>", identificación "<identificacion>", dirección "<direccion>", teléfono "<telefono>" y correo "<correo>"
    Entonces el sistema notifica que el campo "<campo_omitido>" es obligatorio
    Y no se crea ningún registro de asegurado

    Ejemplos:
      | campo_omitido | nombre | apellido | identificacion | direccion            | telefono   | correo                |
      | nombre        |        | Pérez    | 1712345699     | Av. Amazonas N36-152 | 0991234567 | test1@correo.com      |
      | apellido      | Juan   |          | 1712345699     | Av. Amazonas N36-152 | 0991234567 | test2@correo.com      |
      | teléfono      | Juan   | Pérez    | 1712345699     | Av. Amazonas N36-152 |            | test3@correo.com      |

  @CP003-HU-001 @alto
  Escenario: CP003 - Intento de registro sin completar ningún campo
    Cuando omite todos los datos obligatorios y confirma el registro del asegurado
    Entonces el sistema notifica todos los campos obligatorios faltantes
    Y no se crea ningún registro de asegurado

  @CP004-HU-001 @critico
  Esquema del escenario: CP004 - Intento de registro con identificación ya existente
    Cuando registra un asegurado con nombre "<nombre>", apellido "<apellido>", identificación "<identificacion>", dirección "<direccion>", teléfono "<telefono>" y correo "<correo>"
    Entonces el sistema notifica que ya existe un asegurado con esa identificación
    Y no se crea un nuevo registro

    Ejemplos:
      | nombre | apellido | identificacion | direccion        | telefono   | correo             |
      | María  | López    | 1712345678     | Calle Loja S3-20 | 0965432109 | maria.l@correo.com |

  @CP005-HU-001 @alto
  Esquema del escenario: CP005 - Intento de registro con correo electrónico inválido
    Cuando intenta registrar un asegurado con nombre "<nombre>", apellido "<apellido>", identificación "<identificacion>", dirección "<direccion>", teléfono "<telefono>" y correo "<correo>"
    Entonces el sistema notifica que el formato de correo electrónico no es válido
    Y no se crea ningún registro de asegurado

    Ejemplos:
      | nombre | apellido | identificacion | direccion            | telefono   | correo          |
      | Juan   | Pérez    | 1712345699     | Av. Amazonas N36-152 | 0991234567 | juan.perez-mail |
