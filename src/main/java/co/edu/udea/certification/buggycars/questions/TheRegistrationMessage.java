package co.edu.udea.certification.buggycars.questions;

import co.edu.udea.certification.buggycars.userinterfaces.RegisterPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class TheRegistrationMessage implements Question<String> {

    private final boolean isSuccess;

    public TheRegistrationMessage(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    @Override
    public String answeredBy(Actor actor) {
        if (isSuccess) {
            return Text.of(RegisterPage.SUCCESS_MESSAGE).answeredBy(actor);
        } else {
            return Text.of(RegisterPage.ERROR_MESSAGE).answeredBy(actor);
        }
    }

    public static TheRegistrationMessage success() {
        return new TheRegistrationMessage(true);
    }

    public static TheRegistrationMessage error() {
        return new TheRegistrationMessage(false);
    }
}