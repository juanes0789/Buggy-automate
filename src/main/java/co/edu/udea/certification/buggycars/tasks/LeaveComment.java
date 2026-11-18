package co.edu.udea.certification.buggycars.tasks;

import co.edu.udea.certification.buggycars.interactions.Wait;
import co.edu.udea.certification.buggycars.userinterfaces.ModelDetailPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.annotations.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class LeaveComment implements Task {

    private final String comment;

    public LeaveComment(String comment) {
        this.comment = comment;
    }

    @Override
    @Step("{0} deja el comentario: #comment")
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(ModelDetailPage.COMMENT_FIELD, isVisible()).forNoMoreThan(15).seconds(),
                SetCommentAndVoteViaJs.withText(comment),
                Wait.forSeconds(5)
        );
    }

    public static LeaveComment withText(String comment) {
        return instrumented(LeaveComment.class, comment);
    }

    static class SetCommentAndVoteViaJs implements Interaction {
        private final String text;

        SetCommentAndVoteViaJs(String text) { this.text = text; }

        static SetCommentAndVoteViaJs withText(String text) { return new SetCommentAndVoteViaJs(text); }

        @Override
        public <T extends Actor> void performAs(T actor) {
            WebDriver driver = BrowseTheWeb.as(actor).getDriver();
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement textarea = ModelDetailPage.COMMENT_FIELD.resolveFor(actor);

            // Usamos execCommand('insertText') que simula inserción real a nivel
            // del motor del navegador y dispara todos los eventos nativos que el
            // DefaultValueAccessor del NgModel escucha (incluye 'input' con
            // isTrusted correcto). Sin esto, Angular no actualiza el FormControl.
            textarea.click();
            String setScript =
                "var el = arguments[0];" +
                "var val = arguments[1];" +
                "el.focus();" +
                "el.select();" +
                "document.execCommand('delete', false, null);" +
                "document.execCommand('insertText', false, val);" +
                "return el.value;";
            Object domValue = js.executeScript(setScript, textarea, text);
            System.out.println("[LeaveComment] DOM value after insertText: '" + domValue + "'");

            // Dejamos foco fuera para forzar el blur y que Angular confirme el valor
            js.executeScript("arguments[0].blur();", textarea);

            // Pausa para que Angular procese change detection
            try { Thread.sleep(1500); } catch (InterruptedException ignored) {}

            // Click vía JS para evitar problemas de timing de Selenium
            Object voteResult = js.executeScript(
                "var btn = document.querySelector('button.btn.btn-success');" +
                "if (!btn) return 'NO_BUTTON';" +
                "if (btn.disabled) return 'DISABLED';" +
                "btn.click();" +
                "return 'CLICKED:' + btn.textContent.trim();");
            System.out.println("[LeaveComment] Vote result: " + voteResult);
        }
    }
}
