package co.edu.udea.certification.buggycars.questions;

import co.edu.udea.certification.buggycars.userinterfaces.RegisterPage;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

/** Texto que devuelve el servidor al enviar el registro (exito o error). */
@Subject("el mensaje de resultado del registro")
public class TheRegistrationMessage implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(RegisterPage.RESULT_MESSAGE).answeredBy(actor).trim();
    }

    public static TheRegistrationMessage displayed() {
        return new TheRegistrationMessage();
    }
}
