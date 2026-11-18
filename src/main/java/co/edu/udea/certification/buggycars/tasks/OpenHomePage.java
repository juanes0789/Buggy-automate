package co.edu.udea.certification.buggycars.tasks;

import co.edu.udea.certification.buggycars.interactions.Wait;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class OpenHomePage implements Task {

    private final String url = "https://buggy.justtestit.org/";

    @Override
    @Step("{0} abre la pagina principal de Buggy Cars Rating")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Open.url(url),
                Wait.forSeconds(3)
        );
    }

    public static OpenHomePage onBuggyCars() {
        return instrumented(OpenHomePage.class);
    }
}