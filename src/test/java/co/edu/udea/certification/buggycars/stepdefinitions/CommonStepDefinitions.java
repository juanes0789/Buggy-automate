package co.edu.udea.certification.buggycars.stepdefinitions;

import co.edu.udea.certification.buggycars.models.User;
import co.edu.udea.certification.buggycars.tasks.OpenHomePage;
import co.edu.udea.certification.buggycars.tasks.RegisterUser;
import co.edu.udea.certification.buggycars.utils.ActorMemory;
import co.edu.udea.certification.buggycars.utils.Constants;
import io.cucumber.java.en.Given;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

/** Pasos compartidos por varios features. */
public class CommonStepDefinitions {

    @Given("the user is on the Buggy Cars Rating home page")
    public void elUsuarioSeEncuentraEnLaPaginaPrincipal() {
        theActorInTheSpotlight().attemptsTo(OpenHomePage.onBuggyCars());
    }

    /** Crea su propio usuario para no depender de datos que otro haya dejado en el ambiente. */
    @Given("there is a registered user named {string}")
    public void existeUnUsuarioRegistrado(String firstName) {
        User user = User.unique(firstName, "Tester", Constants.DEFAULT_PASSWORD);
        theActorInTheSpotlight().attemptsTo(
                OpenHomePage.onBuggyCars(),
                RegisterUser.as(user)
        );
        theActorInTheSpotlight().remember(ActorMemory.REGISTERED_USER, user);
    }
}
