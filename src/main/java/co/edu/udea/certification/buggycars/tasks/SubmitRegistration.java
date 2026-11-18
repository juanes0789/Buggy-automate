package co.edu.udea.certification.buggycars.tasks;

import co.edu.udea.certification.buggycars.interactions.Wait;
import co.edu.udea.certification.buggycars.userinterfaces.RegisterPage;
import co.edu.udea.certification.buggycars.utils.Constants;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isEnabled;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class SubmitRegistration implements Task {

    @Override
    @Step("{0} envia el formulario de registro")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(RegisterPage.REGISTER_BUTTON, isEnabled()).forNoMoreThan(Constants.TIMEOUT_SECONDS).seconds(),
                Click.on(RegisterPage.REGISTER_BUTTON),
                WaitUntil.the(RegisterPage.RESULT_MESSAGE, isVisible()).forNoMoreThan(Constants.TIMEOUT_SECONDS).seconds(),
                Wait.forSeconds(2)
        );
    }

    public static SubmitRegistration now() {
        return instrumented(SubmitRegistration.class);
    }
}
