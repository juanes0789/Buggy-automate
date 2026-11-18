package co.edu.udea.certification.buggycars.tasks;

import co.edu.udea.certification.buggycars.interactions.Wait;
import co.edu.udea.certification.buggycars.userinterfaces.HomePage;
import co.edu.udea.certification.buggycars.userinterfaces.RegisterPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.annotations.Step;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class RegisterUser implements Task {

    private final String username;
    private final String firstName;
    private final String lastName;
    private final String password;

    public RegisterUser(String username, String firstName, String lastName, String password) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    @Override
    @Step("{0} se registra con usuario #username")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(HomePage.REGISTER_LINK),
                Wait.forSeconds(2),
                Enter.theValue(username).into(RegisterPage.USERNAME),
                Wait.forSeconds(1),
                Enter.theValue(firstName).into(RegisterPage.FIRST_NAME),
                Wait.forSeconds(1),
                Enter.theValue(lastName).into(RegisterPage.LAST_NAME),
                Wait.forSeconds(1),
                Enter.theValue(password).into(RegisterPage.PASSWORD),
                Wait.forSeconds(1),
                Enter.theValue(password).into(RegisterPage.CONFIRM_PASSWORD),
                Wait.forSeconds(2),
                Click.on(RegisterPage.REGISTER_BUTTON),
                Wait.forSeconds(5)
        );
    }

    public static RegisterUser withData(String username, String firstName, String lastName, String password) {
        return instrumented(RegisterUser.class, username, firstName, lastName, password);
    }
}