package co.edu.udea.certification.buggycars.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Ejecuta todos los features. Para filtrar por tags:
 *   ./gradlew clean test -Dcucumber.filter.tags="@smoke"
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = "co.edu.udea.certification.buggycars.stepdefinitions",
        plugin = {"pretty"},
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class Runner {
}
