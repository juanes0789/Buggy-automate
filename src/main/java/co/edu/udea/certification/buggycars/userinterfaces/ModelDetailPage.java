package co.edu.udea.certification.buggycars.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/** Pagina de detalle de un modelo de auto (/model/...). */
public class ModelDetailPage {

    private ModelDetailPage() {
    }

    public static final Target MODEL_NAME = Target.the("nombre del modelo")
            .located(By.xpath("//my-model//h3"));

    public static final Target VOTE_COUNT = Target.the("conteo de votos")
            .located(By.xpath("//my-model//h4[starts-with(normalize-space(),'Votes:')]/strong"));

    public static final Target COMMENT_FIELD = Target.the("campo de comentario")
            .located(By.cssSelector("textarea#comment"));

    public static final Target VOTE_BUTTON = Target.the("boton Vote!")
            .located(By.xpath("//my-model//button[normalize-space()='Vote!']"));

    /** "Thank you for your vote!" o "You need to be logged in to vote." */
    public static final Target VOTE_STATUS = Target.the("mensaje de estado del voto")
            .located(By.cssSelector("my-model p.card-text"));

    public static final Target COMMENTS_TABLE = Target.the("tabla de comentarios")
            .located(By.xpath("//my-model//table/tbody"));
}
