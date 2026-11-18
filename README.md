# Buggy Cars Rating - Pruebas E2E con Screenplay Pattern

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Gradle](https://img.shields.io/badge/Gradle-8.x-02303A.svg)](https://gradle.org/)
[![Serenity BDD](https://img.shields.io/badge/Serenity_BDD-4.1.0-brightgreen.svg)](https://serenity-bdd.github.io/)
[![Cucumber](https://img.shields.io/badge/Cucumber-4.1.0-23D96C.svg)](https://cucumber.io/)
[![Screenplay](https://img.shields.io/badge/Pattern-Screenplay-blue.svg)](https://serenity-bdd.github.io/docs/screenplay/screenplay_fundamentals)

Proyecto académico de automatización de pruebas End-to-End (E2E) sobre la aplicación web pública [Buggy Cars Rating](https://buggy.justtestit.org/), desarrollado aplicando el **Patrón Screenplay** con **Serenity BDD + Cucumber + Selenium WebDriver**.

> Trabajo final del curso de **Certificación en Pruebas Automatizadas** – Facultad de Ingeniería, **Universidad de Antioquia (UdeA)** – Semestre 2026-2.

---

## Tabla de contenido

1. [Acerca del proyecto](#acerca-del-proyecto)
2. [Stack tecnológico](#stack-tecnológico)
3. [Patrón Screenplay](#patrón-screenplay)
4. [Arquitectura y estructura del proyecto](#arquitectura-y-estructura-del-proyecto)
5. [Requisitos previos](#requisitos-previos)
6. [Instalación](#instalación)
7. [Ejecución de las pruebas](#ejecución-de-las-pruebas)
8. [Escenarios implementados](#escenarios-implementados)
9. [Reportes de Serenity](#reportes-de-serenity)
10. [Configuración](#configuración)
11. [Buenas prácticas aplicadas](#buenas-prácticas-aplicadas)
12. [Solución de problemas](#solución-de-problemas)
13. [Autor](#autor)

---

## Acerca del proyecto

Este proyecto automatiza los principales flujos de usuario sobre el sitio de prácticas [Buggy Cars Rating](https://buggy.justtestit.org/), un *Single Page Application* construido con Angular que simula un portal de votación y comentarios sobre modelos de autos. La automatización cubre tres flujos críticos:

- **Registro** de nuevos usuarios (casos de éxito y error).
- **Inicio de sesión** con credenciales válidas e inválidas.
- **Interacción con un modelo**: navegación, votación y publicación de comentarios.

El objetivo pedagógico es poner en práctica el **Patrón Screenplay** como alternativa moderna al clásico *Page Object Model*, favoreciendo la legibilidad, el mantenimiento y la reutilización mediante una semántica centrada en el usuario (Actor).

---

## Stack tecnológico

| Componente               | Versión   | Rol                                                               |
|--------------------------|-----------|-------------------------------------------------------------------|
| Java                     | 17        | Lenguaje base del proyecto                                        |
| Gradle                   | 8.x       | Automatización de la compilación y ejecución                      |
| Serenity BDD             | 4.1.0     | Framework de automatización y generación de reportes              |
| Serenity Screenplay      | 4.1.0     | Implementación del patrón Screenplay                              |
| Serenity Cucumber        | 4.1.0     | Integración de Serenity con Cucumber para BDD                     |
| Cucumber                 | 4.1.0     | Definición de escenarios en Gherkin                               |
| Selenium WebDriver       | (vía Serenity) | Automatización de navegador                                  |
| WebDriverManager         | 5.4.1     | Descarga y gestión automática de los binarios del navegador       |
| JUnit                    | 4.13.2    | Runner de pruebas                                                 |
| Hamcrest                 | 1.3       | Matchers para aserciones expresivas                               |
| Lombok                   | 1.18.22   | Reducción de código boilerplate                                   |
| SLF4J Simple             | 2.0.5     | Logging                                                           |

Adicionalmente, el proyecto utiliza el plugin **[`io.github.jumosqu12.screenplayarchitecture`](https://plugins.gradle.org/plugin/io.github.jumosqu12.screenplayarchitecture) v2.0.1**, que genera el andamiaje estándar de carpetas y clases base para proyectos Screenplay.

---

## Patrón Screenplay

El **Screenplay Pattern** modela las pruebas como una obra de teatro:

- **Actor**: el usuario que ejecuta las acciones (p. ej. `Actor.named("usuario")`).
- **Abilities**: capacidades del actor (ej. `BrowseTheWeb.with(driver)`).
- **Tasks**: acciones de negocio de alto nivel (`LoginAs`, `RegisterUser`, `LeaveComment`).
- **Interactions**: operaciones atómicas sobre la UI (`Click`, `Enter`, `Wait`).
- **Questions**: consultas sobre el estado del sistema (`TheCommentList`, `TheRegistrationMessage`).
- **User Interfaces**: localizadores de elementos (`HomePage`, `RegisterPage`, `ModelDetailPage`).

Ventajas sobre *Page Object Model*:

- Mayor reutilización de código (tasks e interactions componibles).
- Lenguaje centrado en el **dominio y el usuario** en lugar de la página.
- Alineación natural con BDD (Given / When / Then → Tasks / Questions).

---

## Arquitectura y estructura del proyecto

```
BuggyCarsRating/
├── build.gradle                 # Configuración de dependencias y plugins
├── settings.gradle              # Nombre del proyecto Gradle
├── gradle.properties            # Propiedad `package` para el plugin Screenplay
├── serenity.properties          # Encoding del reporte
├── gradlew / gradlew.bat        # Gradle wrapper (Linux/Mac y Windows)
│
├── src/
│   ├── main/java/co/edu/udea/certification/buggycars/
│   │   ├── exceptions/          # Excepciones personalizadas
│   │   ├── integrations/        # Integraciones externas
│   │   ├── interactions/        # Interacciones atómicas (Wait, …)
│   │   ├── models/              # Modelos de datos
│   │   ├── questions/           # Preguntas Screenplay (verificaciones)
│   │   │   ├── TheCommentList.java
│   │   │   ├── TheRegistrationMessage.java
│   │   │   └── TheUserName.java
│   │   ├── tasks/               # Tareas de negocio
│   │   │   ├── OpenHomePage.java
│   │   │   ├── LoginAs.java
│   │   │   ├── RegisterUser.java
│   │   │   ├── NavigateToModel.java
│   │   │   └── LeaveComment.java
│   │   ├── userinterfaces/      # Localizadores de UI (Targets)
│   │   │   ├── HomePage.java
│   │   │   ├── RegisterPage.java
│   │   │   └── ModelDetailPage.java
│   │   └── utils/               # Utilidades
│   │
│   └── test/
│       ├── java/co/edu/udea/certification/buggycars/
│       │   ├── runners/
│       │   │   └── Runner.java                   # JUnit Runner de Cucumber
│       │   └── stepdefinitions/
│       │       └── BuggyCarStepDefinitions.java  # Mapeo Gherkin → Tasks
│       └── resources/
│           ├── features/                         # Archivos .feature (Gherkin)
│           │   ├── login.feature
│           │   ├── register.feature
│           │   └── comment.feature
│           └── serenity.conf                     # Config de WebDriver y Serenity
│
├── target/site/serenity/        # Reporte HTML generado tras cada ejecución
└── history/                     # Histórico de ejecuciones de Serenity
```

---

## Requisitos previos

Antes de ejecutar las pruebas asegúrate de contar con:

- **Java 17** (JDK). Verifica con `java -version`.
- **Gradle**: no es estrictamente necesario instalarlo, el proyecto incluye el **Gradle Wrapper** (`gradlew` / `gradlew.bat`).
- **Google Chrome** instalado (la configuración por defecto usa Chrome).
  - El **ChromeDriver** se descarga automáticamente gracias a `WebDriverManager` y a `webdriver.autodownload = true` en `serenity.conf`.
- Conexión a internet para acceder a `https://buggy.justtestit.org/`.

---

## Instalación

Clona el repositorio y entra al directorio:

```bash
git clone https://github.com/<usuario>/BuggyCarsRating.git
cd BuggyCarsRating
```

Descarga las dependencias (opcional, se hace automáticamente la primera vez):

```bash
# Windows
./gradlew.bat build -x test

# Linux / Mac
./gradlew build -x test
```

---

## Ejecución de las pruebas

### Ejecutar **todos los escenarios**

```bash
# Windows
./gradlew.bat clean test

# Linux / Mac
./gradlew clean test
```

El build está configurado con `test.finalizedBy(aggregate)`, por lo que al terminar la fase de test se genera automáticamente el reporte agregado de Serenity.

### Ejecutar un **único feature**

Puedes aprovechar la propiedad `cucumber.features` de Cucumber:

```bash
./gradlew.bat clean test -Dcucumber.features=src/test/resources/features/login.feature
```

### Ejecutar un **único escenario** por nombre

```bash
./gradlew.bat clean test -Dcucumber.filter.name="Login exitoso con credenciales validas"
```

### Ejecutar en modo headless (opcional)

Edita `src/test/resources/serenity.conf` y añade `--headless=new` a `chrome.switches`.

---

## Escenarios implementados

### 1. `register.feature` — Registro de usuario

| Escenario                                 | Datos                                       | Resultado esperado                                  |
|-------------------------------------------|---------------------------------------------|-----------------------------------------------------|
| Registro exitoso con datos válidos        | Usuario único generado + nombre/apellido    | Se muestra mensaje de registro exitoso              |
| Registro fallido con usuario ya existente | `testBuggy2026`                             | Se muestra mensaje de error (`UserAlreadyExists`)   |
| Registro fallido con campos vacíos        | Todos los campos vacíos                     | El botón de registro permanece deshabilitado        |

### 2. `login.feature` — Inicio de sesión

| Escenario                                   | Datos                                | Resultado esperado                                       |
|---------------------------------------------|--------------------------------------|----------------------------------------------------------|
| Login exitoso con credenciales válidas      | `testBuggy2026` / `Test1234!`        | Se muestra el enlace de **Logout** en la barra superior  |
| Login fallido con credenciales inválidas    | `usuarioFalso` / `claveIncorrecta`   | Se mantiene visible el botón **Login** (no autenticado)  |

### 3. `comment.feature` — Votar y comentar un modelo

| Escenario                                  | Descripción                                                                 |
|--------------------------------------------|-----------------------------------------------------------------------------|
| Dejar un comentario en un modelo de auto   | Registra y hace login con un usuario nuevo, navega al primer modelo popular, publica un comentario ("Excelente auto para pruebas automatizadas UdeA 2026") y verifica que quede listado. |

> Nota técnica: el envío del comentario se hace mediante `JavascriptExecutor` + `document.execCommand('insertText', …)` porque el campo es un control de Angular (`NgModel`) que exige disparar eventos **`input`** nativos con `isTrusted = true`. Un `sendKeys` estándar de Selenium no actualiza el `FormControl` y el botón **Vote** permanece deshabilitado. Ver [`LeaveComment.java`](src/main/java/co/edu/udea/certification/buggycars/tasks/LeaveComment.java).

---

## Reportes de Serenity

Tras cada ejecución exitosa (o fallida), Serenity genera un reporte HTML completo con capturas, tiempos y trazabilidad de cada paso.

- **Ubicación**: [`target/site/serenity/index.html`](target/site/serenity/index.html)
- **Ábrelo en el navegador**:

```bash
# Windows
start target\site\serenity\index.html

# Mac
open target/site/serenity/index.html

# Linux
xdg-open target/site/serenity/index.html
```

El histórico de resultados se conserva en la carpeta [`history/`](history/) para permitir el seguimiento de tendencias entre ejecuciones.

---

## Configuración

### `serenity.conf`

Aspectos destacados de [`src/test/resources/serenity.conf`](src/test/resources/serenity.conf):

- **Capturas**: `take.screenshots = AFTER_EACH_STEP` (captura al terminar cada paso).
- **Browser**: `restart.browser.for.each = scenario` (navegador limpio por cada escenario).
- **WebDriver**: `driver = chrome` + `autodownload = true`.
- **Timeouts**: espera explícita de 15 s y espera implícita de 10 s.
- **Capacidades de Chrome**: modo incógnito, `--no-sandbox`, `--ignore-certificate-errors`, etc.

### `build.gradle`

- Java 17 como *source* y *target*.
- Plugin `screenplayarchitecture` para andamiaje Screenplay.
- Plugin `serenity-bdd.serenity-gradle-plugin` para el reporte agregado.
- `test.finalizedBy(aggregate)` para generar el reporte **automáticamente** al final del test.

---

## Buenas prácticas aplicadas

- **Separación clara de responsabilidades** siguiendo el patrón Screenplay (Tasks, Interactions, Questions, UI).
- **Datos dinámicos**: los usuarios de registro se crean con un sufijo `System.currentTimeMillis()` para evitar colisiones entre ejecuciones.
- **Localizadores resilientes**: se combinan selectores CSS y XPath con fallbacks (`|`) para tolerar variaciones de la UI.
- **Esperas explícitas**: uso de `WaitUntil.the(...).isVisible()` junto con `Wait.forSeconds(...)` en lugares donde la SPA necesita tiempo de *change detection*.
- **Instrumentación Screenplay**: todas las Tasks se crean mediante `instrumented(...)` para que aparezcan correctamente en los reportes.
- **Anotaciones `@Step`**: cada Task describe su acción con placeholders (`#username`, `#comment`) para enriquecer la trazabilidad en el reporte.

---

## Solución de problemas

| Síntoma                                              | Posible causa                                                      | Solución                                                                 |
|------------------------------------------------------|---------------------------------------------------------------------|--------------------------------------------------------------------------|
| `SessionNotCreatedException` al arrancar Chrome      | Versión de Chrome vs ChromeDriver                                  | Actualiza Chrome; `WebDriverManager` descargará el driver compatible.    |
| Botón **Vote** deshabilitado                         | Angular no detecta el input de Selenium                             | Ya se maneja vía `execCommand('insertText')` en `LeaveComment`.          |
| Falla el registro con "UserAlreadyExists" inesperado | El nombre de usuario ya existe en el ambiente compartido            | Los tests dinámicos usan `System.currentTimeMillis()` para evitarlo.     |
| Reporte Serenity vacío                               | La tarea `aggregate` no corrió                                     | Ejecuta `./gradlew aggregate` o usa `./gradlew clean test`.              |
| Pruebas lentas                                       | Esperas fijas (`Wait.forSeconds`)                                   | Reemplazar por `WaitUntil` donde sea posible.                            |

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
