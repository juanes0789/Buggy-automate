Feature: Registro de usuario en Buggy Cars Rating

  Scenario: Registro exitoso con datos validos
    Given el usuario se encuentra en la pagina principal de Buggy Cars Rating
    When el usuario abre el formulario de registro
    And diligencia el formulario con un usuario nuevo nombre "Juan" apellido "Perez" y password "Test1234!"
    Then el sistema muestra un mensaje de registro exitoso

  Scenario: Registro fallido con usuario ya existente
    Given el usuario se encuentra en la pagina principal de Buggy Cars Rating
    When el usuario abre el formulario de registro
    And diligencia el formulario con username "testBuggy2026" nombre "Juan" apellido "Perez" y password "Test1234!"
    Then el sistema muestra un mensaje de error de registro

  Scenario: Registro fallido con campos vacios
    Given el usuario se encuentra en la pagina principal de Buggy Cars Rating
    When el usuario abre el formulario de registro
    And diligencia el formulario con username "" nombre "" apellido "" y password ""
    Then el boton de registro debe estar deshabilitado
