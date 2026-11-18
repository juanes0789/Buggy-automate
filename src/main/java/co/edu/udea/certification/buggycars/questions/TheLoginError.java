package co.edu.udea.certification.buggycars.questions;

import co.edu.udea.certification.buggycars.userinterfaces.HomePage;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

@Subject("el mensaje de error del login")
public class TheLoginError implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(HomePage.LOGIN_ERROR).answeredBy(actor).trim();
    }

    public static TheLoginError displayed() {
        return new TheLoginError();
    }
}
