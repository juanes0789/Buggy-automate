package co.edu.udea.certification.buggycars.exceptions;

/** Se lanza cuando un campo de Angular no quedo con el texto que se intento escribir. */
public class AngularFieldNotUpdatedException extends RuntimeException {

    public AngularFieldNotUpdatedException(String message) {
        super(message);
    }
}
