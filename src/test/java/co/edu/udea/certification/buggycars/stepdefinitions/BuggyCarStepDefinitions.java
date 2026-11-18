package co.edu.udea.certification.buggycars.stepdefinitions;

import co.edu.udea.certification.buggycars.questions.TheCommentList;
import co.edu.udea.certification.buggycars.questions.TheRegistrationMessage;
import co.edu.udea.certification.buggycars.questions.TheUserName;
import co.edu.udea.certification.buggycars.tasks.*;
import co.edu.udea.certification.buggycars.userinterfaces.HomePage;
import co.edu.udea.certification.buggycars.userinterfaces.RegisterPage;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.questions.Visibility;
import net.serenitybdd.annotations.Managed;
import org.openqa.selenium.WebDriver;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.*;

public class BuggyCarStepDefinitions {

    Actor actor = Actor.named("usuario");

    @Managed
    WebDriver browser;

    @Before
    public void setup() {
        actor.can(BrowseTheWeb.with(browser));
    }

    // ===== GIVEN =====

    @Given("el usuario se encuentra en la pagina principal de Buggy Cars Rating")
    public void elUsuarioAbreLaPagina() {
        actor.attemptsTo(OpenHomePage.onBuggyCars());
    }

    @Given("el usuario ha iniciado sesion con username {string} y password {string}")
    public void elUsuarioHaIniciadoSesion(String user, String pass) {
        actor.attemptsTo(
                OpenHomePage.onBuggyCars(),
                LoginAs.user(user, pass)
        );
    }

    @Given("un usuario nuevo registrado inicia sesion en Buggy Cars Rating")
    public void unUsuarioNuevoRegistradoIniciaSesion() {
        String uniqueUser = "cmt" + System.currentTimeMillis();
        String password = "Test1234!";
        actor.attemptsTo(
                OpenHomePage.onBuggyCars(),
                RegisterUser.withData(uniqueUser, "Juan", "Perez", password),
                OpenHomePage.onBuggyCars(),
                LoginAs.user(uniqueUser, password)
        );
    }

    @Given("el usuario se registra y hace login con un usuario nuevo")
    public void registraYHaceLoginConUsuarioNuevo() {
        String uniqueUser = "buggyTest" + System.currentTimeMillis();
        String password = "Test1234!";
        actor.attemptsTo(
                OpenHomePage.onBuggyCars(),
                RegisterUser.withData(uniqueUser, "Auto", "Tester", password)
        );
        actor.attemptsTo(
                OpenHomePage.onBuggyCars(),
                LoginAs.user(uniqueUser, password)
        );
    }

    // ===== WHEN =====

    @When("el usuario abre el formulario de registro")
    public void abreFormularioRegistro() {
        // El click al link Register se hace dentro de RegisterUser task
    }

    @When("diligencia el formulario con un usuario nuevo nombre {string} apellido {string} y password {string}")
    public void diligenciaFormularioNuevo(String first, String last, String pass) {
        String uniqueUser = "testBuggy" + System.currentTimeMillis();
        actor.attemptsTo(RegisterUser.withData(uniqueUser, first, last, pass));
    }

    @When("diligencia el formulario con username {string} nombre {string} apellido {string} y password {string}")
    public void diligenciaFormulario(String user, String first, String last, String pass) {
        actor.attemptsTo(RegisterUser.withData(user, first, last, pass));
    }

    @When("ingresa el username {string} y password {string}")
    public void ingresaCredenciales(String user, String pass) {
        actor.attemptsTo(LoginAs.user(user, pass));
    }

    @When("navega a la pagina de un modelo de auto")
    public void navegaAModelo() {
        actor.attemptsTo(NavigateToModel.page());
    }

    @And("escribe el comentario {string}")
    public void escribeComentario(String comment) {
        actor.attemptsTo(LeaveComment.withText(comment));
    }

    @And("envia el voto")
    public void enviaVoto() {
        // Ya incluido en LeaveComment
    }

    // ===== THEN =====

    @Then("el sistema muestra un mensaje de registro exitoso")
    public void verificaRegistroExitoso() {
        actor.should(
                seeThat(Visibility.of(RegisterPage.SUCCESS_MESSAGE), is(true))
        );
    }

    @Then("el sistema muestra un mensaje de error de registro")
    public void verificaErrorRegistro() {
        actor.should(
                seeThat(Visibility.of(RegisterPage.ERROR_MESSAGE), is(true))
        );
    }

    @Then("el boton de registro debe estar deshabilitado")
    public void verificaBotonDeshabilitado() {
        actor.should(
                seeThat(Visibility.of(RegisterPage.REGISTER_BUTTON), is(true))
        );
    }

    @Then("el sistema muestra el nombre del usuario en la barra de navegacion")
    public void verificaLoginExitoso() {
        actor.should(
                seeThat(Visibility.of(HomePage.LOGOUT_LINK), is(true))
        );
    }

    @Then("el sistema muestra un mensaje de error de autenticacion")
    public void verificaErrorLogin() {
        actor.should(
                seeThat(Visibility.of(HomePage.LOGIN_BUTTON), is(true))
        );
    }

    @Then("el comentario {string} queda visible en la lista de comentarios")
    public void verificaComentarioVisible(String comment) {
        // Tras votar, BuggyCars no refresca la tabla de comentarios automáticamente.
        // Recargamos la página para que Angular vuelva a consultar la lista.
        actor.attemptsTo(co.edu.udea.certification.buggycars.interactions.Wait.forSeconds(2));
        browser.navigate().refresh();
        actor.attemptsTo(co.edu.udea.certification.buggycars.interactions.Wait.forSeconds(5));
        actor.should(
                seeThat(TheCommentList.content(), containsString(comment))
        );
    }
}