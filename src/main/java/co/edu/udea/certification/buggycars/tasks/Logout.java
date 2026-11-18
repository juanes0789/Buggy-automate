package co.edu.udea.certification.buggycars.tasks;

import co.edu.udea.certification.buggycars.interactions.Wait;
import co.edu.udea.certification.buggycars.userinterfaces.HomePage;
import co.edu.udea.certification.buggycars.utils.Constants;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class Logout implements Task {

    @Override
    @Step("{0} cierra la sesion")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(HomePage.LOGOUT_LINK),
                WaitUntil.the(HomePage.LOGIN_BUTTON, isVisible()).forNoMoreThan(Constants.TIMEOUT_SECONDS).seconds(),
                Wait.forSeconds(2)
        );
    }

    public static Logout now() {
        return instrumented(Logout.class);
    }
}
