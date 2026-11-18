package co.edu.udea.certification.buggycars.questions;

import co.edu.udea.certification.buggycars.userinterfaces.ModelDetailPage;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;

/** Todo el texto de la tabla de comentarios del modelo. */
@Subject("la lista de comentarios del modelo")
public class TheCommentList implements Question<String> {

    @Override
    public String answeredBy(Actor actor) {
        return Text.of(ModelDetailPage.COMMENTS_TABLE).answeredBy(actor);
    }

    public static TheCommentList content() {
        return new TheCommentList();
    }
}
