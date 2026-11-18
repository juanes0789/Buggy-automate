package co.edu.udea.certification.buggycars.tasks;

import co.edu.udea.certification.buggycars.interactions.Wait;
import co.edu.udea.certification.buggycars.userinterfaces.HomePage;
import co.edu.udea.certification.buggycars.utils.Constants;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class LoginAs implements Task {

    private final String username;
    private final String password;

    public LoginAs(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    @Step("{0} inicia sesion como '#username'")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(username).into(HomePage.LOGIN_USERNAME),
                Wait.forSeconds(1),
                Enter.theValue(password).into(HomePage.LOGIN_PASSWORD),
                Wait.forSeconds(1),
                Click.on(HomePage.LOGIN_BUTTON),
                // Espera el resultado, sea exito (Logout) o error
                WaitUntil.the(HomePage.LOGIN_OUTCOME, isVisible()).forNoMoreThan(Constants.TIMEOUT_SECONDS).seconds(),
                Wait.forSeconds(2)
        );
    }

    public static LoginAs user(String username, String password) {
        return instrumented(LoginAs.class, username, password);
    }
}
