package co.edu.udea.certification.buggycars.utils;

import java.util.concurrent.atomic.AtomicInteger;

/** Genera datos unicos por ejecucion para no depender de datos previos del ambiente compartido. */
public final class TestData {

    private static final AtomicInteger COUNTER = new AtomicInteger();

    private TestData() {
    }

    public static String uniqueId() {
        return System.currentTimeMillis() + String.valueOf(COUNTER.incrementAndGet());
    }

    public static String uniqueUsername(String prefix) {
        return prefix + uniqueId();
    }

    /**
     * Agrega un identificador al texto para poder encontrar exactamente lo que escribio esta ejecucion.
     * Sin esto la verificacion encontraria comentarios viejos con el mismo texto (falso positivo).
     */
    public static String uniqueText(String base) {
        return base + " ref " + uniqueId();
    }

    public static String repeat(char character, int length) {
        return String.valueOf(character).repeat(length);
    }
}
