package co.edu.udea.certification.buggycars.tasks;

import co.edu.udea.certification.buggycars.interactions.RefreshPage;
import co.edu.udea.certification.buggycars.interactions.Wait;
import co.edu.udea.certification.buggycars.userinterfaces.ModelDetailPage;
import co.edu.udea.certification.buggycars.utils.Constants;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isEnabled;

public class SubmitVote implements Task {

    @Override
    @Step("{0} envia su voto")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(ModelDetailPage.VOTE_BUTTON, isEnabled()).forNoMoreThan(Constants.TIMEOUT_SECONDS).seconds(),
                Click.on(ModelDetailPage.VOTE_BUTTON),
                Wait.forSeconds(2),
                // La pagina no se actualiza sola: se recarga hasta ver el voto guardado
                RefreshPage.untilVisible(ModelDetailPage.VOTE_STATUS),
                Wait.forSeconds(2)
        );
    }

    public static SubmitVote now() {
        return instrumented(SubmitVote.class);
    }
}
