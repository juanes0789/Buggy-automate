# language: es
@registro
Característica: Registro de usuarios en Buggy Cars Rating
  Como visitante de Buggy Cars Rating
  Quiero crear una cuenta
  Para poder votar y comentar los modelos de autos

  Antecedentes:
    Dado que el usuario se encuentra en la pagina principal de Buggy Cars Rating

  @smoke @ruta-feliz
  Escenario: Registro exitoso con datos validos
    Cuando abre el formulario de registro
    Y diligencia el formulario con un usuario nuevo, nombre "Juan", apellido "Perez" y password "Test1234!"
    Y envia el formulario de registro
    Entonces el sistema muestra el mensaje de registro "Registration is successful"

  @camino-excepcional
  Escenario: Registro fallido con un username que ya existe
    Dado que existe un usuario registrado con nombre "Ana"
    Cuando abre el formulario de registro
    Y diligencia el formulario con el mismo username del usuario existente
    Y envia el formulario de registro
    Entonces el sistema muestra el mensaje de registro "UsernameExistsException: User already exists"

  @camino-excepcional @valores-limite
  Esquema del escenario: Registro fallido cuando el password no cumple la politica (<regla>)
    Cuando abre el formulario de registro
    Y diligencia el formulario con un usuario nuevo, nombre "Juan", apellido "Perez" y password "<password>"
    Y envia el formulario de registro
    Entonces el sistema muestra el mensaje de registro "<mensaje>"

    Ejemplos:
      | regla                     | password | mensaje                                       |
      | menos de 6 caracteres     | Ab1!x    | minimum field size of 6                       |
      | menos de 8 caracteres     | Ab1!xy   | Password not long enough                      |
      | sin letras mayusculas     | abcdef1! | Password must have uppercase characters       |
      | sin caracteres especiales | Abcdef12 | Password must have symbol characters          |

  @camino-alterno
  Escenario: El password y su confirmacion no coinciden
    Cuando abre el formulario de registro
    Y diligencia el formulario con nombre "Juan", apellido "Perez", password "Test1234!" y confirmacion "Otra1234!"
    Entonces se muestra la alerta de validacion "Passwords do not match"
    Y el boton de registro queda deshabilitado

  @camino-alterno
  Escenario: No se puede registrar con los campos vacios
    Cuando abre el formulario de registro
    Y diligencia el formulario con username "", nombre "", apellido "" y password ""
    Entonces el boton de registro queda deshabilitado

  @valores-limite
  Esquema del escenario: Limite de longitud del username (<longitud> caracteres)
    Cuando abre el formulario de registro
    Y diligencia el formulario con un username de <longitud> caracteres
    Entonces el boton de registro queda <estado>

    Ejemplos:
      | longitud | estado        |
      | 50       | habilitado    |
      | 51       | deshabilitado |
