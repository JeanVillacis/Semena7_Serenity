@HU-001
Feature: HU-001 Registro de asegurado
  Como gestor de seguros
  Quiero registrar los datos personales de un asegurado en el sistema
  Para que pueda ser vinculado a un vehículo y una póliza de seguro

  Background:
    Given que el gestor está autenticado en el sistema
    And accede al módulo de asegurados

  @CP001-HU-001 @critico
  Scenario Outline: CP001 - Registro exitoso con todos los datos válidos
    When registra un asegurado con nombre "<nombre>", apellido "<apellido>", identificación "<identificacion>", dirección "<direccion>", teléfono "<telefono>" y correo "<correo>"
    Then el sistema crea el asegurado exitosamente
    And el asegurado "<nombre> <apellido>" queda disponible en el listado de asegurados

    Examples:
      | nombre | apellido | identificacion | direccion            | telefono   | correo                |
      | Juan   | Pérez    | 1712345678     | Av. Amazonas N36-152 | 0991234567 | juan.perez@correo.com |

  @CP002-HU-001 @alto
  Scenario Outline: CP002 - Intento de registro omitiendo un campo obligatorio
    When intenta registrar un asegurado con nombre "<nombre>", apellido "<apellido>", identificación "<identificacion>", dirección "<direccion>", teléfono "<telefono>" y correo "<correo>"
    Then el sistema notifica que el campo "<campo_omitido>" es obligatorio
    And no se crea ningún registro de asegurado

    Examples:
      | campo_omitido | nombre | apellido | identificacion | direccion            | telefono   | correo           |
      | nombre        |        | Pérez    | 1712345699     | Av. Amazonas N36-152 | 0991234567 | test1@correo.com |
      | apellido      | Juan   |          | 1712345699     | Av. Amazonas N36-152 | 0991234567 | test2@correo.com |
      | teléfono      | Juan   | Pérez    | 1712345699     | Av. Amazonas N36-152 |            | test3@correo.com |

  @CP003-HU-001 @alto
  Scenario: CP003 - Intento de registro sin completar ningún campo
    When omite todos los datos obligatorios y confirma el registro del asegurado
    Then el sistema notifica todos los campos obligatorios faltantes
    And no se crea ningún registro de asegurado

  @CP004-HU-001 @critico
  Scenario Outline: CP004 - Intento de registro con identificación ya existente
    When registra un asegurado con nombre "<nombre>", apellido "<apellido>", identificación "<identificacion>", dirección "<direccion>", teléfono "<telefono>" y correo "<correo>"
    Then el sistema notifica que ya existe un asegurado con esa identificación
    And no se crea un nuevo registro

    Examples:
      | nombre | apellido | identificacion | direccion        | telefono   | correo             |
      | María  | López    | 1712345678     | Calle Loja S3-20 | 0965432109 | maria.l@correo.com |

  @CP005-HU-001 @alto
  Scenario Outline: CP005 - Intento de registro con correo electrónico inválido
    When intenta registrar un asegurado con nombre "<nombre>", apellido "<apellido>", identificación "<identificacion>", dirección "<direccion>", teléfono "<telefono>" y correo "<correo>"
    Then el sistema notifica que el formato de correo electrónico no es válido
    And no se crea ningún registro de asegurado

    Examples:
      | nombre | apellido | identificacion | direccion            | telefono   | correo          |
      | Juan   | Pérez    | 1712345699     | Av. Amazonas N36-152 | 0991234567 | juan.perez-mail |
