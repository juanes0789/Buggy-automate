package co.edu.udea.certification.buggycars.questions;

import co.edu.udea.certification.buggycars.userinterfaces.ModelDetailPage;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

/** Mensaje del recuadro de votacion ("Thank you for your vote!", "You need to be logged in to vote."). */
@Subject("el mensaje del recuadro de votacion")
public class TheVoteStatus implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(ModelDetailPage.VOTE_STATUS).answeredBy(actor).trim();
    }

    public static TheVoteStatus displayed() {
        return new TheVoteStatus();
    }
}
