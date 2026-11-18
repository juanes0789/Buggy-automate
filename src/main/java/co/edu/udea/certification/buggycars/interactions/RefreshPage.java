package co.edu.udea.certification.buggycars.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Recarga la pagina hasta que aparezca un elemento.
 *
 * Buggy Cars no actualiza la pantalla despues de votar: el resultado guardado solo se ve
 * al volver a cargar el modelo. Se reintenta varias veces porque el servidor puede tardar
 * un poco en registrar el voto. Si el elemento nunca aparece, la Question del paso
 * "Entonces" es la que reporta el fallo.
 */
public class RefreshPage implements Interaction {

    private static final int MAX_ATTEMPTS = 5;

    private final Target expectedElement;

    public RefreshPage(Target expectedElement) {
        this.expectedElement = expectedElement;
    }

    @Override
    @Step("{0} recarga la pagina hasta ver el #expectedElement")
    public <T extends Actor> void performAs(T actor) {
        for (int attempt = 1; attempt <= MAX_ATTEMPTS; attempt++) {
            pauseOneSecond();
            BrowseTheWeb.as(actor).getDriver().navigate().refresh();
            // resolveAllFor usa la espera implicita configurada en serenity.conf
            boolean visible = expectedElement.resolveAllFor(actor).stream()
                    .anyMatch(WebElementFacade::isVisible);
            if (visible) {
                return;
            }
        }
    }

    private static void pauseOneSecond() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static RefreshPage untilVisible(Target expectedElement) {
        return instrumented(RefreshPage.class, expectedElement);
    }
}
