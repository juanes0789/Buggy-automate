package co.edu.udea.certification.buggycars.tasks;

import co.edu.udea.certification.buggycars.interactions.Wait;
import co.edu.udea.certification.buggycars.models.User;
import co.edu.udea.certification.buggycars.userinterfaces.RegisterPage;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Clear;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.Hit;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.Keys;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/** Diligencia el formulario de registro sin enviarlo. */
public class FillRegistrationForm implements Task {

    private final User user;
    private final String username;

    public FillRegistrationForm(User user) {
        this.user = user;
        this.username = user.username();
    }

    @Override
    @Step("{0} diligencia el formulario de registro con el username '#username'")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                type(user.username(), RegisterPage.USERNAME),
                Wait.forSeconds(1),
                type(user.firstName(), RegisterPage.FIRST_NAME),
                Wait.forSeconds(1),
                type(user.lastName(), RegisterPage.LAST_NAME),
                Wait.forSeconds(1),
                type(user.password(), RegisterPage.PASSWORD),
                Wait.forSeconds(1),
                type(user.confirmPassword(), RegisterPage.CONFIRM_PASSWORD),
                // Sale del ultimo campo para que Angular muestre las validaciones
                Hit.the(Keys.TAB).into(RegisterPage.CONFIRM_PASSWORD),
                Wait.forSeconds(1)
        );
    }

    /** Un valor vacio significa "dejar el campo vacio". */
    private static Performable type(String value, Target field) {
        return value == null || value.isEmpty()
                ? Clear.field(field)
                : Enter.theValue(value).into(field);
    }

    public static FillRegistrationForm with(User user) {
        return instrumented(FillRegistrationForm.class, user);
    }
}
