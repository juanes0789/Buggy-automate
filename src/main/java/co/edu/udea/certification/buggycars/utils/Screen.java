package co.edu.udea.certification.buggycars.utils;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Lee la pantalla tal como esta en este momento, sin espera implicita.
 *
 * Serenity y Selenium esperan hasta que un elemento aparezca o se habilite. Eso sirve para
 * verificar que algo SI esta, pero cuando se espera que algo NO este, cada verificacion
 * gastaria el tiempo de espera completo (10 a 15 s). Las Questions que verifican ausencia
 * usan esta clase. La sincronizacion con la pagina ya la hicieron los WaitUntil de las Tasks.
 */
public final class Screen {

    private Screen() {
    }

    /** Elementos del Target que estan visibles ahora mismo. */
    public static List<WebElement> displayedElements(Actor actor, Target target) {
        WebDriver driver = BrowseTheWeb.as(actor).getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ZERO);
        try {
            List<WebElement> displayed = new ArrayList<>();
            for (By locator : target.selectors(driver)) {
                for (WebElement element : driver.findElements(locator)) {
                    if (element.isDisplayed()) {
                        displayed.add(element);
                    }
                }
            }
            return displayed;
        } finally {
            // El driver de Serenity no permite leer la espera actual: se restaura la de serenity.conf
            driver.manage().timeouts().implicitlyWait(Duration.ofMillis(Constants.IMPLICIT_WAIT_MILLIS));
        }
    }
}
