package co.edu.udea.certification.buggycars.questions;

import co.edu.udea.certification.buggycars.userinterfaces.HomePage;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

/** Saludo de la barra de navegacion, por ejemplo "Hi, Ana". */
@Subject("el saludo de la barra de navegacion")
public class TheUserName implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(HomePage.GREETING).answeredBy(actor).trim();
    }

    public static TheUserName displayed() {
        return new TheUserName();
    }
}
