package co.edu.udea.certification.buggycars.interactions;

import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Pausa visible entre pasos para la demo (lo pide la rubrica: "uso del Delay o sleep entre paso y paso").
 *
 * No se usa para sincronizar con la pagina: de eso se encargan los WaitUntil de cada Task.
 * Por eso se puede apagar para una ejecucion rapida (principio Fast de F.I.R.S.T):
 *   ./gradlew clean test -Ddemo.pausa=false
 */
public class Wait implements Interaction {

    private static final boolean DEMO_PAUSES_ENABLED =
            Boolean.parseBoolean(System.getProperty("demo.pausa", "true"));

    private final int seconds;

    public Wait(int seconds) {
        this.seconds = seconds;
    }

    @Override
    @Step("{0} hace una pausa de #seconds segundos para observar la prueba")
    public <T extends Actor> void performAs(T actor) {
        if (!DEMO_PAUSES_ENABLED || seconds <= 0) {
            return;
        }
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static Wait forSeconds(int seconds) {
        return instrumented(Wait.class, seconds);
    }
}
