package co.edu.udea.certification.buggycars.tasks;

import co.edu.udea.certification.buggycars.interactions.Wait;
import co.edu.udea.certification.buggycars.userinterfaces.HomePage;
import co.edu.udea.certification.buggycars.userinterfaces.ModelDetailPage;
import co.edu.udea.certification.buggycars.utils.Constants;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class NavigateToModel implements Task {

    @Override
    @Step("{0} entra a la pagina del segundo modelo del ranking general")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Click.on(HomePage.OVERALL_RANKING_LINK),
                WaitUntil.the(HomePage.SECOND_RANKED_MODEL, isVisible()).forNoMoreThan(Constants.TIMEOUT_SECONDS).seconds(),
                Wait.forSeconds(2),
                Click.on(HomePage.SECOND_RANKED_MODEL),
                WaitUntil.the(ModelDetailPage.VOTE_COUNT, isVisible()).forNoMoreThan(Constants.TIMEOUT_SECONDS).seconds(),
                Wait.forSeconds(2)
        );
    }

    public static NavigateToModel fromOverallRanking() {
        return instrumented(NavigateToModel.class);
    }
}
