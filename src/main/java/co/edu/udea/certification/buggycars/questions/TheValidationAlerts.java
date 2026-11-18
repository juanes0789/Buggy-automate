package co.edu.udea.certification.buggycars.questions;

import co.edu.udea.certification.buggycars.userinterfaces.RegisterPage;
import net.serenitybdd.screenplay.annotations.Subject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

import java.util.List;

/** Alertas de validacion del formulario que estan visibles (las ocultas se ignoran). */
@Subject("las alertas de validacion visibles del formulario")
public class TheValidationAlerts implements Question<List<String>> {

    @Override
    public List<String> answeredBy(Actor actor) {
        return RegisterPage.VALIDATION_ALERTS.resolveAllFor(actor).stream()
                .filter(WebElementFacade::isVisible)
                .map(alert -> alert.getText().trim())
                .toList();
    }

    public static TheValidationAlerts visible() {
        return new TheValidationAlerts();
    }
}
