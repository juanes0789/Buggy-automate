package co.edu.udea.certification.buggycars.questions;

import co.edu.udea.certification.buggycars.userinterfaces.RegisterPage;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

/** Indica si el boton Register se puede pulsar. */
@Subject("si el boton Register esta habilitado")
public class TheRegisterButton implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        return RegisterPage.REGISTER_BUTTON.resolveFor(actor).isEnabled();
    }

    public static TheRegisterButton isEnabled() {
        return new TheRegisterButton();
    }
}
