package co.edu.udea.certification.buggycars.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class HomePage {

    public static final Target LOGIN_USERNAME = Target.the("campo username login")
            .located(By.cssSelector("input[name='login']"));

    public static final Target LOGIN_PASSWORD = Target.the("campo password login")
            .located(By.cssSelector("input[name='password']"));

    public static final Target LOGIN_BUTTON = Target.the("boton login")
            .located(By.xpath("//button[contains(text(),'Login')]"));

    public static final Target REGISTER_LINK = Target.the("link registro")
            .located(By.xpath("//a[contains(text(),'Register')]"));

    public static final Target USER_NAME_DISPLAY = Target.the("nombre del usuario logueado")
            .located(By.xpath("//span[contains(@class,'navbar-text')] | //a[contains(@class,'username') or starts-with(@href,'javascript')]"));

    public static final Target LOGOUT_LINK = Target.the("link logout")
            .located(By.xpath("//a[contains(text(),'Logout') or contains(normalize-space(.),'Logout')]"));

    public static final Target ERROR_MESSAGE = Target.the("mensaje de error login")
            .located(By.xpath("//span[contains(@class,'label-warning')]"));

    public static final Target FIRST_MODEL_LINK = Target.the("primer modelo popular")
            .located(By.cssSelector("a[href*='/model/']"));
}