package co.edu.udea.certification.buggycars.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.annotations.Step;

public class Wait implements Interaction {

    private final int seconds;

    public Wait(int seconds) {
        this.seconds = seconds;
    }

    @Override
    @Step("{0} espera #seconds segundos")
    public <T extends Actor> void performAs(T actor) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static Wait forSeconds(int seconds) {
        return new Wait(seconds);
    }
}