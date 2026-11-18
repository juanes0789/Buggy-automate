package co.edu.udea.certification.buggycars.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class RegisterPage {

    public static final Target USERNAME = Target.the("campo username registro")
            .located(By.id("username"));

    public static final Target FIRST_NAME = Target.the("campo nombre")
            .located(By.id("firstName"));

    public static final Target LAST_NAME = Target.the("campo apellido")
            .located(By.id("lastName"));

    public static final Target PASSWORD = Target.the("campo password")
            .located(By.id("password"));

    public static final Target CONFIRM_PASSWORD = Target.the("campo confirmar password")
            .located(By.id("confirmPassword"));

    public static final Target REGISTER_BUTTON = Target.the("boton registrar")
            .located(By.xpath("//button[contains(text(),'Register')]"));

    public static final Target SUCCESS_MESSAGE = Target.the("mensaje exito registro")
            .located(By.xpath("//*[contains(normalize-space(.),'Registration is successful') or contains(normalize-space(.),'successful') or contains(@class,'text-success') or contains(@class,'result-success')]"));

    public static final Target ERROR_MESSAGE = Target.the("mensaje error registro")
            .located(By.xpath("//*[contains(normalize-space(.),'UserAlreadyExists') or contains(normalize-space(.),'already exists') or contains(normalize-space(.),'already taken') or contains(@class,'text-danger') or contains(@class,'alert-danger')]"));

    public static final Target CANCEL_LINK = Target.the("link cancelar")
            .located(By.xpath("//a[contains(text(),'Cancel')]"));
}