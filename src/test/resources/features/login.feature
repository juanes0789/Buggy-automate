Feature: Inicio de sesion en Buggy Cars Rating

  Scenario: Login exitoso con credenciales validas
    Given el usuario se encuentra en la pagina principal de Buggy Cars Rating
    When ingresa el username "testBuggy2026" y password "Test1234!"
    Then el sistema muestra el nombre del usuario en la barra de navegacion

  Scenario: Login fallido con credenciales invalidas
    Given el usuario se encuentra en la pagina principal de Buggy Cars Rating
    When ingresa el username "usuarioFalso" y password "claveIncorrecta"
    Then el sistema muestra un mensaje de error de autenticacion
