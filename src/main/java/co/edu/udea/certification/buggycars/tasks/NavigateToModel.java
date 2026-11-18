package co.edu.udea.certification.buggycars.tasks;

import co.edu.udea.certification.buggycars.interactions.Wait;
import co.edu.udea.certification.buggycars.userinterfaces.HomePage;
import co.edu.udea.certification.buggycars.userinterfaces.ModelDetailPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class NavigateToModel implements Task {

    @Override
    @Step("{0} navega a la pagina de un modelo de auto")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(HomePage.FIRST_MODEL_LINK, isVisible()).forNoMoreThan(20).seconds(),
                Click.on(HomePage.FIRST_MODEL_LINK),
                Wait.forSeconds(5),
                WaitUntil.the(ModelDetailPage.COMMENT_FIELD, isVisible()).forNoMoreThan(20).seconds()
        );
    }

    public static NavigateToModel page() {
        return instrumented(NavigateToModel.class);
    }
}