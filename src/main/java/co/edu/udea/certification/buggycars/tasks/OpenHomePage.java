package co.edu.udea.certification.buggycars.tasks;

import co.edu.udea.certification.buggycars.interactions.Wait;
import co.edu.udea.certification.buggycars.userinterfaces.HomePage;
import co.edu.udea.certification.buggycars.utils.Constants;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class OpenHomePage implements Task {

    @Override
    @Step("{0} abre la pagina principal de Buggy Cars Rating")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url(Constants.BASE_URL),
                WaitUntil.the(HomePage.FIRST_POPULAR_MODEL, isVisible()).forNoMoreThan(Constants.TIMEOUT_SECONDS).seconds(),
                Wait.forSeconds(2)
        );
    }

    public static OpenHomePage onBuggyCars() {
        return instrumented(OpenHomePage.class);
    }
}
