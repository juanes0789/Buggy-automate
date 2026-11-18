package co.edu.udea.certification.buggycars.stepdefinitions;

import co.edu.udea.certification.buggycars.models.User;
import co.edu.udea.certification.buggycars.questions.TheElementIsDisplayed;
import co.edu.udea.certification.buggycars.questions.TheLoginError;
import co.edu.udea.certification.buggycars.questions.TheUserName;
import co.edu.udea.certification.buggycars.tasks.LoginAs;
import co.edu.udea.certification.buggycars.tasks.Logout;
import co.edu.udea.certification.buggycars.userinterfaces.HomePage;
import co.edu.udea.certification.buggycars.utils.ActorMemory;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import net.serenitybdd.screenplay.questions.Visibility;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class LoginStepDefinitions {

    // ===== WHEN =====

    @When("the user logs in with their credentials")
    public void iniciaSesionConSusCredenciales() {
        User user = theActorInTheSpotlight().recall(ActorMemory.REGISTERED_USER);
        theActorInTheSpotlight().attemptsTo(LoginAs.user(user.username(), user.password()));
    }

    @When("the user logs in with their username and the password {string}")
    public void iniciaSesionConPasswordIncorrecto(String wrongPassword) {
        User user = theActorInTheSpotlight().recall(ActorMemory.REGISTERED_USER);
        theActorInTheSpotlight().attemptsTo(LoginAs.user(user.username(), wrongPassword));
    }

    @When("the user enters the username {string} and the password {string}")
    public void ingresaCredenciales(String username, String password) {
        theActorInTheSpotlight().attemptsTo(LoginAs.user(username, password));
    }

    @When("the user logs out")
    public void cierraLaSesion() {
        theActorInTheSpotlight().attemptsTo(Logout.now());
    }

    // ===== THEN =====

    @Then("the navigation bar shows the greeting {string}")
    public void muestraElSaludo(String expectedGreeting) {
        theActorInTheSpotlight().should(
                seeThat(TheUserName.displayed(), equalTo(expectedGreeting))
        );
    }

    @Then("the logout option is shown")
    public void muestraLaOpcionDeLogout() {
        theActorInTheSpotlight().should(
                seeThat("el link Logout es visible", Visibility.of(HomePage.LOGOUT_LINK), is(true))
        );
    }

    @Then("the system shows the authentication error {string}")
    public void muestraElErrorDeAutenticacion(String expectedError) {
        theActorInTheSpotlight().should(
                seeThat(TheLoginError.displayed(), equalTo(expectedError)),
                seeThat(TheElementIsDisplayed.now(HomePage.LOGOUT_LINK), is(false))
        );
    }

    @Then("the login form is shown again")
    public void muestraElFormularioDeLogin() {
        theActorInTheSpotlight().should(
                seeThat("el boton Login es visible", Visibility.of(HomePage.LOGIN_BUTTON), is(true)),
                seeThat(TheElementIsDisplayed.now(HomePage.LOGOUT_LINK), is(false))
        );
    }
}
