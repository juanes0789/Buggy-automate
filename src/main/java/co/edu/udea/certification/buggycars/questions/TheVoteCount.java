package co.edu.udea.certification.buggycars.questions;

import co.edu.udea.certification.buggycars.userinterfaces.ModelDetailPage;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

/** Numero de votos que muestra el modelo ("Votes: 11140"). */
@Subject("el conteo de votos del modelo")
public class TheVoteCount implements Question<Integer> {

    @Override
    public Integer answeredBy(Actor actor) {
        return Integer.parseInt(Text.of(ModelDetailPage.VOTE_COUNT).answeredBy(actor).trim());
    }

    public static TheVoteCount ofTheModel() {
        return new TheVoteCount();
    }
}
