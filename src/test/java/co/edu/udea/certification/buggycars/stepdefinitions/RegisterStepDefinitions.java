package co.edu.udea.certification.buggycars.stepdefinitions;

import co.edu.udea.certification.buggycars.models.User;
import co.edu.udea.certification.buggycars.questions.TheRegisterButton;
import co.edu.udea.certification.buggycars.questions.TheRegistrationMessage;
import co.edu.udea.certification.buggycars.questions.TheValidationAlerts;
import co.edu.udea.certification.buggycars.tasks.FillRegistrationForm;
import co.edu.udea.certification.buggycars.tasks.OpenRegistrationForm;
import co.edu.udea.certification.buggycars.tasks.SubmitRegistration;
import co.edu.udea.certification.buggycars.utils.ActorMemory;
import co.edu.udea.certification.buggycars.utils.Constants;
import co.edu.udea.certification.buggycars.utils.TestData;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class RegisterStepDefinitions {

    // ===== WHEN =====

    @When("the user opens the registration form")
    public void abreElFormularioDeRegistro() {
        theActorInTheSpotlight().attemptsTo(OpenRegistrationForm.now());
    }

    @When("fills in the form with a new user, first name {string}, last name {string} and password {string}")
    public void diligenciaConUsuarioNuevo(String firstName, String lastName, String password) {
        fill(User.unique(firstName, lastName, password));
    }

    @When("fills in the form with the username of the existing user")
    public void diligenciaConUsernameExistente() {
        User existing = theActorInTheSpotlight().recall(ActorMemory.REGISTERED_USER);
        fill(User.withUsername(existing.username(), "Juan", "Perez", Constants.DEFAULT_PASSWORD));
    }

    @When("fills in the form with username {string}, first name {string}, last name {string} and password {string}")
    public void diligenciaConDatos(String username, String firstName, String lastName, String password) {
        fill(User.withUsername(username, firstName, lastName, password));
    }

    @When("fills in the form with first name {string}, last name {string}, password {string} and confirmation {string}")
    public void diligenciaConConfirmacionDistinta(String firstName, String lastName, String password, String confirmation) {
        fill(User.unique(firstName, lastName, password).withConfirmation(confirmation));
    }

    @When("fills in the form with a username of {int} characters")
    public void diligenciaConUsernameDeLongitud(int length) {
        fill(User.withUsername(TestData.repeat('u', length), "Juan", "Perez", Constants.DEFAULT_PASSWORD));
    }

    @When("submits the registration form")
    public void enviaElFormulario() {
        theActorInTheSpotlight().attemptsTo(SubmitRegistration.now());
    }

    // ===== THEN =====

    @Then("the system shows the registration message {string}")
    public void muestraElMensajeDeRegistro(String expectedMessage) {
        theActorInTheSpotlight().should(
                seeThat(TheRegistrationMessage.displayed(), containsString(expectedMessage))
        );
    }

    @Then("the validation alert {string} is shown")
    public void muestraLaAlertaDeValidacion(String expectedAlert) {
        theActorInTheSpotlight().should(
                seeThat(TheValidationAlerts.visible(), hasItem(expectedAlert))
        );
    }

    @Then("the register button is {word}")
    public void elBotonDeRegistroQueda(String state) {
        boolean expectedEnabled = switch (state) {
            case "enabled" -> true;
            case "disabled" -> false;
            default -> throw new IllegalArgumentException("Unsupported state: " + state);
        };
        theActorInTheSpotlight().should(
                seeThat(TheRegisterButton.isEnabled(), is(expectedEnabled))
        );
    }

    private static void fill(User user) {
        theActorInTheSpotlight().attemptsTo(FillRegistrationForm.with(user));
    }
}
