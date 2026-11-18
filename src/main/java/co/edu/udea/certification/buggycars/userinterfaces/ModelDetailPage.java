package co.edu.udea.certification.buggycars.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class ModelDetailPage {

    public static final Target COMMENT_FIELD = Target.the("campo de comentario")
            .located(By.cssSelector("textarea#comment, input#comment, textarea[name='comment'], [formcontrolname='comment']"));

    public static final Target VOTE_BUTTON = Target.the("boton votar")
            .located(By.cssSelector("button.btn.btn-success, button[type='submit']"));

    public static final Target COMMENTS_TABLE = Target.the("tabla de comentarios")
            .located(By.xpath("//table"));
}