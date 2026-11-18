package co.edu.udea.certification.buggycars.exceptions;

/**
 * Error de negocio usado con seeThat(...).orComplainWith(...):
 * en el reporte de Serenity aparece este mensaje claro en vez de un error generico de Hamcrest.
 */
public class ExpectedResultNotFoundError extends AssertionError {

    public ExpectedResultNotFoundError(String message, Throwable cause) {
        super(message, cause);
    }
}
