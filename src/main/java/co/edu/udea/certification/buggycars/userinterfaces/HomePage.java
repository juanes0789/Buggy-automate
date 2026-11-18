package co.edu.udea.certification.buggycars.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/** Barra de navegacion (login / registro / saludo) y pagina principal. */
public class HomePage {

    private HomePage() {
    }

    public static final Target BRAND = Target.the("logo de Buggy Rating")
            .located(By.cssSelector("a.navbar-brand"));

    public static final Target LOGIN_USERNAME = Target.the("campo username del login")
            .located(By.cssSelector("input[name='login']"));

    public static final Target LOGIN_PASSWORD = Target.the("campo password del login")
            .located(By.cssSelector("input[name='password']"));

    public static final Target LOGIN_BUTTON = Target.the("boton Login")
            .located(By.xpath("//my-login//button[normalize-space()='Login']"));

    public static final Target REGISTER_LINK = Target.the("link Register")
            .located(By.xpath("//my-login//a[normalize-space()='Register']"));

    public static final Target GREETING = Target.the("saludo del usuario")
            .located(By.xpath("//my-login//span[contains(@class,'nav-link') and starts-with(normalize-space(),'Hi,')]"));

    public static final Target LOGOUT_LINK = Target.the("link Logout")
            .located(By.xpath("//my-login//a[normalize-space()='Logout']"));

    public static final Target LOGIN_ERROR = Target.the("mensaje de error del login")
            .located(By.xpath("//my-login//span[contains(@class,'label-warning')]"));

    /** Lo que aparece despues de intentar iniciar sesion: el link Logout o el mensaje de error. */
    public static final Target LOGIN_OUTCOME = Target.the("resultado del login")
            .located(By.xpath("//my-login//a[normalize-space()='Logout'] | //my-login//span[contains(@class,'label-warning')]"));

    public static final Target OVERALL_RANKING_LINK = Target.the("ranking general de modelos")
            .located(By.cssSelector("a[href='/overall']"));

    /**
     * Segundo modelo del ranking general. El primero (Diablo) tiene miles de comentarios y el
     * servidor ya no guarda comentarios nuevos para el (acepta el voto pero descarta el texto).
     */
    public static final Target SECOND_RANKED_MODEL = Target.the("segundo modelo del ranking general")
            .located(By.xpath("(//table/tbody/tr)[2]//a[contains(@href,'/model/')]"));

    public static final Target FIRST_POPULAR_MODEL = Target.the("modelo mas popular")
            .located(By.cssSelector("a[href^='/model/']"));
}
