package co.edu.udea.certification.buggycars.userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

/** Formulario de registro (/register). */
public class RegisterPage {

    private RegisterPage() {
    }

    public static final Target USERNAME = Target.the("campo Login del registro")
            .located(By.id("username"));

    public static final Target FIRST_NAME = Target.the("campo First Name")
            .located(By.id("firstName"));

    public static final Target LAST_NAME = Target.the("campo Last Name")
            .located(By.id("lastName"));

    public static final Target PASSWORD = Target.the("campo Password")
            .located(By.id("password"));

    public static final Target CONFIRM_PASSWORD = Target.the("campo Confirm Password")
            .located(By.id("confirmPassword"));

    public static final Target REGISTER_BUTTON = Target.the("boton Register")
            .located(By.xpath("//button[@type='submit' and normalize-space()='Register']"));

    /** Mensaje que devuelve el servidor al enviar el formulario (exito o error). */
    public static final Target RESULT_MESSAGE = Target.the("mensaje de resultado del registro")
            .located(By.cssSelector("div.result.alert"));

    /** Alertas de validacion del formulario. Estan en el DOM pero ocultas hasta que aplican. */
    public static final Target VALIDATION_ALERTS = Target.the("alertas de validacion del formulario")
            .located(By.cssSelector("form .form-group .alert.alert-danger"));
}
