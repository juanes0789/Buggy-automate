# language: es
@login
Característica: Inicio y cierre de sesion en Buggy Cars Rating
  Como usuario registrado de Buggy Cars Rating
  Quiero iniciar y cerrar sesion
  Para acceder a las opciones de votar y comentar de forma segura

  @smoke @ruta-feliz
  Escenario: Login exitoso con credenciales validas
    Dado que existe un usuario registrado con nombre "Ana"
    Y el usuario se encuentra en la pagina principal de Buggy Cars Rating
    Cuando inicia sesion con sus credenciales
    Entonces la barra de navegacion muestra el saludo "Hi, Ana"
    Y se muestra la opcion para cerrar sesion

  @camino-excepcional
  Escenario: Login fallido con un password incorrecto
    Dado que existe un usuario registrado con nombre "Ana"
    Y el usuario se encuentra en la pagina principal de Buggy Cars Rating
    Cuando inicia sesion con su username y el password "ClaveIncorrecta1!"
    Entonces el sistema muestra el mensaje de error de autenticacion "Invalid username/password"

  @camino-excepcional
  Escenario: Login fallido con un usuario que no existe
    Dado que el usuario se encuentra en la pagina principal de Buggy Cars Rating
    Cuando ingresa el username "usuarioQueNoExiste2026" y password "claveIncorrecta"
    Entonces el sistema muestra el mensaje de error de autenticacion "Invalid username/password"

  @camino-alterno
  Escenario: Cerrar sesion
    Dado que existe un usuario registrado con nombre "Ana"
    Y el usuario se encuentra en la pagina principal de Buggy Cars Rating
    Y inicia sesion con sus credenciales
    Cuando cierra la sesion
    Entonces se muestra nuevamente el formulario de inicio de sesion
