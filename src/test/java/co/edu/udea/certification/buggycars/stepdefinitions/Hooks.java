package co.edu.udea.certification.buggycars.stepdefinitions;

import io.cucumber.java.Before;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;

/**
 * Prepara el escenario antes de cada prueba: el OnlineCast le da al actor la habilidad
 * BrowseTheWeb con un navegador administrado por Serenity (uno nuevo por escenario).
 */
public class Hooks {

    public static final String ACTOR_NAME = "Usuario";

    @Before(order = 0)
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled(ACTOR_NAME);
    }
}
