package co.edu.udea.certification.buggycars.questions;

import co.edu.udea.certification.buggycars.userinterfaces.RegisterPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.annotations.Subject;

/** Indica si el boton Register se puede pulsar. */
@Subject("si el boton Register esta habilitado")
public class TheRegisterButton implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        // Se lee el elemento de Selenium directamente: el isEnabled() de Serenity espera
        // hasta 15 s a que el boton se habilite antes de responder false.
        return RegisterPage.REGISTER_BUTTON.resolveFor(actor).getWrappedElement().isEnabled();
    }

    public static TheRegisterButton isEnabled() {
        return new TheRegisterButton();
    }
}
