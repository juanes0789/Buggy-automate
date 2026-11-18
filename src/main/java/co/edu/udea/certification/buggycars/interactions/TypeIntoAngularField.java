package co.edu.udea.certification.buggycars.interactions;

import co.edu.udea.certification.buggycars.exceptions.AngularFieldNotUpdatedException;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.screenplay.Tasks.instrumented;

/**
 * Escribe texto en un campo enlazado con ngModel de Angular.
 *
 * Un sendKeys normal de Selenium no actualiza el modelo de este campo y el boton "Vote!" no
 * reconoce el comentario. document.execCommand('insertText') inserta el texto como lo haria
 * el navegador y dispara los eventos nativos "input" que Angular escucha.
 */
public class TypeIntoAngularField implements Interaction {

    private static final String INSERT_TEXT_SCRIPT =
            "var el = arguments[0];" +
            "el.focus();" +
            "el.select();" +
            "document.execCommand('delete', false, null);" +
            "document.execCommand('insertText', false, arguments[1]);" +
            "el.blur();" +
            "return el.value;";

    private final String text;
    private final Target field;

    public TypeIntoAngularField(String text, Target field) {
        this.text = text;
        this.field = field;
    }

    @Override
    @Step("{0} escribe '#text' en el #field")
    public <T extends Actor> void performAs(T actor) {
        WebElement element = field.resolveFor(actor);
        element.click();
        JavascriptExecutor js = (JavascriptExecutor) BrowseTheWeb.as(actor).getDriver();
        Object value = js.executeScript(INSERT_TEXT_SCRIPT, element, text);
        if (!text.equals(value)) {
            throw new AngularFieldNotUpdatedException(
                    "El " + field + " quedo con '" + value + "' en vez de '" + text + "'");
        }
    }

    public static Builder theText(String text) {
        return new Builder(text);
    }

    public static class Builder {
        private final String text;

        private Builder(String text) {
            this.text = text;
        }

        public TypeIntoAngularField into(Target field) {
            return instrumented(TypeIntoAngularField.class, text, field);
        }
    }
}
