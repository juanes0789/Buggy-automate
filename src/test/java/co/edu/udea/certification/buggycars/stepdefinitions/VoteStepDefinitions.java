package co.edu.udea.certification.buggycars.stepdefinitions;

import co.edu.udea.certification.buggycars.exceptions.ExpectedResultNotFoundError;
import co.edu.udea.certification.buggycars.models.User;
import co.edu.udea.certification.buggycars.questions.TheCommentList;
import co.edu.udea.certification.buggycars.questions.TheVoteCount;
import co.edu.udea.certification.buggycars.questions.TheVoteStatus;
import co.edu.udea.certification.buggycars.tasks.LoginAs;
import co.edu.udea.certification.buggycars.tasks.NavigateToModel;
import co.edu.udea.certification.buggycars.tasks.OpenHomePage;
import co.edu.udea.certification.buggycars.tasks.RegisterUser;
import co.edu.udea.certification.buggycars.tasks.SubmitVote;
import co.edu.udea.certification.buggycars.tasks.WriteComment;
import co.edu.udea.certification.buggycars.userinterfaces.ModelDetailPage;
import co.edu.udea.certification.buggycars.utils.ActorMemory;
import co.edu.udea.certification.buggycars.utils.Constants;
import co.edu.udea.certification.buggycars.utils.TestData;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.questions.Visibility;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;

public class VoteStepDefinitions {

    // ===== DADO =====

    @Dado("que el usuario se registra e inicia sesion con una cuenta nueva")
    public void seRegistraEIniciaSesion() {
        Actor actor = theActorInTheSpotlight();
        User user = User.unique("Auto", "Tester", Constants.DEFAULT_PASSWORD);
        actor.attemptsTo(
                OpenHomePage.onBuggyCars(),
                RegisterUser.as(user),
                OpenHomePage.onBuggyCars(),
                LoginAs.user(user.username(), user.password())
        );
        actor.remember(ActorMemory.REGISTERED_USER, user);
    }

    // ===== CUANDO =====

    @Cuando("navega a un modelo del ranking general")
    public void navegaAlModeloMasPopular() {
        Actor actor = theActorInTheSpotlight();
        actor.attemptsTo(NavigateToModel.fromOverallRanking());
        actor.remember(ActorMemory.VOTES_BEFORE, TheVoteCount.ofTheModel());
    }

    /** Se agrega un identificador unico: asi se verifica el comentario de ESTA ejecucion y no uno viejo. */
    @Cuando("escribe el comentario {string}")
    public void escribeElComentario(String comment) {
        String uniqueComment = TestData.uniqueText(comment);
        theActorInTheSpotlight().remember(ActorMemory.COMMENT, uniqueComment);
        theActorInTheSpotlight().attemptsTo(WriteComment.withText(uniqueComment));
    }

    @Cuando("envia el voto")
    public void enviaElVoto() {
        theActorInTheSpotlight().attemptsTo(SubmitVote.now());
    }

    // ===== ENTONCES =====

    @Entonces("el comentario queda registrado en la lista de comentarios del modelo")
    public void elComentarioQuedaRegistrado() {
        String comment = theActorInTheSpotlight().recall(ActorMemory.COMMENT);
        theActorInTheSpotlight().should(
                seeThat(TheCommentList.content(), containsString(comment))
                        .orComplainWith(ExpectedResultNotFoundError.class,
                                "El comentario '" + comment + "' no aparece en la lista de comentarios")
        );
    }

    @Entonces("el conteo de votos del modelo aumenta")
    public void elConteoDeVotosAumenta() {
        Integer votesBefore = theActorInTheSpotlight().recall(ActorMemory.VOTES_BEFORE);
        theActorInTheSpotlight().should(
                seeThat(TheVoteCount.ofTheModel(), greaterThan(votesBefore))
                        .orComplainWith(ExpectedResultNotFoundError.class,
                                "El conteo de votos no aumento (antes habia " + votesBefore + ")")
        );
    }

    @Entonces("el sistema muestra el mensaje de votacion {string}")
    public void muestraElMensajeDeVotacion(String expectedMessage) {
        theActorInTheSpotlight().should(
                seeThat(TheVoteStatus.displayed(), equalTo(expectedMessage))
        );
    }

    @Entonces("(ya )no se muestra el formulario para votar")
    public void noSeMuestraElFormularioParaVotar() {
        theActorInTheSpotlight().should(
                seeThat("el campo de comentario es visible", Visibility.of(ModelDetailPage.COMMENT_FIELD), is(false)),
                seeThat("el boton Vote! es visible", Visibility.of(ModelDetailPage.VOTE_BUTTON), is(false))
        );
    }
}
