package co.edu.udea.certification.buggycars.questions;

import co.edu.udea.certification.buggycars.userinterfaces.HomePage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

public class TheUserName implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(HomePage.USER_NAME_DISPLAY).answeredBy(actor);
    }

    public static TheUserName displayed() {
        return new TheUserName();
    }
}