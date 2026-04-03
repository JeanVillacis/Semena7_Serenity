# language: es
@HU-002
Característica: HU-002 Consultar asegurados
  Como gestor de seguros
  Quiero consultar asegurados registrados en el sistema
  Para verificar su información antes de vincularlos a pólizas o vehículos

  Antecedentes:
    Dado que el gestor está autenticado en el sistema
    Y accede al módulo de asegurados

  @CP001-HU-002 @alto
  Escenario: CP001 - Visualización del listado con asegurados registrados
    Cuando consulta el listado de asegurados
    Entonces el sistema presenta un listado con los asegurados registrados
    Y cada fila muestra nombre, identificación, correo y teléfono

  @CP002-HU-002 @alto
  Esquema del escenario: CP002 - Consulta del detalle de un asegurado existente
    Cuando busca al asegurado con identificación "<identificacion>"
    Entonces el sistema muestra al asegurado "<nombre>" en los resultados
    Y el sistema muestra la información del asegurado con identificación "<identificacion>"

    Ejemplos:
      | nombre     | identificacion |
      | Juan Pérez | 1712345678     |

  @CP003-HU-002 @medio
  Esquema del escenario: CP003 - Consulta de un asegurado que no existe
    Cuando busca un asegurado con identificación "<identificacion>" que no existe en el sistema
    Entonces el sistema no muestra ningún asegurado en los resultados

    Ejemplos:
      | identificacion |
      | 0000000000     |
      | 9999999999     |
