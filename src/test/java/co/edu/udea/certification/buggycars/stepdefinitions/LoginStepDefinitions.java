package co.edu.udea.certification.buggycars.stepdefinitions;

import co.edu.udea.certification.buggycars.models.User;
import co.edu.udea.certification.buggycars.questions.TheLoginError;
import co.edu.udea.certification.buggycars.questions.TheUserName;
import co.edu.udea.certification.buggycars.tasks.LoginAs;
import co.edu.udea.certification.buggycars.tasks.Logout;
import co.edu.udea.certification.buggycars.userinterfaces.HomePage;
import co.edu.udea.certification.buggycars.utils.ActorMemory;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.questions.Visibility;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class LoginStepDefinitions {

    // ===== CUANDO =====

    @Cuando("inicia sesion con sus credenciales")
    public void iniciaSesionConSusCredenciales() {
        User user = theActorInTheSpotlight().recall(ActorMemory.REGISTERED_USER);
        theActorInTheSpotlight().attemptsTo(LoginAs.user(user.username(), user.password()));
    }

    @Cuando("inicia sesion con su username y el password {string}")
    public void iniciaSesionConPasswordIncorrecto(String wrongPassword) {
        User user = theActorInTheSpotlight().recall(ActorMemory.REGISTERED_USER);
        theActorInTheSpotlight().attemptsTo(LoginAs.user(user.username(), wrongPassword));
    }

    @Cuando("ingresa el username {string} y password {string}")
    public void ingresaCredenciales(String username, String password) {
        theActorInTheSpotlight().attemptsTo(LoginAs.user(username, password));
    }

    @Cuando("cierra la sesion")
    public void cierraLaSesion() {
        theActorInTheSpotlight().attemptsTo(Logout.now());
    }

    // ===== ENTONCES =====

    @Entonces("la barra de navegacion muestra el saludo {string}")
    public void muestraElSaludo(String expectedGreeting) {
        theActorInTheSpotlight().should(
                seeThat(TheUserName.displayed(), equalTo(expectedGreeting))
        );
    }

    @Entonces("se muestra la opcion para cerrar sesion")
    public void muestraLaOpcionDeLogout() {
        theActorInTheSpotlight().should(
                seeThat("el link Logout es visible", Visibility.of(HomePage.LOGOUT_LINK), is(true))
        );
    }

    @Entonces("el sistema muestra el mensaje de error de autenticacion {string}")
    public void muestraElErrorDeAutenticacion(String expectedError) {
        theActorInTheSpotlight().should(
                seeThat(TheLoginError.displayed(), equalTo(expectedError)),
                seeThat("el link Logout es visible", Visibility.of(HomePage.LOGOUT_LINK), is(false))
        );
    }

    @Entonces("se muestra nuevamente el formulario de inicio de sesion")
    public void muestraElFormularioDeLogin() {
        theActorInTheSpotlight().should(
                seeThat("el boton Login es visible", Visibility.of(HomePage.LOGIN_BUTTON), is(true)),
                seeThat("el link Logout es visible", Visibility.of(HomePage.LOGOUT_LINK), is(false))
        );
    }
}
