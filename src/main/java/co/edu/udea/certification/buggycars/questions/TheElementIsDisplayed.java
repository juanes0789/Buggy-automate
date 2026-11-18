package co.edu.udea.certification.buggycars.questions;

import co.edu.udea.certification.buggycars.utils.Screen;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

/**
 * Indica si un elemento se ve en este momento, sin esperar a que aparezca.
 * Se usa para verificar que algo NO esta (por ejemplo, el formulario de voto despues de votar).
 */
public class TheElementIsDisplayed implements Question<Boolean> {

    private final Target target;

    public TheElementIsDisplayed(Target target) {
        this.target = target;
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        return !Screen.displayedElements(actor, target).isEmpty();
    }

    @Override
    public String toString() {
        return "si se ve el " + target;
    }

    public static TheElementIsDisplayed now(Target target) {
        return new TheElementIsDisplayed(target);
    }
}
