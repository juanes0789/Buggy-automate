package co.edu.udea.certification.buggycars.tasks;

import co.edu.udea.certification.buggycars.interactions.Wait;
import co.edu.udea.certification.buggycars.userinterfaces.HomePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class LoginAs implements Task {

    private final String username;
    private final String password;

    public LoginAs(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    @Step("{0} inicia sesion como #username")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue(username).into(HomePage.LOGIN_USERNAME),
                Wait.forSeconds(1),
                Enter.theValue(password).into(HomePage.LOGIN_PASSWORD),
                Wait.forSeconds(1),
                Click.on(HomePage.LOGIN_BUTTON),
                Wait.forSeconds(5)
        );
    }

    public static LoginAs user(String username, String password) {
        return instrumented(LoginAs.class, username, password);
    }
}