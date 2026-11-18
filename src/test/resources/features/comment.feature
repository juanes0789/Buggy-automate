# language: es
@votacion
Característica: Votar y comentar un modelo de auto
  Como usuario registrado de Buggy Cars Rating
  Quiero votar por un modelo y dejar un comentario
  Para compartir mi opinion con otros usuarios

  @e2e @smoke @ruta-feliz
  Escenario: Un usuario nuevo se registra, inicia sesion, vota y comenta un modelo
    Dado que el usuario se registra e inicia sesion con una cuenta nueva
    Cuando navega a un modelo del ranking general
    Y escribe el comentario "Excelente auto para pruebas automatizadas UdeA 2026"
    Y envia el voto
    Entonces el comentario queda registrado en la lista de comentarios del modelo
    Y el conteo de votos del modelo aumenta
    Y el sistema muestra el mensaje de votacion "Thank you for your vote!"
    Y ya no se muestra el formulario para votar

  @camino-excepcional
  Escenario: Un visitante sin sesion no puede votar
    Dado que el usuario se encuentra en la pagina principal de Buggy Cars Rating
    Cuando navega a un modelo del ranking general
    Entonces el sistema muestra el mensaje de votacion "You need to be logged in to vote."
    Y no se muestra el formulario para votar
