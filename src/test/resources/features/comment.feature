Feature: Votar y comentar un modelo de auto

  Scenario: Dejar un comentario en un modelo de auto
    Given el usuario se registra y hace login con un usuario nuevo
    When navega a la pagina de un modelo de auto
    And escribe el comentario "Excelente auto para pruebas automatizadas UdeA 2026"
    And envia el voto
    Then el comentario "Excelente auto para pruebas automatizadas UdeA 2026" queda visible en la lista de comentarios
