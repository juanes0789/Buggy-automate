package co.edu.udea.certification.buggycars.models;

import co.edu.udea.certification.buggycars.utils.TestData;

/**
 * Datos de un usuario de Buggy Cars Rating.
 * Se usa un record de Java 17 para que el modelo sea inmutable.
 */
public record User(String username, String firstName, String lastName, String password, String confirmPassword) {

    /** Usuario con un username concreto y confirmacion igual al password. */
    public static User withUsername(String username, String firstName, String lastName, String password) {
        return new User(username, firstName, lastName, password, password);
    }

    /** Usuario con un username unico para que cada ejecucion sea independiente y repetible (F.I.R.S.T). */
    public static User unique(String firstName, String lastName, String password) {
        return withUsername(TestData.uniqueUsername("buggy"), firstName, lastName, password);
    }

    /** Copia del usuario con una confirmacion de password distinta. */
    public User withConfirmation(String confirmation) {
        return new User(username, firstName, lastName, password, confirmation);
    }
}
