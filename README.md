# Buggy Cars Rating - Pruebas E2E con Screenplay Pattern

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Gradle](https://img.shields.io/badge/Gradle-9.x-02303A.svg)](https://gradle.org/)
[![Serenity BDD](https://img.shields.io/badge/Serenity_BDD-4.1.0-brightgreen.svg)](https://serenity-bdd.github.io/)
[![Cucumber](https://img.shields.io/badge/Gherkin-English-23D96C.svg)](https://cucumber.io/docs/gherkin/reference/)
[![Screenplay](https://img.shields.io/badge/Pattern-Screenplay-blue.svg)](https://serenity-bdd.github.io/docs/screenplay/screenplay_fundamentals)

Proyecto académico de automatización de pruebas End-to-End (E2E) sobre la aplicación web pública [Buggy Cars Rating](https://buggy.justtestit.org/), desarrollado con el **Patrón Screenplay** sobre **Serenity BDD + Cucumber + Selenium WebDriver**.

> Trabajo final del curso de **Certificación en Pruebas Automatizadas** – Facultad de Ingeniería, **Universidad de Antioquia (UdeA)** – Semestre 2026-2.

---

## Tabla de contenido

1. [Acerca del proyecto](#acerca-del-proyecto)
2. [Cómo cubre el proyecto lo que pide el taller](#cómo-cubre-el-proyecto-lo-que-pide-el-taller)
3. [Stack tecnológico](#stack-tecnológico)
4. [Patrón Screenplay](#patrón-screenplay)
5. [Arquitectura y estructura del proyecto](#arquitectura-y-estructura-del-proyecto)
6. [Requisitos previos](#requisitos-previos)
7. [Ejecución de las pruebas](#ejecución-de-las-pruebas)
8. [Escenarios implementados](#escenarios-implementados)
9. [Principios F.I.R.S.T](#principios-first)
10. [Reportes de Serenity](#reportes-de-serenity)
11. [Decisiones técnicas](#decisiones-técnicas)
12. [Defectos encontrados en Buggy Cars](#defectos-encontrados-en-buggy-cars)
13. [Solución de problemas](#solución-de-problemas)
14. [Autor](#autor)

---

## Acerca del proyecto

Se escogió el sitio **#7 del taller: Buggy Cars Rating**, una *Single Page Application* hecha en Angular donde los usuarios votan y comentan modelos de autos. El reto pedía:

> *Realizar una E2E con un SignUp y un SignIn. Para luego seleccionar un auto y dejar comentarios al respecto; validando que hubieran quedado registrados dichos comentarios.*

La batería cubre tres funcionalidades, cada una con su ruta feliz y sus caminos alternos y excepcionales:

- **Registro** de usuarios (`register.feature`).
- **Inicio y cierre de sesión** (`login.feature`).
- **Votación y comentarios** sobre un modelo, incluido el flujo E2E completo (`comment.feature`).

---

## Cómo cubre el proyecto lo que pide el taller

| Lo que pide el taller | Dónde está |
|---|---|
| Proyecto base generado con el scaffold `ScreenplayArchitecture` | Plugin `io.github.jumosqu12.screenplayarchitecture` en `build.gradle`. Los paquetes `exceptions`, `integrations`, `interactions`, `models`, `questions`, `tasks`, `userinterfaces` y `utils` salen del scaffold |
| Escenarios en archivos **Feature** | `src/test/resources/features/*.feature`, en Gherkin en inglés, con historia de usuario, `Background`, `Scenario Outline` y tags |
| **Actor** principal definido en el StepDefinition | `Hooks.java` prepara el escenario con `OnStage` + `OnlineCast`, y el actor `"Usuario"` recibe la habilidad `BrowseTheWeb` |
| Ruta feliz, caminos alternos y excepcionales | Tags `@happy-path`, `@alternate-path`, `@exception-path` y `@boundary-values` |
| **Tasks** en términos del negocio | `tasks/`: `RegisterUser`, `OpenRegistrationForm`, `FillRegistrationForm`, `SubmitRegistration`, `LoginAs`, `Logout`, `NavigateToModel`, `WriteComment`, `SubmitVote`, `OpenHomePage` |
| **Interactions** ("la menuda") | `interactions/`: `TypeIntoAngularField`, `RefreshPage`, `Wait`, junto con `Click`, `Enter`, `Clear`, `Hit` y `WaitUntil` de Serenity |
| **Questions** para verificar | `questions/`: `TheRegistrationMessage`, `TheValidationAlerts`, `TheRegisterButton`, `TheUserName`, `TheLoginError`, `TheCommentList`, `TheVoteCount`, `TheVoteStatus` |
| **StepDefinitions** y **Runner** | `stepdefinitions/` (uno por feature, más `CommonStepDefinitions` y `Hooks`) y `runners/Runner.java` |
| **Reporte** de la automatización | Serenity genera `target/site/serenity/index.html` automáticamente después de `test` |
| Pausa entre pasos para observar la prueba | Interacción `Wait`: queda en el reporte como *"Usuario hace una pausa de N segundos para observar la prueba"* |

---

## Stack tecnológico

| Componente               | Versión   | Rol                                                               |
|--------------------------|-----------|-------------------------------------------------------------------|
| Java                     | 17        | Lenguaje base (se usan `record`, `switch` con flechas y `Stream.toList()`) |
| Gradle (wrapper)         | 9.9.1     | Compilación y ejecución                                           |
| Serenity BDD             | 4.1.0     | Framework de automatización y reportes                            |
| Serenity Screenplay      | 4.1.0     | Implementación del patrón Screenplay                              |
| Serenity Cucumber        | 4.1.0     | Integración de Serenity con Cucumber                              |
| Selenium WebDriver       | (vía Serenity) | Automatización del navegador                                 |
| WebDriverManager         | 5.4.1     | Descarga automática del driver del navegador                      |
| JUnit                    | 4.13.2    | Runner de pruebas                                                 |
| Hamcrest                 | 1.3       | Matchers para las verificaciones                                  |

---

## Patrón Screenplay

El **Screenplay Pattern** describe las pruebas como una obra de teatro:

- **Actor**: quien usa el sistema (`OnStage.theActorCalled("Usuario")`).
- **Abilities**: lo que el actor puede hacer (`BrowseTheWeb`, que entrega el `OnlineCast`).
- **Tasks**: acciones de negocio (`RegisterUser`, `LoginAs`, `WriteComment`, `SubmitVote`).
- **Interactions**: acciones atómicas sobre la UI (`Click`, `Enter`, `TypeIntoAngularField`, `RefreshPage`).
- **Questions**: lo que el actor consulta para verificar (`TheVoteCount`, `TheCommentList`).
- **User Interfaces**: los localizadores (`HomePage`, `RegisterPage`, `ModelDetailPage`).
- **Memoria del actor**: `actor.remember(...)` y `actor.recall(...)` pasan datos entre pasos, por ejemplo el usuario creado o los votos antes de votar.

Las Tasks se arman con otras Tasks. Por ejemplo, `RegisterUser` = `OpenRegistrationForm` + `FillRegistrationForm` + `SubmitRegistration`. Así un mismo flujo sirve como paso de un escenario o como precondición de otro.

---

## Arquitectura y estructura del proyecto

```
BuggyCarsRating/
├── build.gradle                 # Dependencias, plugins y paso de propiedades -D a las pruebas
├── serenity.properties          # Encoding del reporte
├── gradlew / gradlew.bat        # Gradle wrapper
│
├── src/main/java/co/edu/udea/certification/buggycars/
│   ├── exceptions/              # AngularFieldNotUpdatedException, ExpectedResultNotFoundError
│   ├── interactions/            # TypeIntoAngularField, RefreshPage, Wait
│   ├── models/                  # User (record inmutable)
│   ├── questions/               # Verificaciones (8 Questions)
│   ├── tasks/                   # Tareas de negocio (10 Tasks)
│   ├── userinterfaces/          # HomePage, RegisterPage, ModelDetailPage (Targets)
│   └── utils/                   # Constants, TestData, ActorMemory
│
└── src/test/
    ├── java/co/edu/udea/certification/buggycars/
    │   ├── runners/Runner.java
    │   └── stepdefinitions/     # Hooks + un StepDefinitions por feature
    └── resources/
        ├── features/            # register.feature, login.feature, comment.feature
        └── serenity.conf        # WebDriver, capturas y tiempos de espera
```

---

## Requisitos previos

- **Java 17** (JDK). Se verifica con `java -version`.
- **Google Chrome** instalado. El driver se descarga solo (`webdriver.autodownload = true`).
- Conexión a internet para acceder a `https://buggy.justtestit.org/`.
- No hace falta instalar Gradle: el proyecto trae el wrapper.

---

## Ejecución de las pruebas

```bash
# Todos los escenarios, con las pausas de demo (modo exposición)
./gradlew clean test            # Linux / Mac
gradlew.bat clean test          # Windows

# Sin pausas de demo: ejecución rápida (principio Fast)
./gradlew clean test -Ddemo.pausa=false

# Solo un grupo de escenarios, por tag
./gradlew clean test -Dcucumber.filter.tags="@e2e"
./gradlew clean test -Dcucumber.filter.tags="@smoke"
./gradlew clean test -Dcucumber.filter.tags="@boundary-values"
./gradlew clean test -Dcucumber.filter.tags="@registration and not @boundary-values"

# Un solo feature
./gradlew clean test -Dcucumber.features=src/test/resources/features/comment.feature

# Sin abrir la ventana del navegador
./gradlew clean test -Dheadless.mode=true
```

El reporte agregado de Serenity se genera solo al terminar (`test.finalizedBy(aggregate)`).

---

## Escenarios implementados

**16 escenarios** en total (las filas de cada `Scenario Outline` cuentan como escenarios).

### `register.feature` — Registro (10)

| Escenario | Tipo | Resultado esperado |
|---|---|---|
| Registro exitoso con datos válidos | Ruta feliz | `Registration is successful` |
| Registro con un username que ya existe | Excepcional | `UsernameExistsException: User already exists` |
| Password con menos de 6 caracteres (`Ab1!x`) | Límite | `minimum field size of 6` |
| Password con menos de 8 caracteres (`Ab1!xy`) | Límite | `Password not long enough` |
| Password sin mayúsculas | Excepcional | `Password must have uppercase characters` |
| Password sin caracteres especiales | Excepcional | `Password must have symbol characters` |
| Password y confirmación distintos | Alterno | Alerta `Passwords do not match` y botón deshabilitado |
| Campos vacíos | Alterno | Botón **Register** deshabilitado |
| Username de 50 caracteres | Límite | Botón habilitado (máximo permitido) |
| Username de 51 caracteres | Límite | Botón deshabilitado |

### `login.feature` — Inicio y cierre de sesión (4)

| Escenario | Tipo | Resultado esperado |
|---|---|---|
| Login exitoso | Ruta feliz | Saludo `Hi, Ana` y opción **Logout** |
| Password incorrecto para un usuario existente | Excepcional | `Invalid username/password` |
| Usuario que no existe | Excepcional | `Invalid username/password` |
| Cerrar sesión | Alterno | Vuelve a aparecer el formulario de login |

### `comment.feature` — Votación y comentarios (2)

| Escenario | Tipo | Resultado esperado |
|---|---|---|
| **E2E:** usuario nuevo se registra, inicia sesión, entra al ranking general, vota y comenta | Ruta feliz | El comentario aparece en la lista, el conteo de votos sube, sale `Thank you for your vote!` y el formulario de voto desaparece (no se puede votar dos veces) |
| Visitante sin sesión intenta votar | Excepcional | `You need to be logged in to vote.` y no hay formulario de voto |

---

## Principios F.I.R.S.T

| Principio | Cómo se aplica |
|---|---|
| **Fast** | Las pausas de demo se apagan con `-Ddemo.pausa=false`. La sincronización real con la página usa `WaitUntil`, no pausas fijas. Con `-Dcucumber.filter.tags` se corre solo lo necesario |
| **Independent** | Cada escenario abre un navegador nuevo (`restart.browser.for.each = scenario`) y crea sus propios datos. Ningún escenario depende de otro ni de usuarios creados a mano en el ambiente |
| **Repeatable** | Los usernames y comentarios llevan un identificador único (`TestData`), así que el resultado es el mismo en cada ejecución |
| **Self-validating** | Cada escenario termina con `seeThat(...)` sobre una Question. Las verificaciones del E2E usan `orComplainWith(ExpectedResultNotFoundError.class, ...)` para que el reporte explique el fallo con un mensaje de negocio |
| **Timely** | Los criterios de aceptación se escribieron en Gherkin antes de implementar las Tasks |

---

## Reportes de Serenity

- **Ubicación**: [`target/site/serenity/index.html`](target/site/serenity/index.html)
- Captura de pantalla después de cada paso (`take.screenshots = AFTER_EACH_STEP`).
- Los pasos del Gherkin aparecen en inglés, y debajo de cada uno los `@Step` de las Tasks e Interactions y los `@Subject` de las Questions lo detallan en español.
- Los tags (`@e2e`, `@smoke`, `@boundary-values`...) aparecen en el reporte y sirven para filtrar.
- Reporte de accesibilidad activado (`report.accessibility = true`).

```bash
open target/site/serenity/index.html          # Mac
start target\site\serenity\index.html         # Windows
xdg-open target/site/serenity/index.html      # Linux
```

---

## Decisiones técnicas

- **Campo de comentario en Angular.** El `textarea` del comentario está enlazado con `ngModel`, y un `sendKeys` normal no actualiza el modelo de Angular. La interacción `TypeIntoAngularField` usa `document.execCommand('insertText')`, que dispara los eventos `input` nativos, y luego verifica que el campo haya quedado con el texto. Si no, lanza `AngularFieldNotUpdatedException`.
- **Comentario único por ejecución.** El sitio es compartido y ya tiene muchas veces el mismo texto de comentario de corridas anteriores. Por eso cada ejecución agrega un identificador (`... ref 1795031085116`) y verifica *ese* comentario. Sin esto, la prueba pasaría aunque el voto fallara.
- **Recarga después de votar.** Buggy Cars no actualiza la pantalla cuando se vota. `RefreshPage` recarga la página (con reintentos) hasta que aparece el mensaje del voto guardado.
- **Un voto por usuario y modelo.** Como el sitio solo permite votar una vez, el E2E registra un usuario nuevo en cada ejecución.
- **Pausas de demo separadas de la sincronización.** `Wait` solo sirve para observar la prueba, y la espera real la hace `WaitUntil` en cada Task. Por eso apagar las pausas no rompe las pruebas.
- **Localizadores acotados.** Los `Target` se buscan dentro del componente (`//my-login//...`, `//my-model//...`) para no confundir elementos parecidos de otras partes de la página.

---

## Defectos encontrados en Buggy Cars

| Defecto | Cómo se encontró | Evidencia |
|---|---|---|
| **El modelo más popular (Diablo) ya no guarda comentarios nuevos.** El servidor responde `200 OK` y cuenta el voto, pero descarta el texto sin avisar. El registro del modelo tiene 3692 comentarios (unos 335 KB), cerca del límite de tamaño de la base de datos | La Question `TheCommentList` falló aunque `TheVoteCount` mostraba que el voto sí se contó | `GET /prod/models/{id}` → `votes` sube en cada ejecución, `comments` no cambia. Por eso el E2E vota por el **segundo** modelo del ranking general |

> Este hallazgo también mostró que la versión anterior del escenario daba un **falso positivo**: buscaba un texto fijo que ya estaba en la tabla por ejecuciones viejas, así que pasaba aunque el comentario nuevo no se guardara.

---

## Solución de problemas

| Síntoma | Posible causa | Solución |
|---|---|---|
| `permission denied: ./gradlew` | El archivo perdió el permiso de ejecución | `chmod +x gradlew` |
| `SessionNotCreatedException` al abrir Chrome | Versión de Chrome distinta a la del driver | Actualizar Chrome; WebDriverManager descarga el driver compatible |
| Falla por tiempo de espera | El sitio público está lento | Volver a ejecutar; los tiempos están en `Constants.TIMEOUT_SECONDS` y `serenity.conf` |
| Reporte vacío | No corrió la tarea `aggregate` | `./gradlew aggregate` |

---

## Autor

**Mateo Reyes U.**
Estudiante de Ingeniería – Universidad de Antioquia
Curso: *Certificación en Pruebas Automatizadas* — 2026-2

- Email: mateo.reyesu@udea.edu.co
- GitHub: [@mateor32](https://github.com/mateor32)

---

## Licencia

Proyecto académico sin fines comerciales. Uso libre con fines educativos.
