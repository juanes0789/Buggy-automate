package co.edu.udea.certification.buggycars.tasks;

import co.edu.udea.certification.buggycars.interactions.TypeIntoAngularField;
import co.edu.udea.certification.buggycars.interactions.Wait;
import co.edu.udea.certification.buggycars.userinterfaces.ModelDetailPage;
import co.edu.udea.certification.buggycars.utils.Constants;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class WriteComment implements Task {

    private final String comment;

    public WriteComment(String comment) {
        this.comment = comment;
    }

    @Override
    @Step("{0} escribe el comentario '#comment'")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(ModelDetailPage.COMMENT_FIELD, isVisible()).forNoMoreThan(Constants.TIMEOUT_SECONDS).seconds(),
                TypeIntoAngularField.theText(comment).into(ModelDetailPage.COMMENT_FIELD),
                Wait.forSeconds(2)
        );
    }

    public static WriteComment withText(String comment) {
        return instrumented(WriteComment.class, comment);
    }
}
