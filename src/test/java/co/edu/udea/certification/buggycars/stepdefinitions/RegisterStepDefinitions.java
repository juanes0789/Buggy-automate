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
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Entonces;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;

public class RegisterStepDefinitions {

    // ===== CUANDO =====

    @Cuando("abre el formulario de registro")
    public void abreElFormularioDeRegistro() {
        theActorInTheSpotlight().attemptsTo(OpenRegistrationForm.now());
    }

    @Cuando("diligencia el formulario con un usuario nuevo, nombre {string}, apellido {string} y password {string}")
    public void diligenciaConUsuarioNuevo(String firstName, String lastName, String password) {
        fill(User.unique(firstName, lastName, password));
    }

    @Cuando("diligencia el formulario con el mismo username del usuario existente")
    public void diligenciaConUsernameExistente() {
        User existing = theActorInTheSpotlight().recall(ActorMemory.REGISTERED_USER);
        fill(User.withUsername(existing.username(), "Juan", "Perez", Constants.DEFAULT_PASSWORD));
    }

    @Cuando("diligencia el formulario con username {string}, nombre {string}, apellido {string} y password {string}")
    public void diligenciaConDatos(String username, String firstName, String lastName, String password) {
        fill(User.withUsername(username, firstName, lastName, password));
    }

    @Cuando("diligencia el formulario con nombre {string}, apellido {string}, password {string} y confirmacion {string}")
    public void diligenciaConConfirmacionDistinta(String firstName, String lastName, String password, String confirmation) {
        fill(User.unique(firstName, lastName, password).withConfirmation(confirmation));
    }

    @Cuando("diligencia el formulario con un username de {int} caracteres")
    public void diligenciaConUsernameDeLongitud(int length) {
        fill(User.withUsername(TestData.repeat('u', length), "Juan", "Perez", Constants.DEFAULT_PASSWORD));
    }

    @Cuando("envia el formulario de registro")
    public void enviaElFormulario() {
        theActorInTheSpotlight().attemptsTo(SubmitRegistration.now());
    }

    // ===== ENTONCES =====

    @Entonces("el sistema muestra el mensaje de registro {string}")
    public void muestraElMensajeDeRegistro(String expectedMessage) {
        theActorInTheSpotlight().should(
                seeThat(TheRegistrationMessage.displayed(), containsString(expectedMessage))
        );
    }

    @Entonces("se muestra la alerta de validacion {string}")
    public void muestraLaAlertaDeValidacion(String expectedAlert) {
        theActorInTheSpotlight().should(
                seeThat(TheValidationAlerts.visible(), hasItem(expectedAlert))
        );
    }

    @Entonces("el boton de registro queda {word}")
    public void elBotonDeRegistroQueda(String state) {
        boolean expectedEnabled = switch (state) {
            case "habilitado" -> true;
            case "deshabilitado" -> false;
            default -> throw new IllegalArgumentException("Estado no soportado: " + state);
        };
        theActorInTheSpotlight().should(
                seeThat(TheRegisterButton.isEnabled(), is(expectedEnabled))
        );
    }

    private static void fill(User user) {
        theActorInTheSpotlight().attemptsTo(FillRegistrationForm.with(user));
    }
}
