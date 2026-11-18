package co.edu.udea.certification.buggycars.tasks;

import co.edu.udea.certification.buggycars.models.User;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/** Tarea de negocio completa: abrir, diligenciar y enviar el registro. Se reutiliza como precondicion. */
public class RegisterUser implements Task {

    private final User user;
    private final String username;

    public RegisterUser(User user) {
        this.user = user;
        this.username = user.username();
    }

    @Override
    @Step("{0} se registra con el usuario '#username'")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                OpenRegistrationForm.now(),
                FillRegistrationForm.with(user),
                SubmitRegistration.now()
        );
    }

    public static RegisterUser as(User user) {
        return instrumented(RegisterUser.class, user);
    }
}
