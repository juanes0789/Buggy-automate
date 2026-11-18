package co.edu.udea.certification.buggycars.utils;

/** Valores fijos del proyecto. La URL se puede cambiar con -Dbuggy.url=... */
public final class Constants {

    public static final String BASE_URL = System.getProperty("buggy.url", "https://buggy.justtestit.org/");
    public static final String DEFAULT_PASSWORD = "Test1234!";
    public static final int TIMEOUT_SECONDS = 20;

    private Constants() {
    }
}
